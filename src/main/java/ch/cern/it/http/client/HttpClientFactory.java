package ch.cern.it.http.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import ch.cern.it.security.BasicAuthenticator;
import ch.cern.it.security.BearerAuthenticator;

public class HttpClientFactory {

	public static Client newBasicAuthenticatorClient(String username, String password) {
		return ClientBuilder.newClient().register(new HttpClientLoggingFilter())
				.register(new BasicAuthenticator(username, password));
	}

	public static Client newBearerAuthenticatorClient(String token) {
		return ClientBuilder.newClient().register(new HttpClientLoggingFilter())
				.register(new BearerAuthenticator(token));
	}

}
