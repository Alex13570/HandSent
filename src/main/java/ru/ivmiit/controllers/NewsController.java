package ru.ivmiit.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.request.FaqRequest;
import ru.ivmiit.dto.request.NewsRequest;
import ru.ivmiit.dto.response.FaqResponse;
import ru.ivmiit.dto.response.NewsResponse;
import ru.ivmiit.services.NewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<NewsResponse> findAllNews(){
        return newsService.findAllNews();
    }

    @GetMapping("/{id}")
    public NewsResponse findNewsById(@PathVariable Long id){
        return newsService.findNewsById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public NewsResponse saveNews(@RequestBody NewsRequest request){
        return newsService.addNews(request);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public NewsResponse updateNews(@PathVariable Long id, @RequestBody NewsRequest request){
        return newsService.updateNewsById(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteNews(@PathVariable Long id) {
        newsService.deleteNewsById(id);
        return("OK");
    }

}
