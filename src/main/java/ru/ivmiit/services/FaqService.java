package ru.ivmiit.services;

import ru.ivmiit.dto.request.FaqRequest;
import ru.ivmiit.dto.response.FaqResponse;

import java.util.List;

public interface FaqService {

    List<FaqResponse> findAllFaq();

    FaqResponse findById(Long id);

    FaqResponse addFaq(FaqRequest faqRequest);

    FaqResponse updateFaqById(Long id, FaqRequest faqRequest);

    void deleteFaqById(Long id);

}
