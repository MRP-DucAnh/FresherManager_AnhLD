package com.vmo.fresher.FresherManager_AnhLD.service;

import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.FresherLanguage;
import com.vmo.fresher.FresherManager_AnhLD.entity.ProgrammingLanguage;

import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherLanguageRepository;

import model.response.FresherLanguageResponse;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FresherLanguageServiceTest {
    @Autowired
    private FresherLanguageService fresherLanguageService;

    @MockBean
    FresherLanguageRepository fresherLanguageRepository;

    @Test
    @DisplayName("Test findAllFresherByLanguage Success")
    void testFindAllFresherByLanguageSuccess(){

        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        ProgrammingLanguage java = ProgrammingLanguage.builder()
                .id(1L).name("java").description("OOP").build();
        List<FresherLanguage> fresherLanguage =List.of(new FresherLanguage(1L,java,johnFresher)) ;

        when(fresherLanguageRepository.findAllByLanguage_Name(anyString())).thenReturn(fresherLanguage);
        List<FresherLanguageResponse> ReturnList = fresherLanguageService.findAllFresherByLanguage("Java");
        assertNotNull(fresherLanguage,"Did not found any Fresher!");
        assertEquals(fresherLanguage.size(),ReturnList.size());
        assertEquals(fresherLanguage.get(0).getFresher().getName(),ReturnList.get(0).getFresherName());
    }

    @Test
    @DisplayName("Test findAllFresherByLanguage Fail")
    void testFindAllFresherByLanguageFail(){
        when(fresherLanguageRepository.findAllByLanguage_Name(anyString())).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () -> fresherLanguageService.findAllFresherByLanguage(anyString()));

    }

}
