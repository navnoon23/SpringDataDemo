package com.nvisia.demo.springdata.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nvisia.demo.springdata.beans.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List<Customer> findByLastName(String lastName);
	
	List<Customer> findByGender(String gender);

	List<Customer> findByBirthDate(Date birthDate);
	
	List<Customer> findByLastNameAndFirstName(String lastName, String firstName);
	
	@Query("select c from Customer c where c.firstName = :name or c.lastName = :name")
	List<Customer> findByFirstOrLast(@Param("name") String name);
	
	//TODO: 
	// Inheritence, 
	// Domain object Disparate data stores derby + mongoDB/neo4j, 
	// w/out Hibernate
	// going against indexes for queries
	// weighted queries
}
