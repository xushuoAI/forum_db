package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userHeadingImg;

    private int userFocus;

    private int userFollow;

    private int userArticles;

    @Column(unique = true)
    private String userOpenId;
}
