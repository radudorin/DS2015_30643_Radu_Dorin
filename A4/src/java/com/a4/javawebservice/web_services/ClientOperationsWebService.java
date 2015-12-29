/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a4.javawebservice.web_services;

import a4_client.db.UserTable;
import com.a4.javawebservice.model.User;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author radud
 */
@WebService(serviceName = "client")
public class ClientOperationsWebService {

    UserTable userTable = new UserTable();

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

}
