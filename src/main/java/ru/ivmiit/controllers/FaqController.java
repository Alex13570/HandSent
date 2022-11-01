package ru.ivmiit.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.request.FaqRequest;
import ru.ivmiit.dto.response.FaqResponse;
import ru.ivmiit.services.FaqService;

import java.util.List;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;

    @GetMapping
    public List<FaqResponse> findAllFaq(){
        return faqService.findAllFaq();
    }

    @GetMapping("/{id}")
    public FaqResponse findFaqById(@PathVariable Long id){
        return faqService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public FaqResponse saveFaq(@RequestBody FaqRequest request){
        return faqService.addFaq(request);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FaqResponse updateFaq(@PathVariable Long id, @RequestBody FaqRequest request){
        return faqService.updateFaqById(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFaq(@PathVariable Long id) {
        faqService.deleteFaqById(id);
        return("OK");
    }

}
