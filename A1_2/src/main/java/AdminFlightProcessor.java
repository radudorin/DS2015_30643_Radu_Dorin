import Cnstants.Jsp;
import Cnstants.Keys;
import Utils.DateUtils;
import Utils.HibernateUtils;
import Utils.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import entities.City;
import entities.DAO.EntityDAO;
import entities.Flight;
import entities.User;
import model.Address_components;
import model.GoogleResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Timestamp;

/**
 * Created by radud on 02/11/2015.
 */
@WebServlet("/process")
public class AdminFlightProcessor extends HttpServlet {

    public final String TAG = getClass().getSimpleName();
    private EntityDAO<Flight> flightEntityDAO;
    String charset = "UTF-8";

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

                Gson gson = new Gson();
                URLConnection departureCityConnection = new URL(Utils.URLBuilder.buildURL(flight.getDepartureCity().getLatitude(), flight.getDepartureCity().getLongitude())).openConnection();
                URLConnection arrivalCityConnection = new URL(Utils.URLBuilder.buildURL(flight.getArrivalCity().getLatitude(), flight.getArrivalCity().getLongitude())).openConnection();

                departureCityConnection.setRequestProperty("Accept-Charset", charset);
                arrivalCityConnection.setRequestProperty("Accept-Charset", charset);

                InputStream departureCityConnectionInputStream = departureCityConnection.getInputStream();
                InputStream arrivalCityConnectionInputStream = arrivalCityConnection.getInputStream();

                String departureCity = "";
                String arrivalCity = "";
                try {
                    GoogleResponse departureCityResponse = gson.fromJson(new InputStreamReader(departureCityConnectionInputStream), GoogleResponse.class);
                    GoogleResponse arrivalCityResponse = gson.fromJson(new InputStreamReader(arrivalCityConnectionInputStream), GoogleResponse.class);

                    for(Address_components address_components : departureCityResponse.getResults().get(0).getAddress_components()) {
                        if(address_components.getTypes()[0].equals("locality")) {
                            departureCity = address_components.getLong_name();
                        }
                    }

                    for(Address_components address_components : arrivalCityResponse.getResults().get(0).getAddress_components()) {
                        if(address_components.getTypes()[0].equals("locality")) {
                            arrivalCity = address_components.getLong_name();
                        }
                    }
                } catch (JsonParseException e) {
                    e.printStackTrace();
                }

                req.setAttribute("flight_id", flight.getId());
                req.setAttribute("flight_nr", flight.getFlightNr());
                req.setAttribute("airplane_type", flight.getAirplaneType());
                req.setAttribute("departure_city", departureCity);
                req.setAttribute("departure_date_and_hour", flight.getDepartureDateAndHour());
                req.setAttribute("arrival_city", arrivalCity);
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
            String departureCityName = req.getParameter("departure_city");
            String departureDateAndHour = req.getParameter("departure_date_and_hour");
            String arrivalCityName = req.getParameter("arrival_city");
            String arrivalDateAndHour = req.getParameter("arrival_date_and_hour");

            Gson gson = new Gson();
            URLConnection departureCityConnection = new URL(Utils.URLBuilder.buildURL(departureCityName)).openConnection();
            URLConnection arrivalCityConnection = new URL(Utils.URLBuilder.buildURL(arrivalCityName)).openConnection();

            departureCityConnection.setRequestProperty("Accept-Charset", charset);
            arrivalCityConnection.setRequestProperty("Accept-Charset", charset);

            InputStream departureCityConnectionInputStream = departureCityConnection.getInputStream();
            InputStream arrivalCityConnectionInputStream = arrivalCityConnection.getInputStream();

            City departureCity = new City();
            City arrivalCity = new City();

            try {
                GoogleResponse departureCityResponse = gson.fromJson(new InputStreamReader(departureCityConnectionInputStream), GoogleResponse.class);
                GoogleResponse arrivalCityResponse = gson.fromJson(new InputStreamReader(arrivalCityConnectionInputStream), GoogleResponse.class);

                departureCity.setLatitude(departureCityResponse.getResults().get(0).getGeometry().getLocation().getLat());
                departureCity.setLongitude(departureCityResponse.getResults().get(0).getGeometry().getLocation().getLng());
                arrivalCity.setLatitude(arrivalCityResponse.getResults().get(0).getGeometry().getLocation().getLat());
                arrivalCity.setLongitude(arrivalCityResponse.getResults().get(0).getGeometry().getLocation().getLng());

                System.out.println(departureCityResponse.getResults().get(0).getGeometry().getLocation().getLat() + "      " + departureCityResponse.getResults().get(0).getGeometry().getLocation().getLng());
                System.out.println(arrivalCityResponse.getResults().get(0).getGeometry().getLocation().getLat() + "      " + arrivalCityResponse.getResults().get(0).getGeometry().getLocation().getLng());
            } catch (JsonParseException e) {
                e.printStackTrace();
            }

            flight.setFlightNr(Integer.parseInt(nr));
            flight.setAirplaneType(airplaneType);
            flight.setDepartureCity(departureCity);
            flight.setDepartureDateAndHour(DateUtils.getDateFromString(departureDateAndHour));
            flight.setArrivalCity(arrivalCity);
            flight.setArrivalDateAndHour(DateUtils.getDateFromString(arrivalDateAndHour));
//
            if (flightEntityDAO.saveOrUpdate(flight)) {
                resp.sendRedirect(Jsp.ADMIN_JSP);
            }

        }
    }
}
