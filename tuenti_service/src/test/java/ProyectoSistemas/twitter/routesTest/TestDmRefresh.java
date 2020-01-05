package ProyectoSistemas.twitter.routesTest;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDmRefresh {

	@Test
	public void test() throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		List<String> msgList = new ArrayList<String>();
		
		String user_group_id = "1";
		
		try {

			String urlString = "http://127.0.0.1:4567/get_dms?user_group_id=" +  user_group_id; 
			
			
			HttpGet request = new HttpGet(URI.create(urlString));

			CloseableHttpResponse response = httpClient.execute(request);

			try {
				if (response.getStatusLine().getStatusCode() == 200) {

					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// return it as a String
						String jsonsUsers = EntityUtils.toString(entity);
						msgList = new ObjectMapper().readValue(jsonsUsers, List.class);
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
		
		System.out.println(" esta es la lista de mensajes del chat con id = 2 --> " + msgList);
	}
	
}
