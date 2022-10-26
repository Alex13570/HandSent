package ru.ivmiit.mappers;

import org.mapstruct.Mapper;
import ru.ivmiit.dto.request.UserRequest;
import ru.ivmiit.dto.response.UserResponse;
import ru.ivmiit.models.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, UserRequest, UserResponse> {
}