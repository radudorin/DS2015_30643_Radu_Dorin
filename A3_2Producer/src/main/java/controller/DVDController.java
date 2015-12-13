package controller;

import model.DVD;
import queue.Producer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by radud on 13/12/2015.
 */

@WebServlet("/produce")
public class DVDController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("post_to_queue") != null) {


            String title = req.getParameter("dvd_title");
            Double price;
            int year;
            try {
                price = Double.parseDouble(req.getParameter("dvd_price"));
            } catch (Exception e) {
                price = 0.0;
            }

            try {
                year = Integer.parseInt(req.getParameter("dvd_year"));
            } catch (Exception e) {
                year = 0;
            }

            DVD dvd = new DVD(title, price, year);

            Producer.sendMessage(dvd);

            System.out.println("Message sent.");
            System.out.println(dvd.toString());
        }
    }


}
