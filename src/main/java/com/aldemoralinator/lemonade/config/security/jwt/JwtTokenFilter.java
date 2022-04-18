package com.aldemoralinator.lemonade.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aldemoralinator.lemonade.config.security.AppUserDetailsService;
import com.aldemoralinator.lemonade.model.entity.Account;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, UsernameNotFoundException {
		
		// Get authorization header and validate
		final String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.hasText(headerAuth) || !headerAuth.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
	            return;
		}
		
		// Get jwt token and validate
		final String token = headerAuth.split(" ")[1].trim();
		if (!jwtUtil.validateJwtToken(token)) {
			filterChain.doFilter(request, response);
            return;
		}
		
		// Get user identity, validate and set Spring Security Context
		String username = jwtUtil.getUserNameFromJwtToken(token);
		Account account = (Account) appUserDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		filterChain.doFilter(request, response);
	}
}