package ru.vyazankin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private ServletConfig servletConfig;
    private static final Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("init servlet FirstServlet");
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("request:" + servletRequest);
        servletResponse.getWriter().println("<h1>Hello world</h1>");
        logger.info("response:" + servletResponse);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        logger.info("destroy servlet FirstServlet");
    }
}
