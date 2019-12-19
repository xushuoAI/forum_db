package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "news_comment_reply")
@Data
public class NewsReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int replyType;

    private int replyId;

    private String toUserName;

    private String fromName;

    @Lob
    @Column(columnDefinition = "text")
    private String replyContent;
}
