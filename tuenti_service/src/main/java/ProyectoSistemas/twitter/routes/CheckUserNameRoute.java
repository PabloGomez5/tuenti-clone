package ProyectoSistemas.twitter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;

public class CheckUserNameRoute implements Route {
	private final static Logger logger = LoggerFactory.getLogger(CheckUserNameRoute.class);

	//9
	public Object handle(Request req, Response resp) throws Exception {
		logger.info("Checking User name");

        String user = req.queryParams("user");

        DataAccess da = new DataAccess();
        return da.checkUserName(user);
	}
}