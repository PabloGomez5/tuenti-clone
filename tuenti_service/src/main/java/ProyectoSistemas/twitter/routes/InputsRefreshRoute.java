package ProyectoSistemas.twitter.routes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;


public class InputsRefreshRoute implements Route {
	
	private final static Logger logger = LoggerFactory.getLogger(InputsRefreshRoute.class);

	//6
	
	public Object handle(Request req, Response resp) throws Exception {
		logger.info("Getting Inputs");
		DataAccess da = new DataAccess();
		List<String> users = da.inputsRefresh(); 
		String jsonUsers = "";

		// this is what makes the L<str> as a string 
		ObjectMapper om = new ObjectMapper();
		jsonUsers = om.writeValueAsString(users);

		return jsonUsers;
	}
}