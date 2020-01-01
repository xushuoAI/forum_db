package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article_comment_reply")
@Data
public class ArticleReply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int replyType;

    private String toUserName;

    private String fromUserName;

    @Lob
    @Column(columnDefinition = "text")
    private String replyContent;

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "commentId")
    private ArticleComment comment;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyCreateTime;

    @Transient
    private String userHeadImg;
}
