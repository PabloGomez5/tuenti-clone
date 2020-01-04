package ProyectoSistemas.twitter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;


public class CheckLogInRoute implements Route {
	private final static Logger logger = LoggerFactory.getLogger(CheckLogInRoute.class);

	//1
	
	public String handle(Request req, Response resp) throws Exception {
		logger.info("Checking password");

        String userName = req.queryParams("user");
        String password = req.queryParams("password");

        DataAccess da = new DataAccess();
        return da.checkLogin(userName, password);
	}
}