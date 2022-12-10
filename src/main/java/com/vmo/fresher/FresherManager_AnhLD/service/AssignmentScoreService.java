package com.vmo.fresher.FresherManager_AnhLD.service;

import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import model.response.AssignmentScoreResponse;

import java.util.List;

public interface AssignmentScoreService {
    ResponseObject findAverageScoreByFresherId(Long id);

    List<AssignmentScoreResponse.ScoreResponse> findAllAssignmentByFresherId(Long id);

    ResponseObject findAllFresherByScore(int socre);

    Integer finalScore(Long fresherId);
}
