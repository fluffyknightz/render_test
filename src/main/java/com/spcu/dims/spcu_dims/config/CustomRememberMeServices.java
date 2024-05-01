package com.spcu.dims.spcu_dims.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;


public class CustomRememberMeServices extends TokenBasedRememberMeServices implements RememberMeServices {


    public CustomRememberMeServices(String key, MyUserDetailsServices myUserDetailsService) {
        super(key, myUserDetailsService);
    }

    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        // Update the expiration time of the "Remember Me" cookie
        MyUserDetails myUserDetails = (MyUserDetails) successfulAuthentication.getPrincipal();
        createNewCookieWithUpdatedExpiry(request, response, myUserDetails.getUsername());

        super.onLoginSuccess(request, response, successfulAuthentication);
    }

    private void createNewCookieWithUpdatedExpiry(HttpServletRequest request, HttpServletResponse response, String username) {
        // Calculate the new expiration time (2 weeks from now)
        long expirationTimeMillis = System.currentTimeMillis() + (14 * 24 * 60 * 60 * 1000); // 14 days in milliseconds

        // Create a new cookie with the updated expiration time
        Cookie newCookie = new Cookie(getCookieName(), username);
        newCookie.setMaxAge((int) (expirationTimeMillis - System.currentTimeMillis()) / 1000); // Set max age in seconds
        newCookie.setSecure(request.isSecure());
        response.addCookie(newCookie);
    }
}