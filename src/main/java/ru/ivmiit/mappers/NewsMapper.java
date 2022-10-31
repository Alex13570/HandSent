package ru.ivmiit.mappers;

import org.mapstruct.Mapper;
import ru.ivmiit.dto.request.NewsRequest;
import ru.ivmiit.dto.response.NewsResponse;
import ru.ivmiit.models.NewsEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface NewsMapper extends BaseMapper<NewsEntity, NewsRequest, NewsResponse> {
}
