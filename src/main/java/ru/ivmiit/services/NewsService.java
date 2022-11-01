package ru.ivmiit.services;

import ru.ivmiit.dto.request.NewsRequest;
import ru.ivmiit.dto.response.NewsResponse;

import java.util.List;

public interface NewsService {
    List<NewsResponse> findAllNews();

    NewsResponse findNewsById(Long id);

    NewsResponse addNews(NewsRequest faqRequest);

    NewsResponse updateNewsById(Long id, NewsRequest faqRequest);

    void deleteNewsById(Long id);
}
