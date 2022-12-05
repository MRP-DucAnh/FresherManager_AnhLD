package com.vmo.fresher.FresherManager_AnhLD.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(name = "DELETED")
    protected boolean deleted = false;

    @Column(name = "CREATED_ON")
    protected LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "LAST_MODIFIED")
    protected LocalDateTime lastModified;
}