package com.vmo.fresher.FresherManager_AnhLD.service.impl;

import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.exception.ApiErrorDetail;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityAlreadyExistsException;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import model.request.FresherCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class FresherServiceImplTest {

    @Mock
    private FresherRepository fresherRepository;

    @InjectMocks
    private FresherServiceImpl fresherService;

//    @Test
//    @DisplayName("Should throw an exception when the email is already taken")
//    void createFresherWhenEmailIsAlreadyTakenThenThrowException() {
//        FresherCreateRequest fresherCreateRequest =
//                FresherCreateRequest.builder()
//                        .name("Nguyen Van A")
//                        .dob(LocalDate.of(2000, 1, 1))
//                        .address("Ha Noi")
//                        .phone("0987654321")
//                        .email("nguyenvana@gmail.com")
//                        .build();
//
//        Fresher fresher =
//                Fresher.builder()
//                        .name("Nguyen Van A")
//                        .dob(LocalDate.of(2000, 1, 1))
//                        .address("Ha Noi")
//                        .phone("0987654321")
//                        .email("nguyenvana@gmail.com")
//                        .build();
//
//        when(fresherRepository.save(any())).thenReturn(fresher);
//
//        verify(fresherRepository, times(1)).save(any());
//
//        throw new EntityAlreadyExistsException(ApiErrorDetail.builder()
//                .message("your database is empty No Fresher found")
//                .entityName("Fresher")
//                .fieldName("fresher")
//
//                .httpStatus(HttpStatus.NOT_FOUND)
//                .build());
////        assertThrows(
////                EntityAlreadyExistsException.class,
////                () -> fresherService.createFresher(fresherCreateRequest));
//
//
//    }
//
//    @Test
//    @DisplayName("Should save the fresher when the email is not taken")
//    void createFresherWhenEmailIsNotTaken() {
//        FresherCreateRequest fresherCreateRequest =
//                FresherCreateRequest.builder()
//                        .name("Nguyen Van A")
//                        .dob(LocalDate.of(2000, 1, 1))
//                        .address("Ha Noi")
//                        .phone("0987654321")
//                        .email("nguyenvana@gmail.com")
//                        .build();
//
//        Fresher fresher =
//                Fresher.builder()
//                        .name(fresherCreateRequest.getName())
//                        .dob(fresherCreateRequest.getDob())
//                        .address(fresherCreateRequest.getAddress())
//                        .phone(fresherCreateRequest.getPhone())
//                        .email(fresherCreateRequest.getEmail())
//                        .build();
//
//        when(fresherRepository.save(any(Fresher.class))).thenReturn(fresher);
//
//        Fresher result = fresherService.createFresher(fresherCreateRequest);
//
//        assertEquals(result, fresher);
//
//        verify(fresherRepository, times(1)).save(any(Fresher.class));
//    }
}