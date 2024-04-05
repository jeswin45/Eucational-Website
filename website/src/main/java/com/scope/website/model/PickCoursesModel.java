package com.scope.website.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="pickedcourses")
public class PickCoursesModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
	
@ManyToOne
private SignupModel user;

@ManyToOne
private CoursesModel course;

//	
	
}
