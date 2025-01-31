package com.hetero.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull(message = "Please enter the password")
//    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
    private String password;

}
