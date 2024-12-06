//package com.newsportal.core.servlets;
//
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ValueMap;
//import org.apache.sling.api.servlets.SlingAllMethodsServlet;
//import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
//import org.osgi.service.component.annotations.Component;
//
//import javax.servlet.Servlet;
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//@Component(service = Servlet.class)
//@SlingServletResourceTypes(resourceTypes = "/bin/newsportal/umesh",
//        extensions = {"txt", "xml"},
//        methods = {"GET", "PUT"},
//        selectors = {"reedy"}
//)
//public class ValueMapTesting extends SlingAllMethodsServlet {
//    @Override
//    protected void doGeneric(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//        Resource resource = request.getResource();
//        ValueMap valueMap = resource.getValueMap();
//        response.getWriter().write("diwudugQ] Z 0" +
//                "" +
//                "+" +
//                "0" +
//                "]'[;PLOKIJHG FDSA` Zbgpweiogfvn");
//    }
//}
