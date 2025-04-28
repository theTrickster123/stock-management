package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.dto.CreateProductDTO;
import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.dto.ProductSaleResponseDTO;
import com.stockmanager.stockmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*@PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        ProductDTO created = productService.createProductFromDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }*/

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        ProductDTO createdProduct = productService.createProduct(createProductDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // ✅ Exemple d’un autre endpoint
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO, id);
        return ResponseEntity.ok(productDTO);
    }
    // Endpoint pour vendre un produit a test plus tard
    @PostMapping("/{productId}/sell")
    public ResponseEntity<ProductSaleResponseDTO> sellProduct(
            @PathVariable Long productId,
            @RequestParam int quantitySold) {

        ProductSaleResponseDTO responseDTO = productService.sellProduct(productId, quantitySold);

        return ResponseEntity.ok(responseDTO);
    }


}
