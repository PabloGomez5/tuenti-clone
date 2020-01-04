package ProyectoSistemas.twitter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;

public class CheckAnswerRoute implements Route {
	private final static Logger logger = LoggerFactory.getLogger(CheckAnswerRoute.class);

	//2
	
	public String handle(Request req, Response resp) throws Exception {
		logger.info("Checking answer");

        String userName = req.queryParams("user");
        String answer = req.queryParams("answer");

        DataAccess da = new DataAccess();
        return da.checkAnswer(userName, answer);
	}
}