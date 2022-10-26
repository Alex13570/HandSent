package ru.ivmiit.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.request.ProductRequest;
import ru.ivmiit.dto.response.ProductResponse;
import ru.ivmiit.exceptions.product.ProductNotFoundException;
import ru.ivmiit.mappers.ProductMapper;
import ru.ivmiit.models.ProductEntity;
import ru.ivmiit.repositories.ProductRepository;
import ru.ivmiit.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Long id) {
        return productMapper.toResponse(findProductEntityById(id));
    }

    @Override
    public ProductEntity findProductEntityById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        return productMapper.toResponse(productRepository.save(productMapper.toEntity(request)));
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest request) {
        ProductEntity product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProductById(Long id) {
        findById(id);
        productRepository.deleteById(id);
    }
}
