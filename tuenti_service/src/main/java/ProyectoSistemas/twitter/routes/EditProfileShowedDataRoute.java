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


public class EditProfileShowedDataRoute implements Route  {

	private final static Logger logger = LoggerFactory.getLogger(EditProfileShowedDataRoute.class);

    public Object handle(Request req, Response resp) throws Exception {
        logger.info("Updating db with new profile User data ");
        
        ObjectMapper om = new ObjectMapper();
        
        Map<String, String> jsonMap = om.readValue(req.body(), new TypeReference<Map<String, String>>(){});

        String user_name = jsonMap.get("user_name");
        String name = jsonMap.get("name");
        String surname_first = jsonMap.get("surname_first");
        String surname_second = jsonMap.get("surname_second");
        String email = jsonMap.get("email");
        String password = jsonMap.get("password");
        String answer = jsonMap.get("answer");
        
        System.out.println(user_name);
        System.out.println(name);
        System.out.println(surname_first);
        System.out.println(surname_second);
        System.out.println(password);
        System.out.println(email);
        System.out.println(answer);
        
        // Insert all eight values into the database
        DataAccess da = new DataAccess();
        String success = da.editProfileShowedData(user_name,name,surname_first,surname_second,email,password,answer);
        
        
        // If anything blows up, return "failure".
        // Otherwise:

		return success;
    }
}

