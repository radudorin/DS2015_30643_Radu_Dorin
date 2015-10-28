import entities.DAO.EntityDAO;
import entities.User;
import entities.Utils.HibernateUtils;
import entities.Utils.TextUtils;
import entities.constants.Jsp;
import entities.constants.Keys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Key;

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
            return;
        }

        String username = req.getParameter(Keys.USERNAME);
        String password = req.getParameter(Keys.PASSWORD);

        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            resp.sendRedirect(Jsp.ERROR_JSP);
            return;
        }

        User user = entityDao.getByField(User.class, Keys.USERNAME, username);

        if (user == null) {
            resp.sendRedirect(Jsp.ERROR_JSP);
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
                return;
            }
            session.setAttribute(Keys.SESSION_USER_LEY, user);
        } else {
            resp.sendRedirect(Jsp.ERROR_JSP);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
