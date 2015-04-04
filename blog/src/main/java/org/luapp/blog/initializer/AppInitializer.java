package org.luapp.blog.initializer;

import org.luapp.blog.config.AppConfig;
import org.luapp.blog.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by lumeng on 2015/4/4.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final static String CONFIG_LOCATION = "org.luapp.blog.config";
    private final static String MAPPING_URL = "/*";
    private final static String DISPATCH_SERVLET_NAME = "DispatcherServlet";

//    public void onStartup(ServletContext servletContext) throws ServletException {
//        WebApplicationContext context = getContext();
//        servletContext.addListener(new ContextLoaderListener(context));
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCH_SERVLET_NAME, new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping(MAPPING_URL);
//    }
//
//    private AnnotationConfigWebApplicationContext getContext() {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setConfigLocation(CONFIG_LOCATION);
//        return context;
//    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{MAPPING_URL};
    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }
}
