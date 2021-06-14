package com.cg.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "login")
@Entity
public class CustomerLogin {
	@GeneratedValue
	private int id;
	@Id
	@NotEmpty(message="Please enter your userid")
	@Email
	private String email;
	@NotEmpty(message="Please enter your password")
	@Size(min=5,message = "Mobilenumber must have 10 digits")
	private String password;
    private boolean loggedIn = false;
    private boolean isAdmin =false;
}
