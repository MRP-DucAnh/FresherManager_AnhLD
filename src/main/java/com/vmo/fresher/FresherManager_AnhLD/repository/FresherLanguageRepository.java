package com.vmo.fresher.FresherManager_AnhLD.repository;

import com.vmo.fresher.FresherManager_AnhLD.entity.FresherLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FresherLanguageRepository extends JpaRepository<FresherLanguage,Long> {
    long deleteByFresherId(Long id);
    List<FresherLanguage> findAllByLanguage(String language);
    List<FresherLanguage> findFresherLanguageById(Long id);
    List<FresherLanguage> findAllByLanguage_Name(String language);
}