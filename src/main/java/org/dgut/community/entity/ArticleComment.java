package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long articleId;

    private Long userId;

    @Lob
    @Column(columnDefinition = "text")
    private String commentContent;

    private int isTop;

    private int commentLike;

    private int commentReply;

    private LocalDate commentCreateTime;
}
