/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4_client.db;

import com.a4.javawebservice.model.Package;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radud
 */
public class PackageTable {

    private final Connection myConnection;

    public PackageTable() {
        myConnection = SqlInit.getMyConnection();
    }

    public int addPackage(Package p) {
        String insertTableSQL = "insert into package"
                + "(sender_id, receiver_id, name, description, sender_city, destination_city, tracking) VALUES"
                + "(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, p.getSenderId());
            preparedStatement.setInt(2, p.getReceiverId());
            preparedStatement.setString(3, p.getName());
            preparedStatement.setString(4, p.getDescription());
            preparedStatement.setString(5, p.getSenderCity());
            preparedStatement.setString(6, p.getDestinationCity());
            preparedStatement.setBoolean(7, p.isTracking());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            return -1;

        } catch (SQLException ex) {
            Logger.getLogger(PackageTable.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public Package getPackage(int id) {
        String select = "select * from package where id = ?";

        try {
            PreparedStatement prepareStatement = myConnection.prepareStatement(select);

            prepareStatement.setInt(1, id);

            ResultSet myRs = prepareStatement.executeQuery();

            myRs.next();

            int senderId = myRs.getInt("sender_id");
            int receiverId = myRs.getInt("receiver_id");
            String name = myRs.getString("name");
            String description = myRs.getString("description");
            String senderCity = myRs.getString("sender_city");
            String destinationCity = myRs.getString("destination_city");
            boolean tracking = myRs.getBoolean("tracking");

            return new Package(id, receiverId, senderId, description, name, destinationCity, senderCity, tracking);

        } catch (SQLException ex) {
            Logger.getLogger(PackageTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Package> getPackageList() {
        String select = "select * from package";
        ArrayList<Package> packages = new ArrayList<>();
        PreparedStatement selectAll = null;
        try {

            selectAll = myConnection.prepareStatement(select);

            myConnection.setAutoCommit(false);
            ResultSet myRs = selectAll.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("id");
                int senderId = myRs.getInt("sender_id");
                int receiverId = myRs.getInt("receiver_id");
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                String senderCity = myRs.getString("sender_city");
                String destinationCity = myRs.getString("destination_city");
                boolean tracking = myRs.getBoolean("tracking");

                packages.add(new Package(id, receiverId, senderId, description, name, destinationCity, senderCity, tracking));
            }

            myConnection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(PackageTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return packages;
    }

    public void updatePackage(Package p) {
        String update = "update package set sender_id = ?, receiver_id = ?, name = ?, description = ?, sender_city = ?, destination_city = ?, tracking = ?";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(update);

            preparedStatement.setInt(1, p.getSenderId());
            preparedStatement.setInt(2, p.getReceiverId());
            preparedStatement.setString(3, p.getName());
            preparedStatement.setString(4, p.getDescription());
            preparedStatement.setString(5, p.getSenderCity());
            preparedStatement.setString(6, p.getDestinationCity());
            preparedStatement.setBoolean(7, p.isTracking());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PackageTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removePackage(int id) {
        String delete = "delete from package where id = ?";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(delete);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PackageTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
