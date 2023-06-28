package org.example;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/time")

public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        String timeZone = request.getParameter("timezone");
        ZoneId zoneId;

        if (timeZone != null && !timeZone.isEmpty()) {
            zoneId = ZoneId.of(timeZone.replace(" ","+"));
        } else {
            zoneId = ZoneId.of("UTC");
        }

        LocalDateTime currentTime = LocalDateTime.now(zoneId);
        String result = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        response.getWriter().write("<h1>Date and Time</h1>");
        response.getWriter().write("<p>"+ result +" "+ zoneId+"</p>");

        response.getWriter().close();

    }
}

