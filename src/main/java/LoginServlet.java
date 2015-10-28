import entities.DAO.EntityDAO;
import entities.User;
import entities.Utils.HibernateUtils;

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

    private EntityDAO<User> entityDao;

    @Override
    public void init() throws ServletException {
        entityDao = new EntityDAO<User>(HibernateUtils.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            System.out.println("session not null");
            resp.sendRedirect("user.jsp");
            session.invalidate();
        } else {
            System.out.println("session null");
            resp.sendRedirect("admin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
