package a4_client.db;

import com.a4.javawebservice.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author radud
 */
public class UserTable {

    private final Connection myConnection;

    public UserTable() {
        myConnection = SqlInit.getMyConnection();
    }

    public int addUser(User user) {
        String insertTableSQL = "insert into user"
                + "(username, password, full_name, admin) VALUES"
                + "(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setBoolean(4, user.isAdmin());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            return -1;

        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public User getUser(int id) {
        String select = "select * from user where id = ?";

        try {
            PreparedStatement prepareStatement = myConnection.prepareStatement(select);

            prepareStatement.setInt(1, id);

            ResultSet myRs = prepareStatement.executeQuery();

            myRs.next();

            String username = myRs.getString("username");
            String password = myRs.getString("password");
            String fullName = myRs.getString("full_name");
            boolean admin = myRs.getBoolean("admin");

            return new User(username, password, fullName, admin);

        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUser(String username, String password) {
        String select = "select * from user where username = ? and password = ?";

        try {
            PreparedStatement prepareStatement = myConnection.prepareStatement(select);

            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);

            ResultSet myRs = prepareStatement.executeQuery();

            myRs.next();

            int id = myRs.getInt("id");
            String username1 = myRs.getString("username");
            String password1 = myRs.getString("password");
            String fullName = myRs.getString("full_name");
            boolean admin = myRs.getBoolean("admin");

            return new User(id, username1, password1, fullName, admin);

        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getUserList() {
        String select = "select * from user";
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement selectAll = null;
        try {

            selectAll = myConnection.prepareStatement(select);

            myConnection.setAutoCommit(false);
            ResultSet myRs = selectAll.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String username = myRs.getString("username");
                String password = myRs.getString("password");
                String fullName = myRs.getString("full_name");
                boolean admin = myRs.getBoolean("admin");

                users.add(new User(id, username, password, fullName, admin));
            }

            myConnection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public void updateUser(User user) {
        String update = "update user set username = ?, password = ?, full_name = ?, admin = ? where id = ?";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(update);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setBoolean(4, user.isAdmin());
            preparedStatement.setInt(5, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeUser(int id) {
        String delete = "delete from user where id = ?";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(delete);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
