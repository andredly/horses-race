package com.charniauski.training.horsesrace.services.authcaching;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by Andre on 03.12.2016.
 */
public interface AuthenticationMemcachedService {

    void put(String key,Object element);

    UsernamePasswordAuthenticationToken get(String key);

}
