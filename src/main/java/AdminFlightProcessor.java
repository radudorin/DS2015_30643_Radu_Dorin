import Cnstants.Jsp;
import Cnstants.Keys;
import Utils.HibernateUtils;
import Utils.TextUtils;
import entities.DAO.EntityDAO;
import entities.Flight;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by radud on 02/11/2015.
 */
@WebServlet("/process")
public class AdminFlightProcessor extends HttpServlet {

    public final String TAG = getClass().getSimpleName();
    private EntityDAO<Flight> flightEntityDAO;

    @Override
    public void init() throws ServletException {
        flightEntityDAO = new EntityDAO<Flight>(HibernateUtils.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute(Keys.SESSION_USER_LEY) == null) {
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, user is null.");
            return;
        } else if (!((User) session.getAttribute(Keys.SESSION_USER_LEY)).getRole().equals(Keys.ADMIN_ROLE)) {
            System.out.println(TAG + " Error, user is not an admin.");
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, user is null.");
            return;
        }

        String action = req.getParameter(Keys.FLIGHT_ACTION_KEY);
        if (!TextUtils.isEmpty(action)) {
            if (action.equals(Keys.ACTION_EDIT)) {
                System.out.println(TAG + " Action Edit");
                int id = Integer.parseInt(req.getParameter("flightId"));

                Flight flight = flightEntityDAO.findById(id, Flight.class);

                req.setAttribute("flight_id", flight.getId());
                req.setAttribute("flight_nr", flight.getFlightNr());
                req.setAttribute("airplane_type", flight.getAirplaneType());
                req.setAttribute("departure_city", flight.getDepartureCity());
                req.setAttribute("departure_date_and_hour", flight.getDepartureDateAndHour());
                req.setAttribute("arrival_city", flight.getArrivalCity());
                req.setAttribute("arrival_date_and_hour", flight.getArrivalDateAndHour());

                req.getRequestDispatcher(Jsp.EDIT_FLIGHT_JSP).forward(req, resp);
            } else if (action.equals(Keys.ACTION_DELETE)) {
                int id = Integer.parseInt(req.getParameter("flightId"));
                flightEntityDAO.delete(id, Flight.class);
                resp.sendRedirect(Jsp.ADMIN_JSP);
                System.out.println(TAG + " Action Delete");
            } else if (action.equals(Keys.ACTION_CREATE)) {
                System.out.println(TAG + " Action Create");
                resp.sendRedirect(Jsp.CREATE_FLIGHT_JSP);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute(Keys.SESSION_USER_LEY) == null) {
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, user is null.");
            return;
        } else if (!((User) session.getAttribute(Keys.SESSION_USER_LEY)).getRole().equals(Keys.ADMIN_ROLE)) {
            System.out.println(TAG + " Error, user is not an admin.");
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, user is null.");
            return;
        }

        if (req.getParameter(Keys.CREATE_FLIGHT) != null || req.getParameter(Keys.EDIT_FLIGHT) != null) {

            int id = -1;
            try {
                id = Integer.parseInt(req.getParameter("flight_id"));
            } catch (Exception e) {

            }
            Flight flight = flightEntityDAO.findById(id, Flight.class);
            if(flight == null) {
                flight = new Flight();
            }

            String nr = req.getParameter("flight_nr");
            String airplaneType = req.getParameter("airplane_type");
            String departureCity = req.getParameter("departure_city");
            String departureDateAndHour = req.getParameter("departure_date_and_hour");
            String arrivalCity = req.getParameter("arrival_city");
            String arrivalDateAndHour = req.getParameter("arrival_date_and_hour");

            flight.setFlightNr(Integer.parseInt(nr));
            flight.setAirplaneType(airplaneType);
            flight.setDepartureCity(departureCity);
            flight.setDepartureDateAndHour(departureDateAndHour);
            flight.setArrivalCity(arrivalCity);
            flight.setArrivalDateAndHour(arrivalDateAndHour);

            if (flightEntityDAO.saveOrUpdate(flight)) {
                resp.sendRedirect(Jsp.ADMIN_JSP);
            }

        }
    }
}
