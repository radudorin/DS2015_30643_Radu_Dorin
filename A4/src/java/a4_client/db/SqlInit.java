package a4_client.db;

import constants.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlInit {

    private static Connection myConnection = null;
    private static Statement myStatement = null;

    private static void initDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqlInit.class.getName()).log(Level.SEVERE, null, ex);
        }
        connect(Constants.URL, Constants.USER, Constants.DATABASE, Constants.PASSWORD);
    }

    private static boolean connect(String url, String user, String database, String password) {
        try {
            //Establishing the connection with the Data Base
            if (!database.equals("")) {
                url = url + "/" + database;
            }

            myConnection = DriverManager.getConnection(url, user, password);
            myStatement = myConnection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Connection unsuccesfull");
            return false;
        }
        System.out.println("Connection succesfull");
        return true;
    }

    public static Connection getMyConnection() {
        if (myConnection == null) {
            initDb();
        }
        return myConnection;
    }

    public static Statement getMyStatement() {
        if (myStatement == null) {
            initDb();
        }
        return myStatement;
    }

}
