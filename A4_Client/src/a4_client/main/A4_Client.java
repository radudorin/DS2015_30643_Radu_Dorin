package a4_client.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import a4_client.gui.LoginForm;


/**
 *
 * @author radud
 */
public class A4_Client {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new LoginForm().setVisible(true);
    }

//    public static String getResult(int num1, int num2) {
//
//        return (new MyWebService_Service()).getMyWebServicePort().hello("asd").getUsername();
//    }
//
//    public A4_Client() {
//        this.proxy = (new MyWebService_Service()).getMyWebServicePort();
//    }

}
