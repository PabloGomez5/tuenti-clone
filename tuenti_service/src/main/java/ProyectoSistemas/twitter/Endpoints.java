package ProyectoSistemas.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class Endpoints {
    private final static Logger logger = LoggerFactory.getLogger(Endpoints.class);

    public static Route index = (Request request, Response response) -> {
        logger.info("index page");
        response.status(200);
        return "index\n";
    };
}