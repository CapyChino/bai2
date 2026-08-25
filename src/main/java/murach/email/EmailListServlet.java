package murach.email;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import murach.business.User;
import murach.data.UserDB;

public class EmailListServlet extends HttpServlet {

    // Map giá trị submit -> nhãn hiển thị trên trang cảm ơn (như tmp.html)
    private static final Map<String, String> HEAR_ABOUT_LABELS = new HashMap<>();
    private static final Map<String, String> CONTACT_BY_LABELS = new HashMap<>();
    private static final Map<String, String> ANNOUNCEMENT_LABELS = new HashMap<>();

    static {
        HEAR_ABOUT_LABELS.put("search", "Search engine");
        HEAR_ABOUT_LABELS.put("word", "Word of mouth");
        HEAR_ABOUT_LABELS.put("social", "Social Media");
        HEAR_ABOUT_LABELS.put("other", "Other");

        CONTACT_BY_LABELS.put("email_or_postal", "Email or postal mail");
        CONTACT_BY_LABELS.put("email", "Email");
        CONTACT_BY_LABELS.put("postal", "Postal mail");

        ANNOUNCEMENT_LABELS.put("like", "YES, I'd like that.");
        ANNOUNCEMENT_LABELS.put("email_announcements",
                "YES, please send me email announcements.");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Must be set BEFORE getParameter(), otherwise Tomcat
        // decodes form fields as ISO-8859-1 (breaks Vietnamese: ỳ → á»³).
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Form mới không gửi hidden "action", nên POST = submit (add),
        // GET (mở /emailList trực tiếp trên trình duyệt) = chỉ xem form (join).
        String action = request.getParameter("action");
        if (action == null) {
            action = "POST".equals(request.getMethod()) ? "add" : "join";
        }

        String url = "/index.html";

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.html"; // the "join" page
        } else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String hearAbout = request.getParameter("hearAbout");
            String contactBy = request.getParameter("contactBy");
            String[] announcements = request.getParameterValues("announcements");

            // store data in User object and save User object in db
            User user = new User(firstName, lastName, email);
            UserDB.insert(user);

            // set User object in request object and set URL
            request.setAttribute("user", user);

            // giá trị hiển thị cho trang cảm ơn
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("dob", dob);
            request.setAttribute("hearAbout",
                    HEAR_ABOUT_LABELS.getOrDefault(hearAbout, hearAbout));
            request.setAttribute("contactBy",
                    CONTACT_BY_LABELS.getOrDefault(contactBy, contactBy));

            List<String> announcementLabels = new ArrayList<>();
            if (announcements != null) {
                for (String a : announcements) {
                    announcementLabels.add(ANNOUNCEMENT_LABELS.getOrDefault(a, a));
                }
            }
            request.setAttribute("announcements", announcementLabels);

            url = "/thanks.jsp";
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}