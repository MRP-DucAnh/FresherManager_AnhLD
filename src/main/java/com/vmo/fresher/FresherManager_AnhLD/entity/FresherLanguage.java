package com.vmo.fresher.FresherManager_AnhLD.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FRESHER_LANGUAGE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FresherLanguage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID", nullable = false)
    private ProgrammingLanguage language;

    @ManyToOne
    @JoinColumn(name = "FRESHER_ID", nullable = false)
    private Fresher fresher;

}

//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Setter
//@Getter
//@Table(name = "FRESHER_LANGUAGE")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Fresher_Language extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Long id;
//    @Column(name = "FRESHER_ID", nullable = false)
//    private Long fresher_id;
//    @Column(name = "LANGUAGE_ID", nullable = false)
//    private Long language_id;
//    @Column(name = "ADDRESS", nullable = false)
//    private String address;
//    @Column(name = "PHONE", nullable = false)
//    private String phone;
//    @Column(name = "EMAIL", nullable = false)
//    private  String email;
//
//
//
//    @ManyToOne
//    @JoinColumn(name = "FRESHER_ID")
//    private Fresher fresher;
//
//    @ManyToOne
//    @JoinColumn(name = "CENTER_ID")
//    private Programming_Language language;
//}
