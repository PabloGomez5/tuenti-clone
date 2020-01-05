package ProyectoSistemas.twitter.routes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;


public class DmRefreshRoute implements Route {
	
	private final static Logger logger = LoggerFactory.getLogger(DmRefreshRoute.class);
	
	public Object handle(Request req, Response resp) throws Exception {
		logger.info("Getting DMs");
		
		String user_group_id = req.queryParams("user_group_id");
		
		DataAccess da = new DataAccess();
		List<String> dms = da.dmRefresh(user_group_id); 
		String jsonData = "";

		// this is what makes the L<str> as a string 
		ObjectMapper om = new ObjectMapper();
		jsonData = om.writeValueAsString(dms);

		return jsonData;
	}
}