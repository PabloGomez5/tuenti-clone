package ProyectoSistemas.twitter.routes;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ProyectoSistemas.twitter.DataAccess;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterNewUserRoute implements Route  {

	private final static Logger logger = LoggerFactory.getLogger(RegisterNewUserRoute.class);

    public Object handle(Request req, Response resp) throws Exception {
        logger.info("Register New User");
        
        ObjectMapper om = new ObjectMapper();
        
        Map<String, String> jsonMap = om.readValue(req.body(), new TypeReference<Map<String, String>>(){});

        String name = jsonMap.get("name");
        String surname_first = jsonMap.get("surname_first");
        String surname_second = jsonMap.get("surname_second");
        String user_name = jsonMap.get("user_name");
        String password = jsonMap.get("password");
        String email = jsonMap.get("email");
        String id_questionS = jsonMap.get("id_question");
        int id_questionI;
        try {
        	id_questionI = Integer.parseInt(id_questionS);
        }
        catch (NumberFormatException e)
        {
        	id_questionI = 0;
        }
        String answer = jsonMap.get("answer");
        
        System.out.println(name);
        System.out.println(surname_first);
        System.out.println(surname_second);
        System.out.println(user_name);
        System.out.println(password);
        System.out.println(email);
        System.out.println(id_questionI);
        System.out.println(answer);
        
        // Insert all eight values into the database
        DataAccess da = new DataAccess();
        da.registerNewUser(name,surname_first,surname_second,user_name,password,email,id_questionI ,answer);
        
        
        // If anything blows up, return "failure".
        // Otherwise:

		return "success";
    }
}
