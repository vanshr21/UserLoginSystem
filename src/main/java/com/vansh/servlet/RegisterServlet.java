package com.vansh.servlet;

import com.vansh.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userService = context.getBean(UserService.class);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("register.html");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        Map.Entry<Boolean, String> result = userService.registerUser(
                firstName,
                lastName,
                email,
                username,
                password,
                confirmPassword
        );

        if(result.getKey()) {
            response.sendRedirect("user-created.html");
        } else {
            response.setStatus(400);
            response.setContentType("text/html");
            response.getWriter().println("""
                    <script>
                        alert("%s");
                        window.location.href = "%s/register.html";
                    </script>
                    """.formatted(result.getValue(), request.getContextPath()));

        }
    }
}
