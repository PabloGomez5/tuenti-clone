package ProyectoSistemas.twitter.routesTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestRegisterNewUser {

	@Test
	public void test() throws Exception {
		String responseBody = "fail";

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost("http://127.0.0.1:4567/register_new_user_route");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		

		Map<String, String> params = new HashMap<>();
		

		params.put("name", "Warren");
		params.put("surname_first", "paul");
		params.put("surname_second", "pearson");
		params.put("user_name", "warren8");
		params.put("password", "tortajada8");
		params.put("email", "warren.pearson@gmail.com");
		params.put("id_question", "3");
		params.put("answer", "runnig");
		
	
		String inputJson = new ObjectMapper().writeValueAsString(params);

		StringEntity stringEntity = new StringEntity(inputJson);
		httpPost.setEntity(stringEntity);

		HttpResponse response = httpclient.execute(httpPost);

		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));
		responseBody = br.readLine();

		Assert.assertEquals("Failed posting params", responseBody, "success");
	}
}