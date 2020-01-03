package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "forum_news")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(nullable = false)
    private String newsTitle;

    private String newsReprinted;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    private String newsContent;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publicTime;

    private String newsPicture;

    private int newsLike;

    private int newsCollect;

    private int newsViewTimes;

    //private String newsAuthor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "adminId")
    @JsonIgnore
    private Admin admin;


    @OneToMany(mappedBy = "news")
    @JsonIgnore
    //@mappedBy注解的作用：在JPA中，在@OneToMany里加入mappedBy属性可以避免生成一张中间表。
    /*mappedBy：
    1>只有OneToOne，OneToMany，ManyToMany上才有mappedBy属性，ManyToOne不存在该属性；
    2>mappedBy标签一定是定义在被拥有方的，他指向拥有方；
    3>mappedBy的含义，应该理解为，拥有方能够自动维护跟被拥有方的关系，当然，如果从被拥有方，通过手工强行来维护拥有方的关系也是可以做到的；
    4>mappedBy跟joinColumn/JoinTable总是处于互斥的一方，可以理解为正是由于拥有方的关联被拥有方的字段存在，拥有方才拥有了被拥有方。mappedBy这方定义JoinColumn/JoinTable总是失效的，不会建立对应的字段或者表。 */
    private Set<NewsComment> comments;


}
