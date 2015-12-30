/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4_client.db;

import com.a4.javawebservice.model.Route;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radud
 */
public class RouteTable {

    private final Connection myConnection;

    public RouteTable() {
        myConnection = SqlInit.getMyConnection();
    }

    public int addRoute(Route route) {
        String insertTableSQL = "insert into route"
                + "(city, arrival_time, status, package_id, user_id) VALUES"
                + "(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, route.getCity());
            preparedStatement.setString(2, route.getArrivalTime());
            preparedStatement.setString(3, route.getStatus());
            preparedStatement.setInt(4, route.getPackageId());
            preparedStatement.setInt(5, route.getUserId());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            return -1;

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public Route getRoute(int id) {
        String select = "select * from route where id = ?";

        try {
            PreparedStatement prepareStatement = myConnection.prepareStatement(select);

            prepareStatement.setInt(1, id);

            ResultSet myRs = prepareStatement.executeQuery();

            myRs.next();

            String arrivalTime = myRs.getString("arrival_time");
            String city = myRs.getString("city");
            String status = myRs.getString("status");
            int packageId = myRs.getInt("package_id");
            int userId = myRs.getInt("user_id");

            return new Route(arrivalTime, city, status, packageId, userId);

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Route getRouteForPackage(int id) {
        String select = "select * from route where package_id = ?";

        try {
            PreparedStatement prepareStatement = myConnection.prepareStatement(select);

            prepareStatement.setInt(1, id);

            ResultSet myRs = prepareStatement.executeQuery();

            myRs.next();

            String arrivalTime = myRs.getString("arrival_time");
            String city = myRs.getString("city");
            String status = myRs.getString("status");
            int packageId = myRs.getInt("package_id");
            int userId = myRs.getInt("user_id");

            return new Route(arrivalTime, city, status, packageId, userId);

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Route> getRouteList() {
        String select = "select * from route";
        ArrayList<Route> routes = new ArrayList<>();
        PreparedStatement selectAll = null;
        try {

            selectAll = myConnection.prepareStatement(select);

            myConnection.setAutoCommit(false);
            ResultSet myRs = selectAll.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String arrivalTime = myRs.getString("arrival_time");
                String city = myRs.getString("city");
                String status = myRs.getString("status");
                int packageId = myRs.getInt("package_id");
                int userId = myRs.getInt("user_id");

                routes.add(new Route(id, arrivalTime, city, status, packageId, userId));
            }

            myConnection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return routes;
    }

    public ArrayList<Route> getRouteList(int userId) {
        String select = "select * from route where user_id = ?";
        ArrayList<Route> routes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConnection.prepareStatement(select);

            preparedStatement.setInt(1, userId);

            myConnection.setAutoCommit(false);
            ResultSet myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String arrivalTime = myRs.getString("arrival_time");
                String city = myRs.getString("city");
                String status = myRs.getString("status");
                int packageId = myRs.getInt("package_id");

                routes.add(new Route(id, arrivalTime, city, status, packageId, userId));
            }

            myConnection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return routes;
    }

    public void updateRoute(Route route) {
        String update = "update route set arrival_time = ?, city = ?, status = ?, package_id = ?, user_id where id = ?";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(update);

            preparedStatement.setString(1, route.getArrivalTime());
            preparedStatement.setString(2, route.getCity());
            preparedStatement.setString(3, route.getStatus());
            preparedStatement.setInt(4, route.getPackageId());
            preparedStatement.setInt(5, route.getUserId());
            preparedStatement.setInt(6, route.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRoute(int packageId, String arrivalTime, String city, String status) {
        String update = "update route set arrival_time = ?, city = ?, status = ? where package_id = ?";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(update);

            preparedStatement.setString(1, arrivalTime);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, status);
            preparedStatement.setInt(4, packageId);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeRoute(int id) {
        String delete = "delete from route where id = ?";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(delete);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RouteTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
