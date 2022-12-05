package com.vmo.fresher.FresherManager_AnhLD.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ASSIGNMENT_SCORE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentScore extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FRESHER_ID", nullable = false)
    private Fresher fresher;

    @ManyToOne
    @JoinColumn(name = "ASSIGNMENT_ID", nullable = false)
    private Assignment assignment;

    @Min(0) @Max(10)
    @Column(name = "SCORE", nullable = false)
    private Integer score;
}
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Setter
//@Getter
//@Table(name = "ASSIGNMENT_SCORE")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Assignment_Score extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Long id;
//    @Column(name = "ASSIGNMENT_ID", nullable = false)
//    private Long assighnment_id;
//    @Column(name = "FRESHER_ID", nullable = false)
//    private Long fresher_id;
//    @Column(name = "SCORE", nullable = false)
//    private int score;
//
//    @ManyToOne
//    @JoinColumn(name = "ASSIGNMENT_ID")
//    private Assignment assignment;
//    @ManyToOne
//    @JoinColumn(name = "FRESHER_ID")
//    private Fresher fresher;
//}
