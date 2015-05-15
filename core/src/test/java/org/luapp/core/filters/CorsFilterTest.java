package org.luapp.core.filters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by lum on 2015/5/12.
 */
@RunWith(MockitoJUnitRunner.class)
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
        MockHttpServletResponse rsp = new MockHttpServletResponse();

        filter.doFilter(req, rsp, chain);

        assertThat(rsp.getHeader("Access-Control-Allow-Origin")).isEqualTo("*");
        assertThat(rsp.getHeader("Access-Control-Allow-Methods")).isEqualTo("GET,POST,DELETE,OPTIONS");
        assertThat(rsp.getHeader("Access-Control-Max-Age")).isEqualTo("3600");
        assertThat(rsp.getHeader("Access-Control-Allow-Headers")).isEqualTo("Origin, X-Requested-With, Content-Type, Accept, Cache-Control");

        //You can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

//        //following throws runtime exception
//        System.out.println(mockedList.get(1));
//
//        //following prints "null" because get(999) was not stubbed
//        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
        //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

}
