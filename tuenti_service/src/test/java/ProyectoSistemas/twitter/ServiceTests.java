package ProyectoSistemas.twitter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ProyectoSistemas.twitter.routesTest.TestCheckAnswer;
import ProyectoSistemas.twitter.routesTest.TestCheckLogIn;
import ProyectoSistemas.twitter.routesTest.TestCheckUserName;
import ProyectoSistemas.twitter.routesTest.TestEditProfileShowedData;
import ProyectoSistemas.twitter.routesTest.TestGetUserInfoRoute;
import ProyectoSistemas.twitter.routesTest.TestInputsRefresh;
import ProyectoSistemas.twitter.routesTest.TestProfileShowData;
import ProyectoSistemas.twitter.routesTest.TestRegisterNewUser;
import ProyectoSistemas.twitter.routesTest.TestRegisteredUserList;
import ProyectoSistemas.twitter.routesTest.TestSendInputRoute;
import ProyectoSistemas.twitter.routesTest.TestShowDataToEdit;
import ProyectoSistemas.twitter.routesTest.TestShowPasswordRoute;
import ProyectoSistemas.twitter.routesTest.TestShowQuestionRoute;

@RunWith(Suite.class)
@SuiteClasses({ 
	
	//1
	TestCheckLogIn.class,
	//2
	TestCheckAnswer.class,
	//3
	TestRegisterNewUser.class,
	//4
	TestRegisteredUserList.class,
	//5
	TestProfileShowData.class, 
	//6
	TestInputsRefresh.class,
	//7
	TestSendInputRoute.class,
	//8
	TestEditProfileShowedData.class,
	//9
	TestCheckUserName.class,
	//10
	TestShowQuestionRoute.class,
	//11
	TestShowPasswordRoute.class,
	//12
	TestShowDataToEdit.class, 
	//13
	TestGetUserInfoRoute.class
	
	})

	public class ServiceTests {

	// here is where i can run all the tests ..
	
	}
