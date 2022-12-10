package com.vmo.fresher.FresherManager_AnhLD.service.impl;


import com.vmo.fresher.FresherManager_AnhLD.entity.FresherLanguage;
import com.vmo.fresher.FresherManager_AnhLD.exception.ApiErrorDetail;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherLanguageRepository;


import com.vmo.fresher.FresherManager_AnhLD.service.FresherLanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.FresherLanguageCreateRequest;
import model.response.FresherLanguageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FresherLanguageServiceImpl implements FresherLanguageService {

    private final FresherLanguageRepository fresherLanguageRepository;


    @Transactional
    @Override
    public FresherLanguage createFresherLanguage(FresherLanguageCreateRequest fresherLanguageCreateRequest) {
        return null;
    }

    @Override
    public List<FresherLanguageResponse> findAllFresherByLanguage(String languageName) {
      //  List<ProgrammingLanguage> prL = programmingLanguageReposityory.findAllByName(languageName);
        List<FresherLanguageResponse> list =
         fresherLanguageRepository.findAllByLanguage_Name(languageName).stream()
                .map(fresherLanguage -> FresherLanguageResponse.builder()
                        .languageId(fresherLanguage.getLanguage().getId())
                        .languageName(fresherLanguage.getLanguage().getName())
                        .fresherId(fresherLanguage.getFresher().getId())
                        .fresherName(fresherLanguage.getFresher().getName())
                        .build())
                .collect(Collectors.toList());
    if(list.isEmpty()) throw new EntityNotFoundException(ApiErrorDetail.builder()
            .message("No programing language matched !")
            .entityName("Fresher Language")
            .fieldName("Language")
            .fieldValue(languageName)
            .httpStatus(HttpStatus.NOT_FOUND)
            .build());
    else  return list;
        //        ProgrammingLanguage prL = programmingLanguageReposityory.findByName(languageName);
//        return fresherLanguageRepository.findAllById(prL.getId()).stream()
//                .map(fresherLanguage -> FresherLanguageResponse.builder()
//                        .languageId(fresherLanguage.getLanguage().getId())
//                        .languageName(fresherLanguage.getLanguage().getName())
//                        .fresherId(fresherLanguage.getFresher().getId())
//                        .fresherName(fresherLanguage.getFresher().getName())
//                        .build())
//                .collect(Collectors.toList());

    }

    @Override
    public List<FresherLanguageResponse> findAllFresherByLanguageID(Long languageId) {
        return fresherLanguageRepository.findFresherLanguageById(languageId).stream()
                .map(fresherLanguage -> FresherLanguageResponse.builder()
                        .languageId(fresherLanguage.getLanguage().getId())
                        .languageName(fresherLanguage.getLanguage().getName())
                        .fresherId(fresherLanguage.getFresher().getId())
                        .fresherName(fresherLanguage.getFresher().getName())
                        .build())
                .collect(Collectors.toList());

    }

}
