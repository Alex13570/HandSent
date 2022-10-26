package ru.ivmiit.mappers;

import org.mapstruct.Mapper;
import ru.ivmiit.dto.request.ProductRequest;
import ru.ivmiit.dto.response.ProductResponse;
import ru.ivmiit.models.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, ProductRequest, ProductResponse> {
}
