package com.sap.iot.ch.slack.dao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.iot.ch.slack.model.SlackUser;

/**
 * Default implementation of the {@link SlackUserDAO} interface based on Spring Data
 * JPA.
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/
 */
@Service
public class SlackUserDAOImpl extends BaseDAOImpl<Object> implements SlackUserDAO {

	/**
	 * The spring-data-jpa repository to be used for persistence operations.
	 */
	@Autowired
	SlackUserRepository repository = null;


	@Override
	public Iterable<SlackUser> findAll() {
		return repository.findAll();
	}

	@Override
	public SlackUser save(@Valid SlackUser entity) {
		return repository.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<SlackUser> save(@Valid Iterable<? extends SlackUser> entities) {
		return (Iterable<SlackUser>) repository.save(entities);
	}

	@Override
	public SlackUser findOne(String id) {
		SlackUser entity = repository.findOne(id);
		return entity;
	}

	public SlackUser findUserByUserId(String userId) {
		SlackUser user = repository.findByUserId(userId);
		if(user==null){
			user = new SlackUser();
			user.setUserId(userId);
		}
		return user;
			
	}

	@Override
	public boolean exists(String id) {
		return repository.exists(id);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(String id) {
		repository.delete(id);
	}

	@Override
	public void delete(SlackUser entity) {
		repository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends SlackUser> entities) {
		repository.delete(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}
