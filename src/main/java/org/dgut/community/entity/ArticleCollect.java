package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class ArticleCollect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectId;

    private Long articleId;

    private Long userId;
}
