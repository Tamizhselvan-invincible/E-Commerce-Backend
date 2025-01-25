//package com.hetero.models;
//
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.*;
//import org.hibernate.validator.constraints.URL;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.PastOrPresent;
//import javax.validation.constraints.Pattern;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//
//    @NotNull(message = "First Name cannot be NULL")
//    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in first name")
//    @JsonProperty("FirstName")
//    private String firstName;
//
//    @NotNull(message = "Last Name cannot be NULL")
//    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in last name")
//    @JsonProperty("LastName")
//    private String lastName;
//
//
//    @NotNull(message = "User Name cannot be NULL")
//    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in last name")
//    @JsonProperty("UserName")
//    private String userName;
//
//    @NotNull(message = "Please enter the mobile Number")
//    @Column(unique = true)
//    @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
//    @JsonProperty("PhoneNumber")
//    private String mobileNo;
//
//    @NotNull(message = "Please enter the emaild id")
//    @Column(unique = true)
//    @Email
//    @JsonProperty("Email")
//    private String emailId;
//
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @PastOrPresent
//    @JsonProperty("CreatedAt")
//    private Date dateCreated;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @PastOrPresent
//    @JsonProperty("UpdatedAt")
//    private Date dateUpdated;
//
//    @URL
//    @JsonProperty("ProfilePicture")
//    private String profilePicture;
//
//
//
//    @NotNull
//    @JsonProperty("Role")
//    private AppRole role;
//
//
//    @OneToMany
//    @JoinColumn(name = "order")
//    @JsonProperty("Orders")
//    List<Order> orders;
//
//
//    @OneToMany
//    @JoinColumn(name = "address")
//    List<Address> addresses;
//
//
//
//    @PrePersist
//    protected void onCreate() {
//        dateCreated = new Date();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        dateUpdated = new Date();
//    }
//
//}
