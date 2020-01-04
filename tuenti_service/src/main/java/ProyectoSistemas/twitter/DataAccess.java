package ProyectoSistemas.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.databind.ObjectMapper;


public class DataAccess {
	
	
	// The connection methods called by the others to open/close the connection wit the data base (AWS)
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
        String db = "ProyectoSistemas";
        String host = "database-1.ckcstwoi50kw.us-east-1.rds.amazonaws.com";
        int port = 3306;

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionString =  "jdbc:mysql://" + host + ":" + port + "/" +db;

        return  DriverManager.getConnection(
                connectionString,"admin","suTQzgvp");
    }
	
	private void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
	}    
        
        
    // In the following lines we will find the 13 methods related with the database querys
	
	/**1 CheckLogIn
	 * 
	 * This method send the query to get the password from the userName introduced,
	 * after that, the method compare the introduce password with the DB password.
	 *   
	 * @param userName & password
	 * @return true ( if password is correct)
	 * @return false ( if password !correct )
	 */
	public String checkLogin(String userName, String password) {
		
		Connection con = null;
		String comparationResult = "false";
		
		try{
			
            con = this.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT Password FROM User WHERE (User_name = \""+userName+"\")";
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
            	
            	String passwordDB = rs.getString(1);
                boolean result = passwordDB.equals(password);
                if (result) {
             	   comparationResult ="true";
                }
            }
        }
        catch(Exception e) {
            System.out.println("error located in CheckLogin");
        }
        finally {	
            this.closeConnection(con);
        }
		
		return comparationResult;
	}
	
	
	/**2 CheckAnswer
	 * 
	 * This method check that the answer introduced to recover the password 
	 * in the forgetPassword view is or is not correct.
	 * 
	 * @param userName & answer
	 * @return true ( if password is correct)
	 * @return false ( if password !correct ) 
	 */
	public String checkAnswer(String userName, String answer) {
		
		Connection con = null;
		String comparationResult = "false";
		
		try{
			
            con = this.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT Answer FROM User WHERE (User_name = \""+userName+"\")";
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
            	
               String answerDB = rs.getString(1);
               boolean result = answerDB.equals(answer);
               if (result) {
            	   comparationResult ="true";
               }
            }
        }
        catch(Exception e) {
            System.out.println("error located in checkAnswer");
        }
        finally {	
            this.closeConnection(con);
        }
		
		return comparationResult;
	}
	
	
	/**3 registerNewUser
	 * 
	 * This method is the one that create new users in the data base
	 * 
	 * @param name, firstSurname, secondSurname, userName, password, email, idQuestion, answer.
	 */
	public void registerNewUser( String name, String firstSurname, String secondSurname, String userName,  String password, String email, int idQuestion, String answer) {
		Connection con = null;
        
		try{
            con = this.getConnection();
            Statement stmt = con.createStatement();
            String query = (" INSERT INTO `User` (`Name`, `First_Surname`, `Second_Surname`, `User_name`, `Password`, `Email`, `Id_Question`, `Answer`)" +
                    " VALUES ( \"" +name+ "\" , \"" +firstSurname+ "\" , \"" +secondSurname+ "\" , \"" +userName+ "\" , \"" +password+ "\" , \"" +email+ "\" , "+idQuestion +" ,\"" +answer+ "\" );");

            int rows = stmt.executeUpdate(query);
            System.out.println(rows);

        }
        catch(Exception e) {
        	e.printStackTrace();
            System.out.println("error located in registerNewUser");
        }
        finally {
            this.closeConnection(con);
        }
		
	}
	
	
	/**4 registeredUserList
	 * 
	 *This method is the one that return us as a list<string> all the users registered in the database
	 *
	 * @param 
	 * @return List<string>
	 */
	public List<String> registeredUserList(){
		
		Connection con = null;
		List<String> userList = new ArrayList<String>();
		
		try{
            con = this.getConnection();

            Statement stmt = con.createStatement();
            String query = ("SELECT  `User_name` from `User`;");
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
            	userList.add(rs.getString(1));
            	//System.out.println("el sig usuario se ha añadido a la lista : "+ rs.getString(1));
            	
            }
        }
        catch(Exception e) {
            System.out.println( "error located in registeredUserList");
        }
        finally {
            this.closeConnection(con);
        }
		
		//return userList.toString()
		return userList;
		
	}

	
	/**5 profileShowData
	 * 
	 * This is the method that give as as a list all the info about an user that we will print,
	 * in the menu when he will be logged in.
	 * 
	 * @param userName
	 * @return 
	 * @return list<String>
	 */
	public List<String> profileShowData( String userName) {
		
		Connection con = null;
		List<String> listaProfileData = new ArrayList<String>();
		
		try{
            con = this.getConnection();

            Statement stmt = con.createStatement();
            String query = ("SELECT  `Name`,`First_Surname`,`Second_Surname`,`Email` from `User` WHERE `User_name` = \"" +userName+ "\";");
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
            	
            	listaProfileData.add(rs.getString(1));//name
            	listaProfileData.add(rs.getString(2));//1surname
                listaProfileData.add(rs.getString(3));//2surname
                listaProfileData.add(rs.getString(4));//email
                
            }
        }
        catch(Exception e) {
            System.out.println( "error located in profileShowData");
        }
        finally {
            this.closeConnection(con);
        }
		
		//System.out.println(listaProfileData);
		//return userList.toString()
		return listaProfileData;
	}

	
	/**6 inputsRefresh
	 * 
	 * This method return us all the inputs from the data base.
	 * 
	 * @return List<String>
	 */
	public List<String> inputsRefresh(){
		

		Connection con = null;
		List<String> inputList = new ArrayList<String>();
		List<String> inputListFinal = new ArrayList<String>();
		
		try{
            con = this.getConnection();

            Statement stmt = con.createStatement();
            String query = ("SELECT `User_Name`, `message` FROM `Inputs` ;");
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
            	inputList.add(rs.getString(1)+ " : "+ rs.getString(2));
            	//System.out.println("el sig input se ha añadido a la lista : "+ rs.getString(1));
            }
        }
        catch(Exception e) {
            System.out.println( "error located in inputsRefresh");
        }
        finally {
            this.closeConnection(con);
        }
		
		/* 	the following lines are to change the order of the inputList 
			, just in case there would be  a refresh with new ones. That ones 
			will be at the top in the menu view.
		*/
		
		try {
			ListIterator<String> iter = inputList.listIterator(inputList.size());
			
			while (iter.hasPrevious()) {
				inputListFinal.add(iter.previous());
			}
		      
		}
		catch(Exception e) {
			System.out.println("error located in inputsRefresh");
		}
		
		return inputListFinal;
	}

	
	/**7 sendInput
	 * 
	 * This method is the one that create new input in the data base
	 * 
	 * @param userName , message.
	 */
	public String sendInput(String user_name, String message) {
		 Connection con = null;
	        String result = null;
			try{
	            con = this.getConnection();
	            Statement stmt = con.createStatement();
	            String query = ("INSERT INTO `Inputs` (`Id_Input`, `message`, `User_Name`) VALUES (NULL,\""+message+"\",\""+user_name+"\");");
	            int rows = stmt.executeUpdate(query);
	            System.out.println(rows);
	            result = "success";
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	            System.out.println("error located in sendInput");
	        }
	        finally {
	            this.closeConnection(con);
	        }
			return result;
	 }

	 
	/**8 editProfileShowedData
	* 
	* This method is based on the update of the data from the user we have chosen.
	* 
	* @param username
	* @param name
	* @param firstSurname
	* @param secondSurname
	* @param email
	* @param password
    * @param answer
    */
	public void editProfileShowedData(String username, String name, String firstSurname, String secondSurname, String email, String password, String answer) {
	    	
	    	Connection con = null;
	    	
			try{
	            con = this.getConnection();
	            Statement stmt = con.createStatement();
	            String query = (" UPDATE `User` SET `Name` = \"" + name + "\", `First_Surname` =  \"" + firstSurname + "\" , `Second_Surname` = \"" + secondSurname + "\" , `Password` = \"" + password + "\" , `Email` = \"" + email + "\" , `Answer` = \"" + answer + "\" WHERE `User`.`User_name` = \"" + username + "\" ;");
	            int rows = stmt.executeUpdate(query);
	            System.out.println(rows);

	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	            System.out.println("error located in editProfileShowedData");
	        }
	        finally {
	            this.closeConnection(con);
	        }
			
	    }
	
	
	/**9 checkUserName
	 * 
	 * This method is the one that checks if the user is registered
	 * 
	 * @param userName
	 * @return true if it exits
	 * @return false if it !exits
	 */
    public String checkUserName(String userName) {
        
    	Connection con = null;
    	String foundUser = "";

        try{
            con = this.getConnection();
            Statement stmt = con.createStatement();
            String query = ("select User_name from User where User_name = \""+userName+"\";");
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
            	foundUser = rs.getString(1);
            	return foundUser;
            }
        }
        catch(Exception e) {
            System.out.println("error located in checkUserName");
        }
        finally {
            this.closeConnection(con);
        }

        return foundUser;
    }

    
    /**10 showQuestion
     * 
     * this method return the question of the introduced userName
     * 
     * @param userName
     * @return String
     */
    public String showQuestion(String userName) {
    	
    	Connection con = null;
    	String question = null;
    	
    	try{
            con = this.getConnection();
            Statement stmt = con.createStatement();
            String query = ("SELECT `Question` FROM `Questions` WHERE(`Id_Question`= (SELECT `Id_Question` FROM `User` WHERE(`User_name` = \""+userName+"\")));");
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
            	question = rs.getString(1);
            	System.out.println(question);
            	return question;
            }
        }
        catch(Exception e) {
            System.out.println("error located in showQuestion");
        }
        finally {
            this.closeConnection(con);
        }

    	return question;
    }

    
    /**11 showPassword
     * 
     * This method give us back the password from the user chosen
     * 
     * @param userName
     * @return String
     */
    public String showPassword(String userName) {

    	Connection con = null;
    	String password = null;
    	
    	try{
            con = this.getConnection();
            Statement stmt = con.createStatement();
            String query = ("SELECT `Password` FROM `User` WHERE `User_name` = \""+userName+"\" ;");
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
            	password = rs.getString(1);
            	System.out.println(password);
            	return password;
            }
        }
        catch(Exception e) {
            System.out.println("error located in showQuestion");
        }
        finally {
            this.closeConnection(con);
        }

    	return password;
    	
    }
    
    
    /**12 showDataEdit
     * 
     * This is the method that give as as a list all the info about an user that we will print,
	 * in the Edit when he will be changing data. Is different but pretty similar to 5 (don't think is the same)
     * 
     * @return
     */
    public List<String> showDatatoEdit(String userName){
    	
    	Connection con = null;
		List<String> listaProfileData = new ArrayList<String>();
		
		try{
            con = this.getConnection();

            Statement stmt = con.createStatement();
            String query = ("SELECT  `Name`,`First_Surname`,`Second_Surname`,`Email`,`Password`,`Answer` from `User` WHERE `User_name` = \"" + userName + "\" ;");
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
            	
            	listaProfileData.add(rs.getString(1));//name
            	listaProfileData.add(rs.getString(2));//1surname
                listaProfileData.add(rs.getString(3));//2surname
                listaProfileData.add(rs.getString(4));//email
                listaProfileData.add(rs.getString(5));//password
                listaProfileData.add(rs.getString(6));//answer
            }
        }
        catch(Exception e) {
            System.out.println( "error located in profileShowData");
        }
        finally {
            this.closeConnection(con);
        }
		
		System.out.println("en el metodo data access" + listaProfileData);
		//return userList.toString()
		return listaProfileData;
    }
    
    
    /**13 getUserInfo
     * 
     * This ones give us back all the data from the user we ask for
     * 
     * @param userName
     * @return List<String>
     */
    public List<String> getUserInfo(String userName) {

        Connection con = null;
        List<String> userList = new ArrayList<String>();

        try{
            con = this.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from User where User_name = \""+userName+"\";");
           
            while(rs.next()) {
                userList.add(rs.getString(1));
                userList.add(rs.getString(2));
                userList.add(rs.getString(3));
                userList.add(rs.getString(4));
                userList.add(rs.getString(5));
                userList.add(rs.getString(6));
                userList.add(rs.getString(7));
                userList.add(rs.getString(8));
            }
        }
        catch(Exception e) {
            System.out.println("error located in getUserInfo");
        }
        finally {
            this.closeConnection(con);
        }

        return userList;
    }
 
    
    
    // warrencito did this one
    /**?????
     * 
     * @return
     */
    public String getUserEmails() throws Exception {

        Connection con = null;
        List<String> userList = new ArrayList<String>();

        try{
            con = this.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select Email from User");

            while(rs.next()) {
                userList.add(rs.getString(1));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally {
            this.closeConnection(con);
        }
        
        String jsonUsers = "";

		ObjectMapper om = new ObjectMapper();
		jsonUsers = om.writeValueAsString(userList);

		return jsonUsers;
    }
    
}
