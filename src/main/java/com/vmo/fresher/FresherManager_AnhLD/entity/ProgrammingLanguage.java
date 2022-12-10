package com.vmo.fresher.FresherManager_AnhLD.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@Table(name = "PROGRAMMING_LANGUAGE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgrammingLanguage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private  String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private  String description;


    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private List<FresherLanguage> Fresher_LanguageList;


}
