package com.github.chizzaru.config;

import com.github.chizzaru.App;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/***
 * Create a DispatcherServlet Initializer
 * Create a class to initialize the DispatcherServlet.
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class, App.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
