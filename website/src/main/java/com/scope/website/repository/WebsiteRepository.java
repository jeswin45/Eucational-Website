package com.scope.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scope.website.model.WebsiteModel;

@Repository
public interface WebsiteRepository extends JpaRepository<WebsiteModel,Integer>{

	WebsiteModel findByEmailAndPassword(String email, String password);

	WebsiteModel findById(long id);

}
