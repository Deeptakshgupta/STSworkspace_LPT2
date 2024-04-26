package com.wcs.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="USERS_ADDRESS")
public class Address {

    /*@NotBlank
    @Size(max = 50)
    @Email
    private String email;*/
    @Id
    @NotBlank
    private String UserId;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    @Size(max = 6)
    private int pin;

    @NotBlank
    @Size(max = 10)
    private Long contact;
}