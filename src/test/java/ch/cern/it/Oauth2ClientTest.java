package ch.cern.it;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ch.cern.it.http.client.HttpClientFactory;
import ch.cern.it.remote.api.ResourceCRUDimpl;
import ch.cern.it.security.oauth2.ClientCredentialsFlow;
import ch.cern.it.security.oauth2.Oauth2Client;
import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import ch.cern.it.remote.api.ResourceCRUD;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Oauth2ClientTest {
	
	private Oauth2Client oauth2Client;
	
	@Before
	public void setup() {
		oauth2Client = new Oauth2Client(
				System.getenv("OAUTH2_CLIENT_NAME"),
				System.getenv("OAUTH2_CLIENT_ID"), 
				System.getenv("OAUTH2_CLIENT_SECRET"));
	}

	@Test
	public void test_A_get_access_token() {
		String accessToken = getAccessToken();
		assertNotNull("ACCESS TOKEN IS NULL!!!", accessToken);
	}

	@Test
	public void test_B_get_protected_resource() {
		String accessToken = getAccessToken();
		Client client = HttpClientFactory.newBearerAuthenticatorClient(accessToken);
		ResourceCRUD siteCRUD = new ResourceCRUDimpl(client, System.getenv("API_URL"));
		assertNotNull("API RESULT IS NULL!!!", siteCRUD.getSitesAsJson());
	}

	private String getAccessToken() {
		String accessToken = null;
		Client client = HttpClientFactory.newBasicAuthenticatorClient(oauth2Client.getClient_id(),
				oauth2Client.getClient_secret());
		ClientCredentialsFlow clientCredentialsFlow = new ClientCredentialsFlow(System.getenv("OAUTH2_TOKEN_URL"));
		accessToken = clientCredentialsFlow.getAccessToken(client);
		return accessToken;
	}

}
