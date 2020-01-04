package ProyectoSistemas.twitter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;


public class SendInputRoute implements Route {
	private final static Logger logger = LoggerFactory.getLogger(SendInputRoute.class);

	//7
	
	public String handle(Request req, Response resp) throws Exception {
		logger.info("Sending input");

        String userName = req.queryParams("user");
        String message = req.queryParams("message");

        DataAccess da = new DataAccess();
        return da.sendInput(userName, message);
	}
}