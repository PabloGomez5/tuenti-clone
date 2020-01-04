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


public class TestGetUserInfoRoute {

	
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClientProtocolException, IOException {
		
		String expectedUser = "pablogp5";
		
		List<String> dataEditProfileExpected = new ArrayList<String>();
		dataEditProfileExpected.add("pablo");
		dataEditProfileExpected.add("gomez");
		dataEditProfileExpected.add("perez");
		dataEditProfileExpected.add("pablogp5");
		dataEditProfileExpected.add("tortajada5");
		dataEditProfileExpected.add("pablo.gomez1@alumnos.uneatlantico.es");
		dataEditProfileExpected.add("1");
		dataEditProfileExpected.add("Jaime");
		
		
		List<String> dataEditProfileActual = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {

			HttpGet request = new HttpGet(URI.create("http://127.0.0.1:4567/get_user_info_route?user=")+ expectedUser);

			CloseableHttpResponse response = httpClient.execute(request);

			try {
				if (response.getStatusLine().getStatusCode() == 200) {

					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// return it as a String
						String jsonsUsers = EntityUtils.toString(entity);
						dataEditProfileActual = new ObjectMapper().readValue(jsonsUsers, List.class);
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
		
	 	Assert.assertEquals("the user data return from dataBase failed", dataEditProfileExpected, dataEditProfileActual);
	}
	
}	
	