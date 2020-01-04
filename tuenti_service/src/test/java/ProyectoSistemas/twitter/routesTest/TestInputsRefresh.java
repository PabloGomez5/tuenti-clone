package ProyectoSistemas.twitter.routesTest;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestInputsRefresh {
	
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClientProtocolException, IOException {
		
		List<String> expectedUsers = new ArrayList<String>();
		expectedUsers.add("pablogp5 : programando hoy en dublin.");
		expectedUsers.add("marcossaro7 : hola mi gente, hoy de cena rommantica en sangai.");
		expectedUsers.add("pablogp5 : hola mi gente, hoy de concierto en cuba.");
		
		List<String> actualUsers = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {

			HttpGet request = new HttpGet(URI.create("http://127.0.0.1:4567/inputs_refresh_route"));

			CloseableHttpResponse response = httpClient.execute(request);

			try {
				if (response.getStatusLine().getStatusCode() == 200) {

					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// return it as a String
						String jsonsUsers = EntityUtils.toString(entity);
						actualUsers = new ObjectMapper().readValue(jsonsUsers, List.class);
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
		
	 	Assert.assertEquals("User list did not match", expectedUsers, actualUsers);
	}
	


}
