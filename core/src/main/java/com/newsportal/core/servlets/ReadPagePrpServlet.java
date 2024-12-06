//package com.newsportal.core.servlets;
//
//import com.day.cq.wcm.api.Page;
//import com.day.cq.wcm.api.PageManager;
//import com.newsportal.core.constants.NewsPortalConstants;
//import com.newsportal.core.utils.NewsportalUtilService;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ResourceResolver;
//import org.apache.sling.api.resource.ValueMap;
//import org.apache.sling.api.servlets.SlingAllMethodsServlet;
//import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.Servlet;
//import javax.servlet.ServletException;
//import java.io.IOException;
//import java.util.Objects;
//
//
//@Component(service = Servlet.class)
//@SlingServletResourceTypes(resourceTypes = "newsportal/components/page", extensions = "html")
//@SlingServletResourceTypes(resourceTypes = "/bin/newsportal/modifiblre")
//public class ReadPagePrpServlet extends SlingAllMethodsServlet {
//
//    private static final Logger LOG = LoggerFactory.getLogger(ReadPagePrpServlet.class);
//
//    @Reference
//    NewsportalUtilService newsportalUtilService;
//
//    @Override
//    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//
//        try {
//            String pagePath = request.getPathInfo().replaceAll(".html", "");
//            ResourceResolver resolver = newsportalUtilService.getResourceResolver(NewsPortalConstants.SYSTEM_SERVICE);
//            Resource resource = resolver.getResource(pagePath + NewsPortalConstants.JCR_CONTENT);
//            if (Objects.nonNull(resource)) {
//                ValueMap properties = resource.getValueMap();
//                for (String key : properties.keySet()) {
//                    Object value = properties.get(key);
//                    LOG.info("{}: {}", key, value);
//                }
//            }
//        } catch (Exception e) {
//            LOG.error("getting error :", e);
//        }
//        response.getWriter().write("hjjkh");
//    }
//}
package com.newsportal.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.newsportal.core.constants.NewsPortalConstants;
import com.newsportal.core.utils.NewsportalUtilService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Objects;


@Component(service = Servlet.class)
//@SlingServletResourceTypes(resourceTypes = "newsportal/components/pages", extensions = "html")
@SlingServletPaths("/bin/newsportal/pages")
public class ReadPagePrpServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ReadPagePrpServlet.class);

    @Reference
    NewsportalUtilService newsportalUtilService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
            IOException {

        try {
           String pagePath = request.getParameter("pagePath");
           if(StringUtils.isBlank(pagePath)){
               LOG.info("page Path is Empty");
               response.getWriter().write("pagePath is Empty");
           }
            ResourceResolver resolver = newsportalUtilService.getResourceResolver(NewsPortalConstants.SYSTEM_SERVICE);
            if (Objects.nonNull(resolver)) {
            PageManager pageManager = resolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage(pagePath);
            Resource resource = resolver.getResource(pagePath + NewsPortalConstants.JCR_CONTENT);
            if (Objects.nonNull(resource)) {
                ValueMap properties = resource.getValueMap();
                for (String key : properties.keySet()) {
                    Object value = properties.get(key);
                    LOG.info("{}: {}", key, value);
                }
              }
            }
        } catch (Exception e) {
            LOG.error("getting error :", e);
        }
        response.getWriter().write("hjjkhdbfsg");
    }
}
