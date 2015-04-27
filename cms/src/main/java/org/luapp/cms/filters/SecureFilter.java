package org.luapp.cms.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lum on 2015/4/27.
 */
public class SecureFilter implements Filter {
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse rp = (HttpServletResponse) response;
            rp.addHeader("X-Frame-Options", "SAMEORIGIN");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
