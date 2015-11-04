import entities.DAO.EntityDAO;
import entities.User;
import Utils.HibernateUtils;
import Utils.TextUtils;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public final String TAG = getClass().getSimpleName();

    private EntityDAO<User> entityDao;

    @Override
    public void init() throws ServletException {
        entityDao = new EntityDAO<User>(HibernateUtils.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute(Keys.SESSION_USER_LEY) != null) {
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, session null.");
            return;
        }

        String username = req.getParameter(Keys.USERNAME);
        String password = req.getParameter(Keys.PASSWORD);

        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, username or password are empty.");
            return;
        }

        User user = entityDao.getByField(User.class, Keys.USERNAME, username);

        if (user == null) {
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, user is null.");
            return;
        }

        if (user.getPassword().equals(password)) {
            if (user.getRole().equals(Keys.USER_ROLE)) {
                resp.sendRedirect(Jsp.USER_JSP);
            } else if (user.getRole().equals(Keys.ADMIN_ROLE)) {
                resp.sendRedirect(Jsp.ADMIN_JSP);
            } else {
                //unknown role
                resp.sendRedirect(Jsp.ERROR_JSP);
                System.out.println(TAG + " Error, no such role");
                return;
            }
            session.setAttribute(Keys.SESSION_USER_LEY, user);
        } else {
            resp.sendRedirect(Jsp.ERROR_JSP);
            System.out.println(TAG + " Error, password didn't match.");
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
