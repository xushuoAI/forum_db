package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "article_comment_reply")
@Data
public class ArticleReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int replyType;

    private Long replyId;

    private String toUserName;

    private String fromUserName;

    @Lob
    @Column(columnDefinition = "text")
    private String replyContent;

}
