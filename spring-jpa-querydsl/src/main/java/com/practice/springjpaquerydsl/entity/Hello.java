package com.practice.springjpaquerydsl.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Hello {
    @Id @GeneratedValue
    private Long id;
}
