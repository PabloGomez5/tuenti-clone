package ProyectoSistemas.twitter.routesTest;

import java.io.IOException;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestCheckLogIn {

	@Test
	public void test() throws IOException {

		String user = "pablogp5";
		String password = "tortajada5";
		String success ="false";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {

			String urlString = "http://127.0.0.1:4567/check_log_in_route?user=" + user;
			urlString += "&password=" + password;
			
			HttpGet request = new HttpGet(URI.create(urlString));

			CloseableHttpResponse response = httpClient.execute(request);

			try {
				if (response.getStatusLine().getStatusCode() == 200) {

					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// return it as a String
						success = EntityUtils.toString(entity);
					}
				}
			} 
			finally {
				response.close();
			}
		} 
		finally {
			httpClient.close();
		}
		
		Assert.assertEquals("Incorrect password", success, "true");
		
	}
}
