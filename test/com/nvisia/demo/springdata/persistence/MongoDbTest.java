package com.nvisia.demo.springdata.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nvisia.demo.springdata.beans.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext2.xml" })
public class MongoDbTest {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void crudProfile() {
		
		Long id = 1L;
		Profile p = mongoTemplate.findById(id, Profile.class);
		if( p != null ) {
			mongoTemplate.remove(p);
		}
		p = new Profile();
		p.setId(id);
		p.setUserid("JDOE");
		p.setDisplayName("JD");
		mongoTemplate.save(p);
		
		Profile saved = mongoTemplate.findById(id, Profile.class);
		Assert.assertEquals(p.getUserid(), saved.getUserid());
		Assert.assertEquals(p.getDisplayName(), saved.getDisplayName());
		
		p.setDisplayName("JOHN");
		mongoTemplate.save(p);
		
		saved = mongoTemplate.findById(id, Profile.class);
		Assert.assertEquals(p.getDisplayName(), saved.getDisplayName());
		
		mongoTemplate.remove(saved);

		saved = mongoTemplate.findById(id, Profile.class);
		Assert.assertNull(saved);
	}

}
