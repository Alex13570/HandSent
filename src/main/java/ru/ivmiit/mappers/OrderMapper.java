package ru.ivmiit.mappers;

import org.mapstruct.Mapper;
import ru.ivmiit.dto.request.OrderRequest;
import ru.ivmiit.dto.response.OrderResponse;
import ru.ivmiit.models.OrderEntity;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface OrderMapper extends BaseMapper<OrderEntity, OrderRequest, OrderResponse>{
}
