/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a4.javawebservice.web_services;

import a4_client.db.PackageTable;
import a4_client.db.UserTable;
import com.a4.javawebservice.model.User;
import com.a4.javawebservice.model.Package;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author radud
 */
@WebService(serviceName = "admin")
public class AdminOperationsWebService {

    UserTable userTable = new UserTable();
    PackageTable packageTable = new PackageTable();

    @WebMethod(operationName = "getUsers")
    public ArrayList<User> getUsers() {
        return userTable.getUserList();
    }

    @WebMethod(operationName = "getPackages")
    public ArrayList<Package> getPackages() {
        return packageTable.getPackageList();
    }

    @WebMethod(operationName = "addPackage")
    public int addPackage(@WebParam(name = "package") Package p) {
        return packageTable.addPackage(p);
    }

    @WebMethod(operationName = "deletePackage")
    public void deletePackage(@WebParam(name = "packageId") int id) {
        packageTable.removePackage(id);
    }

    @WebMethod(operationName = "getUser")
    public User getUser(@WebParam(name = "userId") int id) {
        return userTable.getUser(id);
    }
}
