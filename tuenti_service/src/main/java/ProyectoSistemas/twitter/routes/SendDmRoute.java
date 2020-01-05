package ProyectoSistemas.twitter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;


public class SendDmRoute implements Route {
	private final static Logger logger = LoggerFactory.getLogger(SendDmRoute.class);
	
	public String handle(Request req, Response resp) throws Exception {
		logger.info("Sending dm");

        String userName = req.queryParams("user");
        String userGroupId = req.queryParams("user_group_id");
        String message = req.queryParams("message");

        DataAccess da = new DataAccess();
        return da.sendDM(userName, userGroupId, message);
	}
}