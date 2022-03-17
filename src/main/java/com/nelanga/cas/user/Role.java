package com.nelanga.cas.user;

import com.nelanga.cas.commons.RoleType;

import javax.persistence.*;

@Entity
@Table(schema = "auth", name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleType roleName;
    private String description;
}
