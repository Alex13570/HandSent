package ru.ivmiit.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.request.NewsRequest;
import ru.ivmiit.dto.response.NewsResponse;
import ru.ivmiit.exceptions.faq.FaqNotFoundException;
import ru.ivmiit.exceptions.news.NewsNotFoundException;
import ru.ivmiit.mappers.NewsMapper;
import ru.ivmiit.models.NewsEntity;
import ru.ivmiit.models.OrderEntity;
import ru.ivmiit.models.UserEntity;
import ru.ivmiit.repositories.NewsRepository;
import ru.ivmiit.repositories.UserRepository;
import ru.ivmiit.services.NewsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    private final UserRepository userRepository;

    @Override
    public List<NewsResponse> findAllNews() {
        return newsRepository.findAll().stream().map(newsMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public NewsResponse findNewsById(Long id) {
        return newsMapper.toResponse(newsRepository.findById(id).orElseThrow(FaqNotFoundException::new));
    }

    @Override
    public NewsResponse addNews(NewsRequest request) {
        NewsEntity newsEntity = newsMapper.toEntity(request);
        newsEntity.setAuthor(getCurrentUser());
        return newsMapper.toResponse(newsRepository.save(newsEntity));
    }

    @Override
    public NewsResponse updateNewsById(Long id, NewsRequest request) {
        NewsEntity news = newsRepository.findById(id).orElseThrow(NewsNotFoundException::new);
        if (news.getAuthor() == getCurrentUser()){
            news.setTitle(request.getTitle());
            news.setDescription(request.getDescription());
            return newsMapper.toResponse(newsRepository.save(news));
        }
        return null;
    }

    @Override
    public void deleteNewsById(Long id) {
        NewsEntity news = newsRepository.findById(id).orElseThrow(NewsNotFoundException::new);
        if (news.getAuthor() == getCurrentUser()){
            newsRepository.deleteById(id);
        }
    }

    private UserEntity getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername()).get();
    }
}
