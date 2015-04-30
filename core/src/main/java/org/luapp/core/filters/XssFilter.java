package org.luapp.core.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class XssFilter implements Filter {

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession(false);

        if (session != null) {
            Enumeration<?> names = session.getAttributeNames();

            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                System.out.println("=============================" + name + ":" + session.getAttribute(name));
            }
        }

        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
    }

    private static class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private final static Whitelist USER_CONTENT_FILTER = new Whitelist();

        static {
            USER_CONTENT_FILTER.addTags("embed", "object", "param", "span", "div");
            USER_CONTENT_FILTER.addAttributes(":all", "style", "class", "id", "name");
            USER_CONTENT_FILTER.addAttributes("object", "width", "height", "classid", "codebase");
            USER_CONTENT_FILTER.addAttributes("param", "name", "value");
            USER_CONTENT_FILTER.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen",
                    "allowScriptAccess", "flashvars", "name", "type", "pluginspage");
        }

        public XssHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getHeader(String name) {
            String value = super.getHeader(name);
            if (value == null) {
                return null;
            } else {
                return cleanXss(value);
            }
        }

        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);
            if (value == null) {
                return null;
            } else {
                return cleanXss(value);
            }
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            if (values == null) {
                return null;
            }

            String[] encodedValues = new String[values.length];

            for (int i = 0; i < values.length; i++) {
                encodedValues[i] = cleanXss(values[i]);
            }

            return encodedValues;
        }

        private String cleanXss(String value) {
            if (StringUtils.isBlank(value))
                return "";
            return Jsoup.clean(value, USER_CONTENT_FILTER);
        }
    }
}
