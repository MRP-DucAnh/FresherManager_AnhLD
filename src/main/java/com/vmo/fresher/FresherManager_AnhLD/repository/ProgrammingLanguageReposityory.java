package com.vmo.fresher.FresherManager_AnhLD.repository;

import com.vmo.fresher.FresherManager_AnhLD.entity.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammingLanguageReposityory extends JpaRepository<ProgrammingLanguage,Long> {
    List<ProgrammingLanguage> findAllByName (String name);
    ProgrammingLanguage findByName(String name);
}
