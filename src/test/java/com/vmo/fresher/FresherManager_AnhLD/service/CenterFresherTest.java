package com.vmo.fresher.FresherManager_AnhLD.service;
import com.vmo.fresher.FresherManager_AnhLD.entity.Center;
import com.vmo.fresher.FresherManager_AnhLD.entity.CenterFresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;

import com.vmo.fresher.FresherManager_AnhLD.repository.CenterFreshRepository;

import model.response.CenterFresherResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;

import java.util.List;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CenterFresherTest {
    @Autowired
    private CenterFresherService centerFresherService;

    @MockBean
    CenterFreshRepository centerFreshRepository;

    @Test
    @DisplayName("Test findAllFreshersByCenterId Success")
    void testFindAllFreshersByCenterIdSuccess(){
        Center c3nter = Center.builder().id(1L).name("Center 1")
                .address("Ton That Thuyet")
                .build();
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        List<CenterFresher> list = List.of(new CenterFresher(1L,c3nter,johnFresher,null,null));
        when(centerFreshRepository.findAllByCenterId(1L)).thenReturn(list);
        List<CenterFresherResponse> returnList =  centerFresherService.findAllFreshersByCenterId(1L);
        assertNotNull(returnList,"Did not found any Fresher!");
        assertEquals(list.size(),returnList.size());
        assertEquals(list.get(0).getCenter().getName(),returnList.get(0).getCenterName());
    }

    @Test
    @DisplayName("Test findAllFresherByCenterID Fail")
    void  testFindAllFresherByCenterIdFail(){
        when(centerFreshRepository.findAllByCenterId(anyLong())).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () ->
            centerFresherService.findAllFreshersByCenterId(anyLong())
        );
    }
}
