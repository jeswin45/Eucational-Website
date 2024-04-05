package com.scope.website.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="scopestudents")
public class WebsiteModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 long id;
String firstname;
String lastname;
 public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}
String email;
 String course;
 String dob;
 String gender;
 String country;
 String state;
String city;
String hobbies;
String password;


public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}



public String getHobbies() {
	return hobbies;
}

public void setHobbies(String hobbies) {
	this.hobbies = hobbies;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public byte[] getPhoto() {
	return photo;
}

public void setPhoto(byte[] photo) {
	this.photo = photo;
}
@Lob
byte[] photo;
 

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}

public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}

 
 
 
 
 
}
