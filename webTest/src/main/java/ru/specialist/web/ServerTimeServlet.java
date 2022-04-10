package ru.specialist.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet("/ServerTime")
public class ServerTimeServlet extends HttpServlet
{
    // Задача: получив данные из запроса - сформировать ответ resp
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        resp.setContentType("text/html"); // MIME type
        resp.setCharacterEncoding("utf-8");

        // пишем resp в поток, передаются байты
        //resp.getOutputStream();

        // для текстового ответа удобнее использовать PrintWriter
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>");
        //out.println("My servlet is cool");
        out.printf("Server Time: %tR", LocalTime.now());
        out.println("</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}