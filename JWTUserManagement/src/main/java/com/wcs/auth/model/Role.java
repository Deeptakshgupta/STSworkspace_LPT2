//package com.wcs.auth.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import javax.persistence.*;
////import java.io.Serializable;
//import java.util.Set;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "role")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable=false, unique=true)
//    @Enumerated(EnumType.STRING)
//    private ERole name;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="roles")
//    @JsonIgnore
//    private Set<User_jwt> users;
//
//}
