package com.vansh.servlet;

import com.vansh.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userService = context.getBean(UserService.class);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map.Entry<Boolean, String> result = userService.loginUser(username, password);

        if(result.getKey()) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            request.getRequestDispatcher("WEB-INF/login-sucess.html").forward(request, response);
        } else {
            response.setStatus(400);
            response.setContentType("text/html");
            response.getWriter().println(
                    """
                    <script>
                        alert("%s");
                        window.location.href = "%s/login.html";
                    </script>
                    """.formatted(result.getValue(), request.getContextPath())
            );
        }
    }
}
