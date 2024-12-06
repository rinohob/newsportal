package com.newsportal.core.servlets;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "/bin/newsportal/modifiblr")
public class ModifableVlaueMap extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        Page page = pageManager.getPage("/content/newsportal/us/en/cricket/ODI");
        String name = page.getPath();
        Resource  resource=resourceResolver.getResource(name);
        ValueMap valueMap=resource.getValueMap();
        String name1=valueMap.get("jcr:primaryType",String.class);
        response.getWriter().write(name1);
    }
}
