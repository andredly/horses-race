package com.charniauski.training.horsesrace.services.authcaching;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by Andre on 03.12.2016.
 */
public class AuthenticationMemcachedServiceImp implements AuthenticationMemcachedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationMemcachedServiceImp.class);

    private final MemcachedClient memcachedClient;
    @Value("${timeToLiveSecondsAuth}")
    private int timeToLiveSecondsAuth;

    public AuthenticationMemcachedServiceImp(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
        memcachedClient.flush(60);
    }

    @Override
    public void put(String key, Object element) {
    memcachedClient.add(key,timeToLiveSecondsAuth,element);
        LOGGER.info("Put memcached element={}",element);
    }

    @Override
    public UsernamePasswordAuthenticationToken get(String key) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) memcachedClient.get(key);
        LOGGER.info("Get memcached element={}",token);
        return token;
    }
}
