import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet("/get_users")
public class Hello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        List<User> usrs = new ArrayList<>();
        usrs.add(new User(1, "Pom", "400"));
        usrs.add(new User(2, "Tot", "500"));
        usrs.add(new User(3, "Non", "600"));
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(usrs));
        writer.close();
    }
}
