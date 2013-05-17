package com.nvisia.demo.springdata.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nvisia.demo.springdata.beans.Account;
import com.nvisia.demo.springdata.beans.Customer;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	
	List<Account> findByCustomer(Customer cust);
	
	@Query("select a from Account a where a.customer.id = :custId")
	List<Account> findByCustomerId(@Param("custId") Long custId);
	
	@Modifying
	@Transactional
	@Query("delete from Account a where a.customer.id = :custId")
	void deleteAllForCustomer(@Param("custId") Long custId);

}
