package ru.ivmiit.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.request.ProductRequest;
import ru.ivmiit.dto.response.ProductResponse;
import ru.ivmiit.models.ProductEntity;
import ru.ivmiit.services.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = {"${settings.cors_origin}"})
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products that are available.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found products",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))) }),
            @ApiResponse(responseCode = "403", description = "User is Not Authorized",
                    content = @Content) })
    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return productService.findAllProducts();
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)) }),
            @ApiResponse(responseCode = "403", description = "User is Not Authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        System.out.println("i am here");
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse saveProduct(@RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProductById(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.updateProductById(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "OK";
    }
}
