package com.vmo.fresher.FresherManager_AnhLD.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CENTER")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Center extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private List<CenterFresher> centerFresherList;


}

//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Table(name = "CENTER")
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Center extends BaseEntity{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Long id;
//
//    @Column(name = "NAME", nullable = false)
//    private String name;
//
//    @Column(name = "CODE")
//    private String code;
//
//    @Column(name = "DOB", nullable = false)
//    private LocalDate dob;
//
//    @Column(name = "ADDRESS", nullable = false)
//    private String address;
//
//    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
//    private List<CenterFresher> centerFresherList;
//
//}