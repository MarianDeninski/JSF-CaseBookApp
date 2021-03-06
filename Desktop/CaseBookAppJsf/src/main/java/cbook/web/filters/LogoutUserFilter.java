package cbook.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter({
        "/faces/view/home.xhtml",
        "/faces/view/logout.xhtml",
        "/faces/view/friends.xhtml",
        "/faces/view/profile-page.xhtml",
        "/home",
        "/logout",
        "/friends",
        "/profile-page"

})
public class LogoutUserFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

            System.out.println("I am in the filter");
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            HttpSession session = req.getSession();

            if (session.getAttribute("username") == null) {
                resp.sendRedirect("/index");
            } else {
                chain.doFilter(req, resp);
            }
        }

}
