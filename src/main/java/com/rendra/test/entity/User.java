package com.rendra.test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;


    @NotBlank(message = "Fullname is required")
    @Length(min = 3, max = 100, message = "Fullname length must be between 3 and 100 characters")
    private String fullname;

    @NotBlank(message = "Username is required")
    @Length(min = 5, max = 50, message = "Username length must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 100, message = "Password length must be between 8 and 100 characters")
    private String password;

    @NotNull(message = "Status is required")
    private Character status;

}
