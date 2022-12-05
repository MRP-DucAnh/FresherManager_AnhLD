package com.vmo.fresher.FresherManager_AnhLD.service.impl;

import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.repository.AssignmentScoreRepository;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.AssignmentScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.response.AssignmentScoreResponse;
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
    public ResponseObject averageScore(Long id) {
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
        return new ResponseObject("fresher Id exist !","" ,listR);
//        List<AssignmentScoreResponse.ScoreResponse> list = assignmentScoreRepository.findAllByFresherId(fresherId).stream()
//                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
//                        .assignmentId(assignmentScore.getAssignment().getId())
//                   //     .fresherId(assignmentScore.getFresher().getId())
//               //         .fresherName(  assignmentScore.getFresher().getName())
//                        .score(assignmentScore.getScore())
//                        .percentage(assignmentScore.getAssignment().getPercentage()).build()).collect(Collectors.toList());
//        Double finalScore = 0.0;
//        for(AssignmentScoreResponse.ScoreResponse assScore : list){
//            finalScore += assScore.getScore().doubleValue() * assScore.getPercentage().doubleValue() /100;
//        }
//        return new ResponseObject("fresher Id exist !","Final Score: " + finalScore ,list);
    }

    @Override
    public List<AssignmentScoreResponse.ScoreResponse> findAllAssignmentByFresherId(Long id) {

        return assignmentScoreRepository.findAllByFresherId(id).stream()
                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
                        .assignmentId(assignmentScore.getAssignment().getId())
                  //      .fresherId(assignmentScore.getFresher().getId())
                  //      .fresherName(assignmentScore.getFresher().getName())
                        .score(assignmentScore.getScore())
                        .percentage(assignmentScore.getAssignment().getPercentage()).build()).collect(Collectors.toList());





////                .assignmentId(assignmentScore.getAssignmentId())
////                        .fresherName(assignmentScore.getFresherName())
////                        .score(assignmentScore.getScore())
////                        .percentage(assignmentScore.getPercentage())
////                        .build()).collect(Collectors.toList());
//
    }

    @Override
    public ResponseObject findAllFresherByScore(int score) {
        List<AssignmentScoreResponse> list =  fresherRepository.findAll().stream()
                .map(fresher -> AssignmentScoreResponse.builder()
                        .fresherId(fresher.getId())
                        .fresherName(fresher.getName())
        .scoreResponseList(findAllAssignmentByFresherId(fresher.getId()))
         .avgScore(finalScore(fresher.getId())) .build()).collect(Collectors.toList());

//        List<AssignmentScoreResponse> list =  assignmentScoreRepository.findAll().stream()
//                .map(assignmentScore -> AssignmentScoreResponse.builder()
//                        .fresherId(assignmentScore.getFresher().getId())
//                        .fresherName(assignmentScore.getFresher().getName())
//                        .scoreResponseList(findAllAssignmentByFresherId(assignmentScore.getFresher().getId()))
//                        .avgScore(finalScore(assignmentScore.getId())) .build()).collect(Collectors.toList());

        list = list.stream().filter(fresher -> fresher.getAvgScore() >=score).collect(Collectors.toList());
    long count =  list.stream().count();

        return new ResponseObject("Found: " + count,count+" fresher have final score greater or equal: " + score,list);
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
        Double finalScore = 0.0;
        for(AssignmentScoreResponse.ScoreResponse assScore : list){
            finalScore += assScore.getScore().doubleValue() * assScore.getPercentage().doubleValue() /100;
        }
        return finalScore.intValue();
    }
}