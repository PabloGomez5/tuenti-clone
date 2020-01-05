package ProyectoSistemas.twitter.routesTest;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestGetUserGroup {
	
	@Test
	public void test() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String testUser1 = "gonzalo20";
		String testUser2 = "warren8";
		int user_group = 0;
			
		try {
			String params = "user1=" + testUser1 + "&user2=" + testUser2;
			HttpGet request = new HttpGet(URI.create("http://127.0.0.1:4567/get_user_group?")+ params);

			CloseableHttpResponse response = httpClient.execute(request);

			try {
				if (response.getStatusLine().getStatusCode() == 200) {

					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// return it as a String
						String result = EntityUtils.toString(entity);
						try {
							user_group = Integer.parseInt(result);
						}
						catch (NumberFormatException e) {
							user_group = 0;
						}
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
		
		Assert.assertTrue(user_group == 12);
	}
	
}	