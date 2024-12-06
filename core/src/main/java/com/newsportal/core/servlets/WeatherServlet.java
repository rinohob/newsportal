// package com.newsportal.core.servlets;

// import com.day.crx.JcrConstants;
// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.servlets.HttpConstants;
// import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
// import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
// import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
// import org.osgi.service.component.annotations.Component;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import javax.naming.spi.Resolver;
// import javax.servlet.Servlet;
// import javax.servlet.ServletException;
// import java.io.IOException;

// @Component(service = { Servlet.class })
// @SlingServletResourceTypes(
//         resourceTypes="newsportal/components/page",
//         methods= HttpConstants.METHOD_GET,
//         extensions="txt")
// public class WeatherServlet extends SlingSafeMethodsServlet {
//     private static final Logger LOG= LoggerFactory.getLogger(WeatherServlet.class);
//     @Override
//     protected void doGet( SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException {
//       ResourceResolver resourceResolver= request.getResourceResolver();
//       Resource resource= resourceResolver.getResource("/newsportal/components/helloworld");
//      LOG.info(response.toString());

//     }
// }
