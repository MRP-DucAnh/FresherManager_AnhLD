package com.vmo.fresher.FresherManager_AnhLD.service;
import com.vmo.fresher.FresherManager_AnhLD.entity.Assignment;
import com.vmo.fresher.FresherManager_AnhLD.entity.AssignmentScore;
import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.repository.AssignmentScoreRepository;
import com.vmo.fresher.FresherManager_AnhLD.repository.CenterRepository;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import model.response.AssignmentScoreResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AssignmentScoreServiceTest {
    @Autowired
    private AssignmentScoreService assignmentScoreService;

    @MockBean
    AssignmentScoreRepository assignmentScoreRepository;
    @MockBean
    FresherRepository fresherRepository;

    private  List<Fresher> fresherList = new ArrayList<>();
    private List<AssignmentScore> list = new ArrayList<>();
 //   private List<Assignment>
    @BeforeEach
    void init(){
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        fresherList.add(johnFresher);
        Assignment test1 = Assignment.builder().id(1L).percentage(50).build();
        Assignment test2 = Assignment.builder().id(2L).percentage(30).build();
        Assignment test3 = Assignment.builder().id(3L).percentage(20).build();
        AssignmentScore assignmentScore1 = AssignmentScore.builder().id(1L).score(8).assignment(test1).fresher(johnFresher).build();
        AssignmentScore assignmentScore2 = AssignmentScore.builder().id(2L).score(8).assignment(test2).fresher(johnFresher).build();
        AssignmentScore assignmentScore3 = AssignmentScore.builder().id(3L).score(8).assignment(test3).fresher(johnFresher).build();
    list = List.of(assignmentScore1,assignmentScore2,assignmentScore3);
    }

    @Test
    @DisplayName("Test findAllAssignmentByFresherId Success")
    void findAllAssignmentByFresherIdSuccess(){
//        Fresher johnFresher = Fresher.builder()
//                .id(1L).name("John").address("123 Street").email("john@email.com")
//                .build();
      //  when(fresherRepository.findFresherById(anyLong())).thenReturn(List.of(johnFresher));
        when(assignmentScoreRepository.findAllByFresherId(anyLong())).thenReturn(list);
        List<AssignmentScoreResponse.ScoreResponse> returnList = assignmentScoreService.findAllAssignmentByFresherId(anyLong());
        assertNotNull(returnList,"No assignment score records found!");
        assertEquals(list.size(),returnList.size());
        assertEquals(list.get(0).getScore(),returnList.get(0).getScore());
    }
    @Test
    @DisplayName("Test findAllAssignmentByFresherId Fail")
    void findAllAssignmentByFresherIdFail(){
    //    when(fresherRepository.findFresherById(anyLong())).thenReturn(List.of());
       when(assignmentScoreRepository.findAllByFresherId(anyLong())).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () ->
               assignmentScoreService.findAllAssignmentByFresherId(anyLong())
        );
    }

    @Test
    @DisplayName("Test findAverageScoreByFresherId Success")
    void testFindAverageScoreByFresherIdSuccess(){
        when(fresherRepository.findFresherById(anyLong())).thenReturn(fresherList);
        when(assignmentScoreRepository.findAllByFresherId(anyLong())).thenReturn(list);
        ResponseObject returnList = assignmentScoreService.findAverageScoreByFresherId(anyLong());
        assertNotNull(returnList.getData(),"No assignment score records found!");
        assertEquals(HttpStatus.OK.toString(),returnList.getStatus());
        assertEquals("fresher Id exist !",returnList.getMessage());
    }

    @Test
    @DisplayName("Test findAverageScoreByFresherId Fail")
    void testFindAverageScoreByFresherIdFail(){
        when(fresherRepository.findFresherById(anyLong())).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () ->
                assignmentScoreService.findAverageScoreByFresherId(anyLong())
        );
    }

    @Test
    @DisplayName("Test findAllFresherByScore success")
    void testFindAllFresherByScoreSuccess(){
        int score =1;
     when(fresherRepository.findFresherById(1L)).thenReturn(fresherList);
     //   when(assignmentScoreRepository.findAllByFresherId(1L)).thenReturn(list);
        when(fresherRepository.findAll()).thenReturn(fresherList);
given(fresherRepository.findFresherById(1L).get(0).getAssignmentScores()).willReturn(list);

        ResponseObject returnObject = assignmentScoreService.findAllFresherByScore(score);
        assertNotNull(returnObject.getData(),"No fresher found !");
        assertEquals(HttpStatus.OK.toString(),returnObject.getStatus());
        assertEquals(1+" fresher have final score greater or equal: 1",returnObject.getMessage());


    }

    @Test
    @DisplayName("Test findAllFresherByScore Fail")
    void testFindAllFresherByScoreFail(int score){

    }


}
