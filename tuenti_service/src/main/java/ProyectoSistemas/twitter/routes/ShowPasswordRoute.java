package ProyectoSistemas.twitter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;


public class ShowPasswordRoute implements Route {
	private final static Logger logger = LoggerFactory.getLogger(ShowPasswordRoute.class);

	//10
	public Object handle(Request req, Response resp) throws Exception {
		logger.info("showing password");

        String user = req.queryParams("user");

        DataAccess da = new DataAccess();
        return da.showPassword(user);
	}
	
}