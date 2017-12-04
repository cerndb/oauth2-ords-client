package ch.cern.it.security.oauth2;

/**
 * Encapsulates the application metadata
 * @author lurodrig
 *
 */
public class Oauth2Client {

	private String name;
	private String client_id;
	private String client_secret;

	public Oauth2Client(String name, String client_id, String client_secret) {
		super();
		this.name = name;
		this.client_id = client_id;
		this.client_secret = client_secret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

    @Override
    public String toString() {
        return "Oauth2Client{" + "name=" + name + ", client_id=" + client_id + ", client_secret=" + client_secret + '}';
    }
}
