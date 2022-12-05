package com.vmo.fresher.FresherManager_AnhLD.repository;

import com.vmo.fresher.FresherManager_AnhLD.entity.AssignmentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentScoreRepository extends JpaRepository<AssignmentScore,Long>{
    List<AssignmentScore> findAllByFresherId(Long fresherId);



}