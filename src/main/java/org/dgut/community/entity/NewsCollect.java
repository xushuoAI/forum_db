package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class NewsCollect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsCollectId;

    private Long newsId;

    private Long userId;
}
