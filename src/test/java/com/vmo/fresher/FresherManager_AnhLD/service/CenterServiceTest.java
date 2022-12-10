package com.vmo.fresher.FresherManager_AnhLD.service;

import com.vmo.fresher.FresherManager_AnhLD.entity.Center;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.repository.CenterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CenterServiceTest {
    @Autowired
    private CenterService centerService;

    @MockBean
    CenterRepository centerRepository;

    @Test
    @DisplayName("Test findCenterByID Success")
    void testFindCenterByIdSuccess(){
        Center c3nter = Center.builder().id(1L).name("Center 1")
                .address("Ton That Thuyet")
                .build();
        when(centerRepository.findById(1L)).thenReturn(Optional.of(c3nter));

        ResponseObject returnCenter = centerService.findByID(1L);
        assertNotNull(returnCenter.getData(),"Center was not found!");
        assertEquals(HttpStatus.OK.toString(),returnCenter.getStatus());
        assertEquals("Center does exist !",returnCenter.getMessage());
    }

    @Test
    @DisplayName("Test findCenterById Fail")
    void testFindByCenterIdFail() {
       when(centerRepository.findById(anyLong())).thenReturn(Optional.empty());
       assertThrows(EntityNotFoundException.class,()-> centerService.findByID(anyLong()));

    }

    @Test
    @DisplayName("Test findAllCenters Success")
    void testFindAllCenter(){
        Center c3nter = Center.builder().id(1L).name("Center 1")
                .address("Ton That Thuyet")
                .build();
        Center c3nter1 = Center.builder().id(2L).name("Center 1")
                .address("Ton That Thuyet")
                .build();
        List<Center> list = new ArrayList<>();
        list.add(c3nter);
        list.add(c3nter1);

        when(centerRepository.findAll()).thenReturn(list);
        List<String> returnedCenter = centerService.findAllCenters();

        assertEquals(list.stream().map(Center::getName).collect(Collectors.toList()),returnedCenter);
    }


}
