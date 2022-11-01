package ru.ivmiit.mappers;

import org.mapstruct.Mapper;
import ru.ivmiit.dto.request.FaqRequest;
import ru.ivmiit.dto.response.FaqResponse;
import ru.ivmiit.models.FaqEntity;

@Mapper(componentModel = "spring")
public interface FaqMapper extends BaseMapper<FaqEntity, FaqRequest, FaqResponse>{
}
