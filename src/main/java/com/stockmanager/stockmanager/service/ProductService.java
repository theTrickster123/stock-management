package com.stockmanager.stockmanager.service;


import com.stockmanager.stockmanager.dto.CreateProductDTO;
import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.dto.ProductSaleResponseDTO;
import com.stockmanager.stockmanager.dto.ProfitAndChargeDTO;
import com.stockmanager.stockmanager.mapper.ProductMapper;
import com.stockmanager.stockmanager.mapper.ProductSaleMapper;
import com.stockmanager.stockmanager.model.Category;
import com.stockmanager.stockmanager.model.Product;
import com.stockmanager.stockmanager.repository.CategoryRepository;
import com.stockmanager.stockmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductSaleMapper productSaleMapper;

    @Autowired
    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository,ProductSaleMapper productSaleMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productSaleMapper = productSaleMapper;
    }


    @Transactional
    public ProductDTO createProduct(CreateProductDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Les données sont manquantes");

        // On récupère la catégorie à partir de son ID
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'id: " + dto.getCategoryId()));


        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category); // on assigne la catégorie récupérée
        product.setActive(dto.getActive());
        product.setManufacturer(dto.getManufacturer());
        product.setOutOfStock(dto.getOutOfStock());
        product.setDetails(dto.getDetails());
        product.setTotalIncome(dto.getTotalIncome());
        product.setTotalCharges(dto.getTotalCharges());
        product.setImage(dto.getImage());

        // Sauvegarde et mapping en DTO
        Product saved = productRepository.save(product);
        return ProductMapper.toDto(saved);
    }

    public ProductDTO findById(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            return ProductMapper.toDto(product); // Convert the Product to ProductDTO using the ProductMapper
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        productRepository.delete(product);
    }

    @Transactional
    public ProductDTO updateProduct(ProductDTO dto, Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Category category = categoryRepository.findById(dto.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategory().getId()));
            Product product = productOpt.get();
            product.setTitle(dto.getTitle());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setQuantity(dto.getQuantity());
            product.setCategory(category);
            product.setActive(dto.getActive());
            product.setManufacturer(dto.getManufacturer());
            product.setOutOfStock(dto.getOutOfStock());
            product.setDetails(dto.getDetails());
            product.setTotalIncome(dto.getTotalIncome());
            product.setTotalCharges(dto.getTotalCharges());
            product.setImage(dto.getImage());
            Product saved = productRepository.save(product);
            return ProductMapper.toDto(saved);
        }
        throw new RuntimeException("Product not found with id " + id);
    }

    //a tester
    @Transactional
    public ProductSaleResponseDTO sellProduct(Long productId, int quantitySold) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        if (product.getQuantity() < quantitySold) {
            throw new RuntimeException("Stock insuffisant pour vendre " + quantitySold + " unités");
        }

        // Mise à jour du produit
        product.setQuantity(product.getQuantity() - quantitySold);
        product.setTotalSoldQuantity(product.getTotalSoldQuantity() + quantitySold);

        BigDecimal saleIncome = product.getPrice().multiply(BigDecimal.valueOf(quantitySold));
        BigDecimal saleCharges = product.getPurchasePrice().multiply(BigDecimal.valueOf(quantitySold));

        product.setTotalIncome(product.getTotalIncome().add(saleIncome));
        product.setTotalCharges(product.getTotalCharges().add(saleCharges));
        product.setOutOfStock(product.getQuantity() <= 0);
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

        // Utiliser MapStruct
        ProductSaleResponseDTO responseDTO = productSaleMapper.toProductSaleResponseDTO(product);

        // Compléter les champs ignorés
        responseDTO.setQuantitySold(quantitySold);
        responseDTO.setSaleIncome(saleIncome);

        return responseDTO;
    }

    //Just added them should test them all and verify if APIs are working

    public List<ProductDTO> findTopSellingProducts() {
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Product::getTotalSoldQuantity).reversed())
                .limit(5) // par exemple, top 5
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findLowSellingProducts() {
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Product::getTotalSoldQuantity))
                .limit(5) // par exemple, top 5
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findTopRevenueProducts() {
        return productRepository.findAll()
                .stream()
                .sorted((p1, p2) -> p2.getPrice().multiply(BigDecimal.valueOf(p2.getTotalSoldQuantity()))
                        .compareTo(p1.getPrice().multiply(BigDecimal.valueOf(p1.getTotalSoldQuantity()))))
                .limit(5)
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProfitAndChargeDTO calculateProfitAndCharges() {
        List<Product> products = productRepository.findAll();

        BigDecimal totalRevenue = products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getTotalSoldQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCost = products.stream()
                .map(p -> p.getPurchasePrice().multiply(BigDecimal.valueOf(p.getTotalSoldQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal profit = totalRevenue.subtract(totalCost);

        return new ProfitAndChargeDTO(totalRevenue, totalCost, profit);
    }

}
