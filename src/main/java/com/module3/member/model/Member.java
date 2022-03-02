package com.module3.member.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private String userEmail;
    private int birthYear;

    @Builder
    public Member(Long id, String name, String gender, String userEmail, int birthYear) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.userEmail = userEmail;
        this.birthYear = birthYear;
    }
}
