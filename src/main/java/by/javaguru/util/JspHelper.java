package by.javaguru.util;

import static java.lang.String.*;

public class JspHelper {
    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String getPath(String jspName) {
        return format(JSP_FORMAT, jspName);
    }
}
