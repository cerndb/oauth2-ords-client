package ch.cern.it.security;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

/**
 * This just add the bearer authorization header in the request
 * 
 * @author lurodrig
 *
 */
public class BearerAuthenticator implements ClientRequestFilter {

	private static final String BEARER = "Bearer ";
	private final String token;

	public BearerAuthenticator(String token) {
		this.token = token;
	}

	public void filter(ClientRequestContext requestContext) throws IOException {
		MultivaluedMap<String, Object> headers = requestContext.getHeaders();
		final String bearerAuthentication = getBearerAuthentication();
		headers.add(HttpHeaders.AUTHORIZATION, bearerAuthentication);
	}

	private String getBearerAuthentication() {
		return BEARER + this.token;
	}

}
