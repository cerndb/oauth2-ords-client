package ch.cern.it.security.oauth2;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;

import ch.cern.it.http.client.HttpClientUtils;
import ch.cern.it.json.JsonUtils;

public class ClientCredentialsFlow {
	
	private static final String GRANT_TYPE="grant_type";
	private static final String CLIENT_CREDENTIALS="client_credentials";
	private static final String ACCESS_TOKEN="access_token";
	private String tokenUrl;

	public ClientCredentialsFlow(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getAccessToken(Client client) {
		String accessToken = null;
		Entity<Form> data = HttpClientUtils.buildFormData(GRANT_TYPE,CLIENT_CREDENTIALS);
		String responseBody = HttpClientUtils.getResponse(client, this.tokenUrl, data);
		accessToken = JsonUtils.get(responseBody, ACCESS_TOKEN);
		return accessToken;
	}
}
