package controleur;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import services.UserService;
import java.io.IOException;

@WebServlet("/log")
/**
 * Servlet qui s'occupe de l'authentification
 */
public class ControleurConnexion extends HttpServlet {
    @EJB
    UserService userService;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        User user = userService.getUserByData(login, mdp);

        if (user == null) {
            request.setAttribute("message", "Identifiants incorrects");
            request.getRequestDispatcher("analyse.jsp").forward(request, response);
        }
        else {
            /**
             * Une session est cree pour l'utilisateur durant son temps de connexion
             * ce qui permettra de retrouver ses informations partout dans l'appliaction
             */
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("analyse.jsp");
        }
    }
}

