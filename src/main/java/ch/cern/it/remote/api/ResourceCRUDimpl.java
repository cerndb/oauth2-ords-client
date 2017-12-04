package ch.cern.it.remote.api;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;

import ch.cern.it.http.client.HttpClientUtils;
import ch.cern.it.json.JsonUtils;

public class ResourceCRUDimpl implements ResourceCRUD {

	private String apiUrl;
	private Client client;

	public ResourceCRUDimpl(Client client,String apiUrl) {
		this.apiUrl = apiUrl;
		this.client = client;
	}

	@Override
	public JsonObject getSitesAsJson() {
		return JsonUtils.getJsonObject(HttpClientUtils.getResponse(this.client, this.apiUrl));
	}

}
