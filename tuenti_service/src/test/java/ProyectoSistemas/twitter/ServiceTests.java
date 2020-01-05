package ProyectoSistemas.twitter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ProyectoSistemas.twitter.routesTest.TestCheckAnswer;
import ProyectoSistemas.twitter.routesTest.TestCheckLogIn;
import ProyectoSistemas.twitter.routesTest.TestCheckUserName;
import ProyectoSistemas.twitter.routesTest.TestDmRefresh;
import ProyectoSistemas.twitter.routesTest.TestEditProfileShowedData;
import ProyectoSistemas.twitter.routesTest.TestGetUserGroup;
import ProyectoSistemas.twitter.routesTest.TestGetUserInfo;
import ProyectoSistemas.twitter.routesTest.TestInputsRefresh;
import ProyectoSistemas.twitter.routesTest.TestProfileShowData;
import ProyectoSistemas.twitter.routesTest.TestRegisterNewUser;
import ProyectoSistemas.twitter.routesTest.TestRegisteredUserList;
import ProyectoSistemas.twitter.routesTest.TestSendInput;
import ProyectoSistemas.twitter.routesTest.TestShowDataToEdit;
import ProyectoSistemas.twitter.routesTest.TestShowPassword;
import ProyectoSistemas.twitter.routesTest.TestShowQuestion;

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
	TestSendInput.class,
	//8
	TestEditProfileShowedData.class,
	//9
	TestCheckUserName.class,
	//10
	TestShowQuestion.class,
	//11
	TestShowPassword.class,
	//12
	TestShowDataToEdit.class, 
	//13
	TestGetUserInfo.class,
	//14
	TestGetUserGroup.class,
	//14b
	
	//15
	TestDmRefresh.class
	
	})

	public class ServiceTests {

	// here is where i can run all the tests ..
	
	}
