package com.scope.website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.scope.website.model.SignupModel;




@Repository
public interface SignupRepository extends JpaRepository<SignupModel,Integer> {
	 
	 SignupModel findById(long id);
	
	
	 

	SignupModel findByEmailAndPassword(String email, String password);

	SignupModel findByEmail(String email);

	SignupModel findByPassword(String password);
		
	Optional<SignupModel> findById(Long id);

	
	 
}
