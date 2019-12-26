package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class FourmArticle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    private String articleContent;

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private User user;

    private LocalDate articlePublicTime;

    private int articleLike;

    private int articleCollect;

    private String articlePhoto;

    private int articleCommentTimes;

    @Transient
    private int isLike;

    @Transient
    private int collect;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "article")
    private Set<ArticleComment> comments;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "article")
    private Set<ArticleLike> likes;
}
