package com.vmo.fresher.FresherManager_AnhLD.controller;

import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.FresherService;
import com.vmo.fresher.FresherManager_AnhLD.service.impl.FresherServiceImpl;
import model.request.FresherCreateRequest;
import model.response.FresherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
class FresherControllerTest {
//    @Mock
//    private FresherRepository fresherRepository;

//    @InjectMocks
//    private FresherService fresherService  ;
@Autowired
private FresherService fresherService;

    @MockBean
    FresherRepository fresherRepository;

    @BeforeEach
    public void beforeEach() {
        List<Fresher> freshers = new ArrayList<>();
      //  Fresher a = new Fresher(1L,"pear",LocalDate.of(2022,05,20),"abc","0987654321","john@gmail.com");
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        Fresher johnsonFresher = Fresher.builder()
                .id(1L).name("John Son").address("123 Street").email("johnson@email.com")
                .build();
        Fresher MichaelFresher = Fresher.builder()
                .id(1L).name("Michael").address("123 Street").email("Michaelj@email.com")
                .build();
        freshers.add(johnFresher);
        freshers.add(johnsonFresher);
        freshers.add(MichaelFresher);
       //  freshers.add();
        Mockito.when(fresherRepository.findAll()).thenReturn(freshers);
    }

    @Test
    @DisplayName("Test find by Name Success")
    void  testFindByNameSuccess()  {
//        Fresher johnFresher = Fresher.builder()
//                .id(1L).name("John").address("123 Street").email("john@email.com")
//                .build();
//        List<Fresher> list  = List.of(johnFresher);
//        when(fresherRepository.findAll()).thenReturn(list);
        //  Execute the service call
        ResponseObject returnRes = fresherService.findByName("John");
//        assertEquals("found",returnRes.getStatus());
//        assertNotNull(returnRes.getData());

    }

//    @Mock
//    private FresherService fresherService;
//
//    @InjectMocks
//    private FresherController fresherController;

//    @Test
//    @DisplayName("Should return 400 when the email is already taken")
//    void createFresherWhenEmailIsAlreadyTakenThenReturn400() {
//        FresherCreateRequest fresherCreateRequest =
//                FresherCreateRequest.builder()
//                        .name("Nguyen Van A")
//                        .dob(LocalDate.of(2000, 1, 1))
//                        .address("Ha Noi")
//                        .phone("0987654321")
//                        .email("nguyenvana@gmail.com")
//                        .build();
//
//        when(fresherService.createFresher(fresherCreateRequest)).thenReturn(null);
//
//        ResponseEntity<Fresher> responseEntity =
//                fresherController.createFresher(fresherCreateRequest);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Should return 201 when the fresher is created")
//    void createFresherWhenFresherIsCreatedThenReturn201() {
//        FresherCreateRequest fresherCreateRequest =
//                FresherCreateRequest.builder()
//                        .name("Nguyen Van A")
//                        .dob(LocalDate.of(2000, 1, 1))
//                        .address("Ha Noi")
//                        .phone("0987654321")
//                        .email("nguyenvana@gmail.com")
//                        .build();
//
//        when(fresherService.createFresher(fresherCreateRequest))
//                .thenReturn(Fresher.builder().build());
//
//        ResponseEntity<Fresher> responseEntity =
//                fresherController.createFresher(fresherCreateRequest);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//    }
}