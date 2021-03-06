package com.doinfinite.battlegame.model.repository;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.doinfinite.battlegame.model.Role;
import com.doinfinite.battlegame.model.SocialMediaService;
import com.doinfinite.battlegame.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/dataAccessContext.xml" })
@Transactional
public class UserRepositoryTest extends TestCase {
	
	private User user = new User();
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void buildData(){
		Date creationTime = new Date(System.currentTimeMillis());
		user.setEmail("testme@gmail.com");
		user.setUsername("cacho");
		user.setFirstName("Ewen");
		user.setLastName("Mackenzie");
		user.setPassword("Password1");
		user.setRole(Role.ROLE_USER);
		user.setSignInProvider(SocialMediaService.NONE);
		user.setCreationTime(creationTime);
		user.setModificationTime(creationTime);
		
		user = userRepository.save(user);
	}
	
	@Test
	public void testSaveUser(){
		assertTrue(userRepository.exists(user.getId()));
		assertTrue(userRepository.count() > 0);
	}
	
	@Test
	public void testFindByEmail(){
		User user = userRepository.findByEmail("testme@gmail.com");
		assertTrue(user!=null);
	}
	
	@Test
	public void testFindByUsername(){
		User user = userRepository.findByUsername("cacho");
		assertTrue(user!=null);
	}
	@Test
	public void testFindByUsernameAndSignInProvider(){
		User user = userRepository.findByUsernameAndSignInProvider("cacho",SocialMediaService.NONE);
		assertTrue(user!=null);
	}
}
