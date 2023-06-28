package org.example;

import java.io.IOException;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(value = "/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {

        String timeZone = request.getParameter("timezone");

        if (isValidTimezone(timeZone)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(404);
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println("<h1>Invalid timezone</h1>");
            response.getWriter().close();

        }
    }

    private boolean isValidTimezone(String time) {
        try {
            ZoneId zone = ZoneId.of(time);
            return true; // Временная зона существует
        } catch (ZoneRulesException e) {
            return  false; // Временная зона не существует
        }
    }
}
