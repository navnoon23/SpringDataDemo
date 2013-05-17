package com.nvisia.demo.springdata.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nvisia.demo.springdata.beans.Cooperative;
import com.nvisia.demo.springdata.beans.Corporation;
import com.nvisia.demo.springdata.beans.GovernmentOrg;
import com.nvisia.demo.springdata.beans.LlcCorporation;
import com.nvisia.demo.springdata.beans.NonGovernmentOrg;
import com.nvisia.demo.springdata.beans.Organization;

public interface OrgRepository extends PagingAndSortingRepository<Organization, Long> {
	
	//Query("select o from Organization o where o.organizationTypeCode = :orgType")
	//List<Organization> findByOrgType(@Param("orgType") String orgTypeCode);
	
	@Query("select o from Corporation o")
	List<Corporation> findAllCorporations();

	@Query("select o from LlcCorporation o")
	List<LlcCorporation> findAllLlcCorporations();
	
	@Query("select o from GovernmentOrg o")
	List<GovernmentOrg> findAllGovernmentOrgs();

	@Query("select o from NonGovernmentOrg o")
	List<NonGovernmentOrg> findAllNonGovernmentOrgs();

	@Query("select o from GovernmentOrg o where o.governmentTypeCode='FED'")
	List<GovernmentOrg> findAllFederalOrganizations();

	@Query("select o from Cooperative o")
	List<Cooperative> findAllCooperatives();
}
