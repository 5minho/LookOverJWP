package spms.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 7. 31..
 */
@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name="encoding", value="UTF-8")})
public class ChracterEncodingFilter implements Filter {
    FilterConfig filterConfig;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(filterConfig.getInitParameter("encoding"));
        // 서블릿이 실행되기 전에 해야할 작업

        chain.doFilter(req, resp); // 다음 필터를 호출하고 더 이상 필터가 없다면 서블릿의 service() 호출

        // 서블릿을 실행한 후, 클라이언트에게 응답하기 전에 해야할 작업
    }

    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

}
