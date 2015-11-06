import Cnstants.Jsp;
import Cnstants.Keys;
import Utils.HibernateUtils;
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
import java.util.List;

/**
 * Created by radud on 28/10/2015.
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

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
        }

        if (req.getParameter(Keys.LOGOUT) != null && req.getParameter(Keys.LOGOUT).equals(Keys.LOGOUT)) {
            session.invalidate();
            resp.sendRedirect(Jsp.INDEX_JSP);
            return;
        }

        List<Flight> flightList = flightEntityDAO.findAll(Flight.class);
        req.setAttribute(Keys.FLIGHT_LIST_KEY, flightList);
        req.getRequestDispatcher(Jsp.USER_FLIGHTS_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
