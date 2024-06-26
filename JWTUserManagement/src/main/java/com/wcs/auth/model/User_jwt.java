package com.wcs.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_jwt")
public class User_jwt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    @Size(min = 8, max = 20)
    private String userId;

    @NotBlank
    @Size(max = 10)
    private String contact;

    @NotBlank
//    @Size(min = 6, max = 10)
    private String password;

    private String role;
}
