package com.vmo.fresher.FresherManager_AnhLD.repository;

import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Long> {
    List<Fresher> findFresherByNameContaining(String fresherName);
    List<Fresher> findFresherByEmailContaining(String fresherEmail);
    List<Fresher> findFresherById(Long id);

}
