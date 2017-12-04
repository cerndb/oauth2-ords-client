package ch.cern.it.json;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonUtils {
	
	public static String get(String responseBody, String key) {
		String accessToken;
		JsonObject jsonObject = getJsonObject(responseBody);
		accessToken = jsonObject.getString(key);
		return accessToken;
	}

	public static JsonObject getJsonObject(String responseBody) {
		InputStream is = new ByteArrayInputStream(responseBody.getBytes());
		JsonReader jsonReader = Json.createReader(is);
		JsonObject jsonObject = jsonReader.readObject();
		return jsonObject;
	}
}
