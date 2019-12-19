package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(unique = true, nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String adminPassword;

    private int adminPhone;

    private String adminEmail;

    @Column(nullable = false)
    private String roleName;

    @Column(nullable = false)
    private int adminStatus;

    private LocalDate adminCreateTime;
}
