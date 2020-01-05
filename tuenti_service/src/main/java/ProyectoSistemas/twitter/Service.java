package ProyectoSistemas.twitter;

import static spark.Spark.get;
import static spark.Spark.post;

import ProyectoSistemas.twitter.routes.CheckAnswerRoute;
import ProyectoSistemas.twitter.routes.CheckLogInRoute;
import ProyectoSistemas.twitter.routes.InputsRefreshRoute;
import ProyectoSistemas.twitter.routes.ProfileShowDataRoute;
import ProyectoSistemas.twitter.routes.RegisterNewUserRoute;
import ProyectoSistemas.twitter.routes.RegisteredUserListRoute;
import ProyectoSistemas.twitter.routes.SendInputRoute;
import ProyectoSistemas.twitter.routes.ShowDataToEditRoute;
import ProyectoSistemas.twitter.routes.ShowPasswordRoute;
import ProyectoSistemas.twitter.routes.ShowQuestionRoute;
import ProyectoSistemas.twitter.routes.CheckUserNameRoute;
import ProyectoSistemas.twitter.routes.EditProfileShowedDataRoute;
import ProyectoSistemas.twitter.routes.GetUserInfoRoute;
import ProyectoSistemas.twitter.routes.GetUserGroupRoute;
import ProyectoSistemas.twitter.routes.DmRefreshRoute;
import ProyectoSistemas.twitter.routes.SendDmRoute;

public class Service {
    public static void main(String[] args) {
    	
    	// here is where i create the /end-points for each method 
    	
    	//1
    	get("/check_log_in_route", new CheckLogInRoute());
    	
    	//2
        get("/check_answer_route", new CheckAnswerRoute());
        
        //3
        post("/register_new_user_route", new RegisterNewUserRoute());
        
        //4
        get("/registered_user_list_route", new RegisteredUserListRoute());
        
        //5
        get("/profile_show_data_route", new ProfileShowDataRoute());
     
        //6
        get("/inputs_refresh_route", new InputsRefreshRoute());
        
        //7
        get("/send_input_route", new SendInputRoute());
        
        //8
        post("/edit_profile_showed_data", new EditProfileShowedDataRoute());
        
    	//9
        get("/check_user_exists_route", new CheckUserNameRoute());

        //10
        get("/show_question_route", new ShowQuestionRoute());
        
        //11
        get("/show_password_route", new ShowPasswordRoute());
        
        //12
        get("/show_data_to_edit_route", new ShowDataToEditRoute());
        
        //13
        get("/get_user_info_route", new GetUserInfoRoute());
        
        //14 
        get("/get_user_group", new GetUserGroupRoute());
        
        //15
        get("/get_dms", new DmRefreshRoute());
        
        //16
        get("/send_dm", new SendDmRoute());
    }
}