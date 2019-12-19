package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class NewsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsCommentId;

    private Long newsId;

    private Long userId;

    @Lob
    @Column(columnDefinition = "text")
    private String newsCommentContent;

    private int toTop;

    private int newsCommentLike;

    private int newsCommentReply;

    private LocalDate newsCommentCreateTime;
}
