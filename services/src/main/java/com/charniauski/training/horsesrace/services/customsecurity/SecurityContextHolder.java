package com.charniauski.training.horsesrace.services.customsecurity;

import org.springframework.security.core.userdetails.User;

public class SecurityContextHolder {

    private static final ThreadLocal<User> threadLocalScope = new  ThreadLocal<>();
	
	public final static User getLoggedUser() {
		return threadLocalScope.get();
	}
	
	public final static void setLoggedUser(User user) {
		threadLocalScope.set(user);
	}

}