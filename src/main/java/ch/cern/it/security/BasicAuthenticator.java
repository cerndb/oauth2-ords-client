package ch.cern.it.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

/**
 * From
 * http://www.adam-bien.com/roller/abien/entry/client_side_http_basic_access
 * 
 * @author lurodrig
 *
 */
public class BasicAuthenticator implements ClientRequestFilter {

	private static final String BASIC = "Basic ";
	private final String user;
	private final String password;

	public BasicAuthenticator(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public void filter(ClientRequestContext requestContext) throws IOException {
		MultivaluedMap<String, Object> headers = requestContext.getHeaders();
		final String basicAuthentication = getBasicAuthentication();
		headers.add(HttpHeaders.AUTHORIZATION, basicAuthentication);
	}

	private String getBasicAuthentication() {
		String token = this.user + ":" + this.password;
		try {
			return BASIC + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			throw new IllegalStateException("Cannot encode with UTF-8", ex);
		}
	}

}
