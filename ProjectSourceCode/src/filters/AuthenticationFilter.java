package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "filters.AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        Cookie[] cookies = request.getCookies();
        String userlogin = "";
        if(cookies == null){
            chain.doFilter(req, resp);
        }
        else {
            for (Cookie cook : cookies) {
                System.out.println(" cookie : name = " + cook.getName() + " val = " + cook.getValue());
                if (cook.getName().equals("access")) {
                    userlogin = cook.getValue();
                    break;
                }
            }
            if (userlogin.equals("")) {
                //response.sendRedirect("./project/enter.jsp");
            }
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
