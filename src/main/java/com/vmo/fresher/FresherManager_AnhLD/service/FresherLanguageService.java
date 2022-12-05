package com.vmo.fresher.FresherManager_AnhLD.service;

import com.vmo.fresher.FresherManager_AnhLD.entity.FresherLanguage;
import model.request.FresherLanguageCreateRequest;
import model.response.FresherLanguageResponse;

import java.util.List;

public interface FresherLanguageService {
    FresherLanguage createFresherLanguage(FresherLanguageCreateRequest fresherLanguageCreateRequest);
    List<FresherLanguageResponse> findAllFresherByLanguage(String language);
     List<FresherLanguageResponse> findAllFresherByLanguageID(Long languageId) ;
}