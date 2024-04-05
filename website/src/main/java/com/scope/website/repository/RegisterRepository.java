package com.scope.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scope.website.model.RegisterModel;
import com.scope.website.model.SignupModel;

public interface RegisterRepository extends JpaRepository<RegisterModel,Integer> {

	RegisterModel findByEmail(String email);
	RegisterModel findByEmailAndPassword(String email, String password);

}
