package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article_comment_reply")
@Data
public class ArticleReply implements Serializable {
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

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentId")
    private ArticleComment comment;
}
