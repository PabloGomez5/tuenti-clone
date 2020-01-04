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


public class TestProfileShowData {

	
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClientProtocolException, IOException {
		
		String expectedUser = "pablogp5";
		
		List<String> dataProfileExpected = new ArrayList<String>();
		dataProfileExpected.add("pablo");
		dataProfileExpected.add("gomez");
		dataProfileExpected.add("perez");
		dataProfileExpected.add("pablo.gomez1@alumnos.uneatlantico.es");
		
		
		List<String> dataProfileActual = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {

			HttpGet request = new HttpGet(URI.create("http://127.0.0.1:4567/profile_show_data_route?user=")+ expectedUser);

			CloseableHttpResponse response = httpClient.execute(request);

			try {
				if (response.getStatusLine().getStatusCode() == 200) {

					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// return it as a String
						String jsonsUsers = EntityUtils.toString(entity);
						dataProfileActual = new ObjectMapper().readValue(jsonsUsers, List.class);
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
		
	 	Assert.assertEquals("Data List did not match", dataProfileExpected, dataProfileActual);
	}
	
}