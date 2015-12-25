package com.kingdee.internet.finance.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String message = "<h1>Hello World!!!</h1>";
		Writer writer = resp.getWriter();
		AttributePrincipal principal = (AttributePrincipal) req.getUserPrincipal();
		Map<String, Object> attributes = principal.getAttributes();
		Object userID = attributes.get("userid");
		Object userName = attributes.get("username");
		message += "<h2>name: " + principal.getName() + "</h2>";
		message += "<h2>attributes: " + attributes + "</h2>";
		message += "<h2>userID: " + userID + "</h2>";
		message += "<h2>userName: " + userName + "</h2>";
		writer.write(message);
	}

}
