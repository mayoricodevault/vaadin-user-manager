package de.merten.umgr.backend.bo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The user. 
 *
 * @author Nathanael Schwalbe
 * @since 16.02.2012
 */
@SuppressWarnings("serial")
@Entity
public class User implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable=false, length=255)
	private String surname;
	
	@NotBlank
	@Column(nullable=false, length=255)
	private String givenname;
	
	@NotNull
	@Email
	@Column(nullable=false, length=255)
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=6)
	private Gender gender;
	
	// der eindeutige Benutzername zum einloggen.
	@NotNull
	@Column(nullable=false, length=255, unique=true)
	private String login;
	
	// das Password.
	@NotNull
	@Column(nullable=false, length=255)
	private String password;
	
	@ElementCollection(targetClass = Role.class) 
	@CollectionTable(name = "user2role",
	    joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role_id")
	private Set<Role> roles; 
	
	User() {}
	
	/**
	 * Creates a new user. All parameter are required and not null or blank.
	 * 
	 * @param reseller to which the user belongs. e.g. abado.
	 * @param login the email or username. 
	 * @param gender
	 * @param givenname
	 * @param surname
	 * @param email
	 * @param password
	 */
	public User(String login, Gender gender, String givenname, String surname, String email, String password) {
		this.login = login;
		this.gender = gender;
		this.givenname = givenname;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surename) {
		this.surname = surename;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivename(String givename) {
		this.givenname = givename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
