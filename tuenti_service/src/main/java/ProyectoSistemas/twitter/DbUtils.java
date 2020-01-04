package ProyectoSistemas.twitter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.p34r50n.sparkservice.config.Config;

public class DbUtils {
        public String checkUserExists(String user) {
            Connection con = null;
            String userName = "Not found";

            try{
                con = this.getConnection();
                Statement stmt = con.createStatement();
                String query = "select user_name from user where user_name = '" + user + "'";
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    userName = rs.getString(1);
                }
            }
            catch(Exception e) {
                System.out.println(e);
            }
            finally {
                this.closeConnection(con);
            }

            return userName;
        }

        public List<String> getUsers() {
            Connection con = null;
            List<String> userList = new ArrayList<String>();

            try{
                con = this.getConnection();

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select user_name from user");

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

            return userList;
        }

        private Connection getConnection() throws ClassNotFoundException, SQLException {
        	Properties config = Config.getConfig();
        	
            String db = config.getProperty("db.name");
            String host = config.getProperty("db.host");
            String port = config.getProperty("db.port");
            String user = config.getProperty("db.user");
            String pass = config.getProperty("db.pass");

            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionString =  "jdbc:mysql://" + host + ":" + port + "/" +db;

            return  DriverManager.getConnection(
                    connectionString, user, pass);
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
    }
