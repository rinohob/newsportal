package com.newsportal.core.servlets;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;


@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "/bin/newportal/121",
        extensions = {"txt", "json"},
        methods = {"GET"},
        selectors = {"test"}
)
public class WcmApiTesingServlet extends SlingAllMethodsServlet {
    private static final Logger Log = LoggerFactory.getLogger(WcmApiTesingServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource("/content/newsportal/us/en/cricket/ODI");

        if (resource != null) {
            Iterator<Resource> childern = resource.listChildren();
            StringBuilder childersPath = new StringBuilder();
            while (childern.hasNext()) {
                Resource child = childern.next();
                childersPath.append(child.getPath()).append("\n");
            }
            response.getWriter().write(childersPath.toString());
        } else {
            response.getWriter().write("no childfound");
        }
    }
}
