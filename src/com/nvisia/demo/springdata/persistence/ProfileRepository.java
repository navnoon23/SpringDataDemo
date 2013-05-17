package com.nvisia.demo.springdata.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;

import com.nvisia.demo.springdata.beans.Profile;

public interface ProfileRepository extends MongoRepository<Profile, Long> {
	

}
