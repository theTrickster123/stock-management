package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.mapper.ProductMapper;
import com.stockmanager.stockmanager.model.Product;
import com.stockmanager.stockmanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ChatbotService(ProductService productService, ProductRepository productRepository, ProductMapper productMapper) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public String handleMessage(String message) {
        message = message.toLowerCase();

        if (message.contains("les plus vendus")) {
            return handleTopSelling();
        } else if (message.contains("les moins vendus")) {
            return handleLeastSelling();
        } else if (message.contains("sans revenu") || message.contains("rien") || message.contains("ne rapportent") || message.contains(" ne rapportent pas de l'argent") ) {
            return handleNoIncome();
        } else if (message.contains("rentable") && message.contains("produit")) {
            return handleProfitability(message);
        } else {
            return "Je suis désolé, je n'ai pas compris votre question. Veuillez reformuler.";
        }
    }

    private String handleTopSelling() {
        List<ProductDTO> topProducts = productService.findTopSellingProducts();
        if (topProducts.isEmpty()) return "Aucun produit vendu pour le moment.";
        return "Les produits les plus vendus sont : " + formatProductNames(topProducts);
    }

    private String handleLeastSelling() {
        List<ProductDTO> leastProducts = productService.findLowSellingProducts();
        if (leastProducts.isEmpty()) return "Tous les produits ont un bon volume de vente.";
        return "Les produits les moins vendus sont : " + formatProductNames(leastProducts);
    }

    private String handleNoIncome() {
        List<ProductDTO> noIncome = productRepository.findProductsWithNoIncome()
                .stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());

        if (noIncome.isEmpty()) return "Tous les produits génèrent un revenu.";
        return "Les produits sans revenu sont : " + formatProductNames(noIncome);
    }

    private String handleProfitability(String message) {
        message = message.toLowerCase();

        // Vérifie que la phrase contient bien les deux expressions clés
        if (!message.contains("produit") || !message.contains("est-il")) {
            return "Veuillez formuler votre question comme : 'Le produit [nom] est-il rentable ?'";
        }

        // Récupère la sous-chaîne entre "produit" et "est-il"
        int startIndex = message.indexOf("produit") + "produit".length();
        int endIndex = message.indexOf("est-il");

        if (startIndex >= endIndex) {
            return "Le nom du produit semble manquant ou mal placé.";
        }

        String rawName = message.substring(startIndex, endIndex).trim().replace("?", "");

        // Capitalisation basique pour correspondre à un titre (optionnel selon ta base de données)
        String productName = capitalizeEachWord(rawName);

        Optional<Product> productOpt = productService.getProductByTitle(productName);

        if (productOpt.isEmpty()) {
            return "Le produit \"" + productName + "\" n'existe pas.";
        }

        Product product = productOpt.get();
        BigDecimal benefit = product.getTotalIncome().subtract(product.getTotalCharges());

        if (benefit.compareTo(BigDecimal.ZERO) > 0) {
            return "Oui, le produit \"" + product.getTitle() + "\" est rentable avec un bénéfice de " + benefit + " DH.";
        } else if (benefit.compareTo(BigDecimal.ZERO) < 0) {
            return "Non, le produit \"" + product.getTitle() + "\" n'est pas rentable. Il a une perte de " + benefit.abs() + " DH.";
        } else {
            return "Le produit \"" + product.getTitle() + "\" est à l'équilibre (ni perte ni bénéfice).";
        }
    }

    // Fonction utilitaire : met une majuscule au début de chaque mot
    private String capitalizeEachWord(String input) {
        String[] words = input.trim().split("\\s+");
        return Arrays.stream(words)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    private String formatProductNames(List<ProductDTO> products) {
        return products.stream()
                .map(ProductDTO::getTitle)
                .collect(Collectors.joining(", "));
    }
}

