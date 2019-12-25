package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class NewsComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsCommentId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "newsId")
    private News news;

    private Long userId;

    @Lob
    @Column(columnDefinition = "text")
    private String newsCommentContent;

    private int toTop;

    private int newsCommentLike;

    private int newsCommentReply;

    private LocalDate newsCommentCreateTime;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private Set<NewsReply> replies;
}
