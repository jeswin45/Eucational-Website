package com.scope.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scope.website.model.CoursesModel;

public interface CourseRepository extends JpaRepository<CoursesModel,Integer>{

}
