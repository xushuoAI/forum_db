package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class ArticleComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articleId")
    private FourmArticle article;

    private Long userId;

    @Lob
    @Column(columnDefinition = "text")
    private String commentContent;

    private int isTop;

    private int commentLike;

    private int commentReply;

    private LocalDate commentCreateTime;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private Set<ArticleReply> replies;
}
