package ProyectoSistemas.twitter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import spark.Response;
import spark.Route;

public class PostRoute implements Route {
    private final static Logger logger = LoggerFactory.getLogger(PostRoute.class);

    public Object handle(Request req, Response resp) throws Exception {
        logger.info("PostRoute");

        String p1 = req.queryParams("param1");
        String p2 = req.queryParams("param2");

        List<String> params = new ArrayList<>();

        params.add(p1);
        params.add(p2);
        	
        ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(params);
    }
}
