package ru.ivmiit.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.request.FaqRequest;
import ru.ivmiit.dto.response.FaqResponse;
import ru.ivmiit.exceptions.faq.FaqNotFoundException;
import ru.ivmiit.mappers.FaqMapper;
import ru.ivmiit.models.FaqEntity;
import ru.ivmiit.repositories.FaqRepository;
import ru.ivmiit.repositories.UserRepository;
import ru.ivmiit.services.FaqService;

import java.io.NotActiveException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private final FaqMapper faqMapper;

    @Override
    public List<FaqResponse> findAllFaq() {
        return faqRepository.findAll().stream().map(faqMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public FaqResponse findById(Long id) {
        return faqMapper.toResponse(faqRepository.findById(id).orElseThrow(FaqNotFoundException::new));
    }

    @Override
    public FaqResponse addFaq(FaqRequest faqRequest) {
        return faqMapper.toResponse(faqRepository.save(faqMapper.toEntity(faqRequest)));
    }

    @Override
    public FaqResponse updateFaqById(Long id, FaqRequest faqRequest) {
        FaqEntity faq = faqRepository.findById(id).orElseThrow(FaqNotFoundException::new);
        faq.setQuestion(faqRequest.getQuestion());
        faq.setAnswer(faqRequest.getAnswer());
        return faqMapper.toResponse(faqRepository.save(faq));
    }

    @Override
    public void deleteFaqById(Long id) {
        findById(id);
        faqRepository.deleteById(id);
    }
}
