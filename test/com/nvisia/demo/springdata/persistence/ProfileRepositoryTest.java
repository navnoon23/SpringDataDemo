package com.nvisia.demo.springdata.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nvisia.demo.springdata.beans.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext2.xml" })
public class ProfileRepositoryTest {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Test
	public void testCreateProfile() {
		Long id = 1L;
		String uid = "JDOE";
		String dname = "JD";
		
		profileRepository.delete(id);
		
		Profile p = new Profile();
		p.setId(id);
		p.setUserid(uid);
		p.setDisplayName(dname);
		profileRepository.save(p);
		
		Profile saved = profileRepository.findOne(id);
		Assert.assertNotNull(saved);
		Assert.assertEquals(id, saved.getId());
		Assert.assertEquals(uid, saved.getUserid());
		Assert.assertEquals(dname, saved.getDisplayName());
	}
}
