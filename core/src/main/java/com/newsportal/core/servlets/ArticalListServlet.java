package com.newsportal.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
//pathBased
//@SlingServletPaths(value ={"/bin/newsportal/articallist","/bin/newsportal/articallist34"})

@SlingServletResourceTypes(resourceTypes = "/bin/newsportal/articallist", extensions = {"txt", "json"}, selectors = {"umesh", "reddy"}, methods = {"GET", "PUT"})
public class ArticalListServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("this response from the Dogetmethod");
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("this response from the doPost");
    }

    @Override
    protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("this response from the doPut");
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("this response from the doDelete");
    }
}
