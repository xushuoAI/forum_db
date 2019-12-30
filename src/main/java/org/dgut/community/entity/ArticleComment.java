package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ArticleComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "articleId")
    private FourmArticle article;

    private Long userId;

    @Lob
    @Column(columnDefinition = "text")
    private String commentContent;

    private int isTop;

    private int commentLike;

    private int commentReply;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentCreateTime;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private Set<ArticleReply> replies;

    @Transient
    private String userName;

    @Transient
    private String userHeadImg;
}
