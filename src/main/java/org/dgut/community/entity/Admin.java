package org.dgut.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "forum_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Integer adminId;

    @Column(unique = true, nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String adminPassword;

    private String adminPhone;

    @Email
    private String adminEmail;

    @Column(nullable = false)
    private String roleName;

    @Column(nullable = false)
    private int adminStatus;

    private LocalDate adminCreateTime;





}
