package com.vmo.fresher.FresherManager_AnhLD.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ASSIGNMENT")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assignment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Min(0)
    @Max(100)
    @Column(name = "PERCENTAGE", nullable = false)
    private Integer percentage;

    @Column(name = "DESCRIPTION")
    private String description;


}

//import lombok.*;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Data
//@Setter
//@Getter
//@Table (name = "ASSIGNMENT")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Assignment extends BaseEntity {
//
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Long id;
//    @Column(name = "PERCENTAGE", nullable = false)
//    private int percentage;
//    @Column(name = "NAME", nullable = false)
//    private  String name;
//    @Column(name = "DESCRIPTION", nullable = false)
//    private  String description;
//
//    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY)
//    private List<Assignment_Score> Assignment_ScoreList;
//
//
//}
