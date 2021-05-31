package com.cg.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	private String email;
	@NotEmpty(message="Please enter your password")
	private String password;
    private boolean loggedIn = false;
}
