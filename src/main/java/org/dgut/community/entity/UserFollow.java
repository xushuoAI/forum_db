package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    private Long starId;

    private Long fansId;
}
