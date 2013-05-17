package com.nvisia.demo.springdata.persistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nvisia.demo.springdata.beans.Corporation;
import com.nvisia.demo.springdata.beans.GovernmentOrg;
import com.nvisia.demo.springdata.beans.GovernmentType;
import com.nvisia.demo.springdata.beans.LlcCorporation;
import com.nvisia.demo.springdata.beans.OrgType;
import com.nvisia.demo.springdata.beans.Organization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OrgRepositoryTest {

	@Autowired
	private OrgRepository orgRepository;
	
	@Test
	public void testGetOrgById() {
		Long orgId = 1L;
		Organization org = orgRepository.findOne(orgId);
		Assert.assertNotNull(org);
		Assert.assertTrue(org instanceof Corporation);
		Corporation corp = (Corporation) org;
		Assert.assertEquals("GOOGLE INC.", corp.getName());
		Assert.assertEquals(corp.getOrgType(), OrgType.INC);
	}
	
	@Test
	public void testGetAllLlcs() {
		//List<Organization> orgs = orgRepository.findByOrgType(OrgType.LLC.getCode());
		List<LlcCorporation> orgs = orgRepository.findAllLlcCorporations();
		Assert.assertNotNull(orgs);
		Assert.assertEquals(1, orgs.size());
		LlcCorporation llc = orgs.get(0);
		Assert.assertEquals(2L, llc.getId().longValue());
		Assert.assertEquals("NVISIA LLC.", llc.getName());
		Assert.assertEquals(llc.getOrgType(), OrgType.LLC);
	}

	@Test
	public void testGetAllFedOrgs() {
		List<GovernmentOrg> orgs = orgRepository.findAllFederalOrganizations();
		Assert.assertNotNull(orgs);
		Assert.assertEquals(1, orgs.size());
		GovernmentOrg fed = orgs.get(0);
		Assert.assertEquals("FBI", fed.getName());
		Assert.assertEquals(fed.getOrgType(), OrgType.GOV);
		Assert.assertEquals(fed.getGovernmentType(), GovernmentType.FEDERAL);
	}
}
