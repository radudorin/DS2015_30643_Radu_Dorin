/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a4.javawebservice.web_services;

import a4_client.db.PackageTable;
import a4_client.db.RouteTable;
import a4_client.db.UserTable;
import com.a4.javawebservice.model.User;
import com.a4.javawebservice.model.Package;
import com.a4.javawebservice.model.Route;
import constants.Constants;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import utils.DateUtils;
import utils.TrackingThread;

/**
 *
 * @author radud
 */
@WebService(serviceName = "client")
public class ClientOperationsWebService {

    PackageTable packageTable = new PackageTable();
    UserTable userTable = new UserTable();
    RouteTable routeTable = new RouteTable();

    @WebMethod(operationName = "login")
    public User login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        User user = userTable.getUser(username, password);
        return user;
    }

    @WebMethod(operationName = "register")
    public int register(@WebParam(name = "user") User user) {
        if (user != null) {
            return userTable.addUser(user);
        }
        return -1;
    }

    @WebMethod(operationName = "getUsers")
    public ArrayList<User> getUsers() {
        return userTable.getUserList();
    }

    @WebMethod(operationName = "getPackages")
    public ArrayList<Package> getPackages(@WebParam(name = "userId") int id) {
        return packageTable.getPackageList(id);
    }

    @WebMethod(operationName = "getPackage")
    public Package getPackage(@WebParam(name = "packageId") int packageId) {
        return packageTable.getPackage(packageId);
    }

    @WebMethod(operationName = "getRoutes")
    public ArrayList<Route> getRoutes(@WebParam(name = "userId") int userId) {
        return routeTable.getRouteList(userId);
    }

    @WebMethod(operationName = "getRoute")
    public Route getRoute(@WebParam(name = "routeId") int routeId) {
        return routeTable.getRoute(routeId);
    }

    @WebMethod(operationName = "beginTracking")
    public void beginTracking(@WebParam(name = "packageId") int packageId) {
        Package p = packageTable.beginTracking(packageId);
        if (p == null) {
            return;
        }

        routeTable.addRoute(new Route(DateUtils.getCurrentTimeStamp(), p.getSenderCity(), Constants.STATUS_IN_PROGRESS, packageId, p.getReceiverId()));
        TrackingThread thread = new TrackingThread(packageId, routeTable, packageTable);
        thread.start();
    }

}
