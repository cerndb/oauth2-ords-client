package ch.cern.it.http.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class HttpClientUtils {

	public static String getResponse(Client client, String url, Entity<Form> data) {
		Response response = client.target(url).request().post(data);
		return getResponseBodyAsString(response);
	}

	public static String getResponse(Client client, String url) {
		Response response = client.target(url).request().get();
		return getResponseBodyAsString(response);
	}

	public static Entity<Form> buildFormData(String name, String value) {
		Form form = new Form();
		form.param(name, value);
		Entity<Form> data = Entity.form(form);
		return data;
	}

	private static String getResponseBodyAsString(Response response) {
		String responseBody = response.readEntity(String.class);
		return responseBody;
	}
}
