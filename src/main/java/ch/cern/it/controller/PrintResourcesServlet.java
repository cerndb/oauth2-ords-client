package ch.cern.it.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;

import ch.cern.it.http.client.HttpClientFactory;
import ch.cern.it.remote.api.ResourceCRUDimpl;
import ch.cern.it.security.oauth2.ClientCredentialsFlow;
import ch.cern.it.security.oauth2.Oauth2Client;
import ch.cern.it.remote.api.ResourceCRUD;

/**
 * This class will access a REST service (GET) and print the raw content (json)
 */
@WebServlet("/printresources")
public class PrintResourcesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Oauth2Client oauth2Client;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrintResourcesServlet() {      
		oauth2Client = new Oauth2Client(
				System.getenv("OAUTH2_CLIENT_NAME"),
				System.getenv("OAUTH2_CLIENT_ID"), 
				System.getenv("OAUTH2_CLIENT_SECRET"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accessToken = null;
		Client client = HttpClientFactory.newBasicAuthenticatorClient(oauth2Client.getClient_id(),
				oauth2Client.getClient_secret());
		ClientCredentialsFlow clientCredentialsFlow = new ClientCredentialsFlow(System.getenv("OAUTH2_TOKEN_URL"));
		accessToken = clientCredentialsFlow.getAccessToken(client);
		client = HttpClientFactory.newBearerAuthenticatorClient(accessToken);
		ResourceCRUD siteCRUD = new ResourceCRUDimpl(client,System.getenv("API_URL"));
		response.getWriter().println(siteCRUD.getSitesAsJson());
	}

}
