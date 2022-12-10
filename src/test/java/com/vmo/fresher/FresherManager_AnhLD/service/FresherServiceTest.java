package com.vmo.fresher.FresherManager_AnhLD.service;


import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.FresherService;
import com.vmo.fresher.FresherManager_AnhLD.service.impl.FresherServiceImpl;
import model.request.FresherCreateRequest;
import model.response.FresherResponse;
import model.response.FresherReturnResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class FresherServiceTest {

    @Autowired
    private FresherService fresherService;

    @MockBean
    FresherRepository fresherRepository;




    @Test
    @DisplayName("Test findById Success")
    void testFindByIdSuccess() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();

        // Return the mocked data when retrieving
        when(fresherRepository.findById(1L)).thenReturn(Optional.of(johnFresher));

        //  Execute the service call
        FresherResponse returnedFresher = fresherService.findById(1L);

        assertNotNull(returnedFresher, "Fresher was not found!");
        assertEquals(johnFresher.getName(), returnedFresher.fresherName());
        assertEquals(johnFresher.getId(), returnedFresher.fresherId());
    }

    @Test
    @DisplayName("Test findById Fail")
    void testFindByIdFail() {
        // Return the mocked data when retrieving
        when(fresherRepository.findById(anyLong())).thenReturn(Optional.empty());

        //  Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.findById(1L);
        });
    }

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

    @Test
    @DisplayName("Test findByName Success")
    void testFindByNameSuccess() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        List<Fresher> list2 = new ArrayList<>();
        list2.add(johnFresher);

        FresherReturnResponse johnFresherRes = FresherReturnResponse.builder().id(johnFresher.getId()).name(johnFresher.getName())
                .address(johnFresher.getAddress()).email(johnFresher.getEmail())
                .build();
        List<FresherReturnResponse> list = new ArrayList<>();
        list.add(johnFresherRes);
        // Return the mocked data when retrieving
        when(fresherRepository.findFresherByNameContaining("J")).thenReturn(list2);

        //  Execute the service call
        ResponseObject returnedList = fresherService.findByName("J");

       assertNotNull(returnedList.getData(), "Fresher was not found!");
        assertEquals(HttpStatus.FOUND.toString(),returnedList.getStatus());
        assertEquals("Found "+list2.size()+" freshers contain \"" + "J"+"\"",returnedList.getMessage());

    }

    @Test
    @DisplayName("Test findByName Fail")
    void testFindByNameFail() {

        // Return the mocked data when retrieving
        when(fresherRepository.findFresherByNameContaining(anyString())).thenReturn(List.of());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.findByName(anyString());
        });

    }

    @Test
    @DisplayName("Test findByEmail Success")
    void testFindByEmailSuccess() {

        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        List<Fresher> list2 = new ArrayList<>();
        list2.add(johnFresher);

//        FresherReturnResponse johnFresherRes = FresherReturnResponse.builder().id(johnFresher.getId()).name(johnFresher.getName())
//                .address(johnFresher.getAddress()).email(johnFresher.getEmail())
//                .build();
//        List<FresherReturnResponse> list = new ArrayList<>();
//        list.add(johnFresherRes);
        // Return the mocked data when retrieving
        when(fresherRepository.findFresherByEmailContaining("J")).thenReturn(list2);

        //  Execute the service call
        ResponseObject returnedList = fresherService.findByEmail("J");

        assertNotNull(returnedList.getData(), "Fresher was not found!");
        assertEquals(HttpStatus.FOUND.toString(),returnedList.getStatus());
        assertEquals("Found "+list2.size()+" fresher's emails contain \"" + "J"+"\"",returnedList.getMessage());
    }

    @Test
    @DisplayName("Test findByEmail Fail")
    void testFindByEmailFail() {

        // Return the mocked data when retrieving
        when(fresherRepository.findFresherByEmailContaining(anyString())).thenReturn(List.of());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.findByEmail("lmao@gmaill.com");
        });
    }

    @Test
    @DisplayName("Test update Successes")
    void  testUpdateFresherSuccess() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        Fresher johnsonFresher = Fresher.builder()
                .id(1L).name("John Son").address("123 Street").email("johnson@email.com")
                .build();
    given(fresherRepository.save(johnFresher)).willReturn(johnFresher);

        when(fresherRepository.findById(1L)).thenReturn(Optional.of(johnFresher));
        ResponseObject returnFresherUpdate = fresherService.UpdateFresher(johnsonFresher,1L);



        assertNotNull(returnFresherUpdate.getData(), "Fresher was not found!");
       assertEquals("Update Fresher successfully",returnFresherUpdate.getMessage());
       assertEquals(HttpStatus.OK.toString(),returnFresherUpdate.getStatus());
       assertNotEquals(johnFresher,fresherRepository.findById(1L));

    }

    @Test
    @DisplayName("Test update fail")
    void  testUpdateFresherFail() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        when(fresherRepository.findById(1L)).thenReturn(Optional.empty());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.UpdateFresher(johnFresher,1L);
        });
    }



//    @Test
//    @DisplayName("Test findByEmail Success")
//    void testFindByEmailSuccess() {
//        Fresher johnFresher = Fresher.builder()
//                .id(1L).name("John").address("123 Street").email("john1@email.com")
//                .build();
//        Fresher punFresher = Fresher.builder()
//                .id(2L).name("Pun").address("1234 Street").email("pun1@email.com")
//                .build();
//        Fresher jerryFresher = Fresher.builder()
//                .id(3L).name("Jerry").address("12345 Street").email("jerry2@email.com")
//                .build();
//        List<Fresher> list = new ArrayList<>();
//        list.add(johnFresher);
//
//
//        // Return the mocked data when retrieving
//        when(fresherRepository.findFresherByEmailContaining("1@gmail.com")).thenReturn(list);
//
//        //  Execute the service call
//        ResponseObject returnedList = fresherService.findByEmail("1@gmail.com");
//
//        assertNotNull(returnedList, "Fresher was not found!");
//        assertEquals("found",returnedList.getStatus());
//        assertEquals(list,returnedList.getData());
//    }
}