package com.alten.ecommerce.controller;

import com.alten.ecommerce.dto.ProductDTO;
import com.alten.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
@Tag(name = "Certificate Operations", description = "Endpoints for managing certificates.")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> products() {
        List<ProductDTO> products = productService.listProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable @Min(1) long id) {
        ProductDTO product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }


    /**
     * Retrieves a certificate by its ID.
     * @return The certificate data corresponding to the provided ID.
     */
    @Operation(
            summary = "Find Certificate by ID",
            description = "Endpoint for finding a certificate by its ID. Accessible to users with the 'ORGANIZATION' role and matching client ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Certificate found successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found. Certificate with the provided ID does not exist.",
                    content = @Content(
                            mediaType = "application/json"
                            //schema = @Schema(implementation = ProductDTO.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO createProduct) {
        ProductDTO savedProduct = productService.createProduct(createProduct);
        return ResponseEntity.status(201).body(savedProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable @Min(1) long id,
            @RequestBody @Valid ProductDTO updatedProductDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, updatedProductDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Min(1) long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> filterProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category) {
        List<ProductDTO> filteredProducts = productService.filterProducts(name, minPrice, maxPrice, category);
        return ResponseEntity.ok(filteredProducts);
    }

}
