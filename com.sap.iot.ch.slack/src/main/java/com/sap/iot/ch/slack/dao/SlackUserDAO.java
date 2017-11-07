package com.sap.iot.ch.slack.dao;

import com.sap.iot.ch.slack.model.SlackUser;

/**
 * Interface describing the life-cycle operations (e.g. CRUD operations) for {@link SlackUser} objects.
 */
public interface SlackUserDAO extends BaseDAO<SlackUser>
{
	SlackUser findUserByUserId(String userId);
   
}
