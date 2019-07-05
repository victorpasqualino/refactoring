package com.celfocus.training.domain.frontend;

import com.celfocus.training.domain.user.User;

public interface FrontendRequest {

	public String getFrontendUser(User user) throws Exception;

}
