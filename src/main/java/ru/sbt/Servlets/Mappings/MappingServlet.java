package ru.sbt.Servlets.Mappings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.sbt.Servlets.Utils.Connect.exec;
import static ru.sbt.Servlets.Utils.Connect.warmup;
import static ru.sbt.Servlets.Utils.UserTools.checkLoginAndPass;

public class MappingServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(MappingServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        warmup();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String path = req.getServletPath();

        switch (path) {
            case ("/login"):
                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
            case ("/logout"):
                req.getSession().setAttribute("isAuthorized", "false");
                logger.info("User @"+req.getSession().getAttribute("username")+" was logout");
                req.getSession().setAttribute("username", "");
                resp.sendRedirect("/login");
                break;
            case ("/register"):
                getServletContext().getRequestDispatcher("/reg.jsp").forward(req, resp);
                break;
            case ("/messenger"):
                getServletContext().getRequestDispatcher("/messenger.jsp").forward(req, resp);
                break;
            case ("/"):
                getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (req.getSession().isNew()) {
            logger.info("Got new session");
        }

        logger.info("Got GET request: " + path);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String path = req.getServletPath();
        HttpSession session = req.getSession();

        switch (path) {
            case ("/login"):
                if (checkLoginAndPass(req.getParameter("username"), req.getParameter("password"))) {
                    session.setAttribute("isAuthorized", "true");
                    session.setAttribute("username", req.getParameter("username"));
                    logger.info("User @"+req.getParameter("username")+" was authorized");
                    resp.sendRedirect("/messenger");
                } else {
                    resp.sendRedirect("/login");
                }
                break;
            case ("/register"):
                exec("INSERT INTO 'users'('username', 'password') VALUES ('"+
                        req.getParameter("username")+"','"+
                        req.getParameter("password")+"');");
                logger.info("New user @"+req.getParameter("username")+" was registered");
                resp.sendRedirect("/login");
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        logger.info("Got POST request: " + path);
    }

}
