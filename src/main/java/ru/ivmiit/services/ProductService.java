package ru.ivmiit.services;

import ru.ivmiit.dto.request.ProductRequest;
import ru.ivmiit.dto.response.ProductResponse;
import ru.ivmiit.models.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findAllProducts();

    ProductResponse findById(Long id);

    ProductResponse addProduct(ProductRequest request);

    ProductResponse updateProductById(Long id, ProductRequest request);

    void deleteProductById(Long id);

    ProductEntity findProductEntityById(Long id);
}
