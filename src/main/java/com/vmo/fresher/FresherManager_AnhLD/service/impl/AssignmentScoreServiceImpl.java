package com.vmo.fresher.FresherManager_AnhLD.service.impl;

import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.exception.ApiErrorDetail;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.exception.OutOfRangeExceptions;
import com.vmo.fresher.FresherManager_AnhLD.repository.AssignmentScoreRepository;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.AssignmentScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.response.AssignmentScoreResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignmentScoreServiceImpl implements AssignmentScoreService {

    private final AssignmentScoreRepository assignmentScoreRepository;
    private final FresherRepository fresherRepository;
    @Override
    public ResponseObject findAverageScoreByFresherId(Long id) {
        //if (assignmentScoreRepository.findAllByFresherId(id).isEmpty()) throw

        List<AssignmentScoreResponse.ScoreResponse> list = assignmentScoreRepository.findAllByFresherId(id).stream()
                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
                        .assignmentId(assignmentScore.getAssignment().getId())
                        //      .fresherId(assignmentScore.getFresher().getId())
                        //            .fresherName(assignmentScore.getFresher().getName())
                        .score(assignmentScore.getScore())
                        .percentage(assignmentScore.getAssignment().getPercentage()).build()).collect(Collectors.toList());

        List<AssignmentScoreResponse> listR = fresherRepository.findFresherById(id).stream()
                .map(fresher -> AssignmentScoreResponse.builder()
                        .fresherName(fresher.getName())
                        .fresherId(fresher.getId())
                        .avgScore(finalScore(id))
                        .scoreResponseList(list).build()).collect(Collectors.toList());
        if (fresherRepository.findFresherById(id).isEmpty())throw new EntityNotFoundException(ApiErrorDetail.builder()
                .message("No assignment score records found!")
                .entityName("assignment score")
                .fieldName("Id")
                .fieldValue(id)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
        return new ResponseObject(HttpStatus.OK.toString(), "fresher Id exist !" ,listR);

    }

    @Override
    public List<AssignmentScoreResponse.ScoreResponse> findAllAssignmentByFresherId(Long id) {

        List<AssignmentScoreResponse.ScoreResponse> list =
         assignmentScoreRepository.findAllByFresherId(id).stream()
                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
                        .assignmentId(assignmentScore.getAssignment().getId())
                  //      .fresherId(assignmentScore.getFresher().getId())
                  //      .fresherName(assignmentScore.getFresher().getName())
                        .score(assignmentScore.getScore())
                        .percentage(assignmentScore.getAssignment().getPercentage()).build()).collect(Collectors.toList());

        if(list.isEmpty()) throw new EntityNotFoundException(ApiErrorDetail.builder()
                .message("No assignment score records found!")
                .entityName("assignment score")
                .fieldName("Id")
                .fieldValue(id)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
        else
        return list;


////                .assignmentId(assignmentScore.getAssignmentId())
////                        .fresherName(assignmentScore.getFresherName())
////                        .score(assignmentScore.getScore())
////                        .percentage(assignmentScore.getPercentage())
////                        .build()).collect(Collectors.toList());
//
    }

    @Override
    public ResponseObject findAllFresherByScore(int score) {
//        if(score>10 || score<=0 ) throw new OutOfRangeExceptions(ApiErrorDetail.builder()
//                .message("Invalid input score! - must be in range {0,10}")
//                .entityName("assignment score")
//                .fieldName("Score")
//                .fieldValue(score)
//                .httpStatus(HttpStatus.NOT_FOUND)
//                .build());
        List<AssignmentScoreResponse> list2 =  fresherRepository.findAll().stream()
                .filter(fresher -> !fresher.getAssignmentScores().isEmpty())
                .map(fresher -> AssignmentScoreResponse.builder()
                        .fresherId(fresher.getId())
                        .fresherName(fresher.getName())
        .scoreResponseList(findAllAssignmentByFresherId(fresher.getId()))
         .avgScore(finalScore(fresher.getId())) .build()).collect(Collectors.toList());



        list2 = list2.stream().filter(fresher -> fresher.getAvgScore() >=score).collect(Collectors.toList());
    long count =  list2.size();

        return new ResponseObject(HttpStatus.OK.toString(), count+" fresher have final score greater or equal: " + score,list2);
    }


    @Override
    public Integer finalScore(Long fresherId) {

        List<AssignmentScoreResponse.ScoreResponse> list = assignmentScoreRepository.findAllByFresherId(fresherId).stream()
                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
                        .assignmentId(assignmentScore.getAssignment().getId())
                     //   .fresherId(assignmentScore.getFresher().getId())
                    //    .fresherName(  assignmentScore.getFresher().getName())
                        .score(assignmentScore.getScore())
                        .percentage(assignmentScore.getAssignment().getPercentage()).build()).collect(Collectors.toList());
        double finalScore = 0.0;
        for(AssignmentScoreResponse.ScoreResponse assScore : list){
            finalScore += assScore.getScore().doubleValue() * assScore.getPercentage().doubleValue() /100;
        }
        return (int) finalScore;
    }
}