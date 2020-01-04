package ProyectoSistemas.twitter.routes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProfileShowDataRoute implements Route {


	private final static Logger logger = LoggerFactory.getLogger(ProfileShowDataRoute.class);

	//5
	
	public Object handle(Request req, Response resp) throws Exception {
		
		logger.info("Showing data user profile");
		
		String user = req.queryParams("user");
		
		DataAccess da = new DataAccess();
		
		List<String> data = da.profileShowData(user); 
				
		String jsonUsers = "";

		// this is what makes the L<str> as a string 
		ObjectMapper om = new ObjectMapper();
		jsonUsers = om.writeValueAsString(data);

		return jsonUsers;
	}

}
