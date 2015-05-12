package org.luapp.core.filters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by lum on 2015/5/12.
 */
public class CorsFilterTest {

    public static String log;

    @Rule
    public TestRule globalTimeout = Timeout.seconds(2);

    @Test
    public void test() {
//        log+= "ran1";
//        for(;;) {}
    }

    @Test
    public void testFilter() throws IOException, ServletException {
        CorsFilter filter = new CorsFilter();

        MockFilterChain chain = new MockFilterChain();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse rsp =new MockHttpServletResponse();

        filter.doFilter(req,rsp,chain);

        System.out.println(rsp.getHeader("Access-Control-Allow-Origin")) ;
    }

}
