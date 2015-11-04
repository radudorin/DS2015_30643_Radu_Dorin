import entities.DAO.EntityDAO;
import entities.User;
import Utils.HibernateUtils;
import Cnstants.Jsp;
import Cnstants.Keys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by radud on 27/10/2015.
 */

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    public final String TAG = getClass().getSimpleName();

    private EntityDAO<User> entityDao;

    @Override
    public void init() throws ServletException {
        entityDao = new EntityDAO<User>(HibernateUtils.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute(Keys.SESSION_USER_LEY) != null) {
            User user = (User) session.getAttribute(Keys.SESSION_USER_LEY);
            if (user.getRole().equals(Keys.USER_ROLE)) {
                resp.sendRedirect(Jsp.USER_JSP);
            } else if (user.getRole().equals(Keys.ADMIN_ROLE)) {
                resp.sendRedirect(Jsp.ADMIN_JSP);
            }
        } else {
            session.invalidate();
            resp.sendRedirect(Jsp.LOGIN_JSP);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
