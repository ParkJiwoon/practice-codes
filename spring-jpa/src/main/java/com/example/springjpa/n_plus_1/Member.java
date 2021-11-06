package com.example.springjpa.n_plus_1;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "fromMember")
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "toMember")
    private List<Follow> followers = new ArrayList<>();

    public Member(String email) {
        this.email = email;
    }
}