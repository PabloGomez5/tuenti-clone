package ProyectoSistemas.twitter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetUserGroupRoute implements Route {
	private final static Logger logger = LoggerFactory.getLogger(GetUserGroupRoute.class);
	
	public String handle(Request req, Response resp) throws Exception {
		logger.info("Getting user group");

        String userName1 = req.queryParams("user1");
        String userName2 = req.queryParams("user2");

        DataAccess da = new DataAccess();
        return String.valueOf(da.getUserGroup(userName1, userName2));
	}
}