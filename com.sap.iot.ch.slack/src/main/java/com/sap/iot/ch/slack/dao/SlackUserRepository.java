package com.sap.iot.ch.slack.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.sap.iot.ch.slack.model.SlackUser;

/**
 * Interface describing the life-cycle operations (e.g. CRUD operations) for {@link SlackUser} objects.
 */
public interface SlackUserRepository extends CrudRepository<SlackUser, String>, JpaSpecificationExecutor<SlackUser>{
	
	SlackUser findByUserId(String userId);	
}
