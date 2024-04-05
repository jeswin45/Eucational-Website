package com.scope.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scope.website.model.MailModel;

public interface MailRepository extends JpaRepository<MailModel,Integer> {

}
