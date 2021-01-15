package com.qweed.backend.security;

import com.qweed.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    CustomerService customerService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        //
    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object token = usernamePasswordAuthenticationToken.getCredentials();
        return Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(customerService::findByToken)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
    }
}
