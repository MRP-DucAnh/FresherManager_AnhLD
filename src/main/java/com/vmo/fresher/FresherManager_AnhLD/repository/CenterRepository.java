package com.vmo.fresher.FresherManager_AnhLD.repository;


import com.vmo.fresher.FresherManager_AnhLD.entity.Center;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
}