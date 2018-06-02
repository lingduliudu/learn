package com.example.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TestFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filter)
			throws ServletException, IOException {
		filter.doFilter(req,resp);
	}

}
