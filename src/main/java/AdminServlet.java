import entities.constants.Jsp;
import entities.constants.Keys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by radud on 28/10/2015.
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    public final String TAG = getClass().getSimpleName();

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute(Keys.SESSION_USER_LEY) == null) {
            resp.sendRedirect(Jsp.ERROR_JSP);
            return;
        }

        if(req.getParameter(Keys.LOGOUT).equals(Keys.LOGOUT)) {
            session.invalidate();
            resp.sendRedirect(Jsp.INDEX_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
