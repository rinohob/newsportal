package com.newsportal.core.servlets;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.newsportal.core.models.TagPageModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.StreamSupport;

@Component(service = Servlet.class)
@SlingServletPaths(value = TaggedPagesServlet.SERVLET_PATH)
public class TaggedPagesServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(TaggedPagesServlet.class);

    public static final String SERVLET_PATH = "/bin/newsportal/get-tagged-pages";
    private static final String BLOG_TAG_PATH = "/content/cq:tags/newsportal/blogs";
    private static final String ARTICLE_TAG_PATH = "/content/cq:tags/newsportal/articals";
    private static final String COMPONENT_RESOURCE_TYPE = "newsportal/components/tagComponent";
    private static final String QUERY_TEMPLATE =
            "SELECT * FROM [nt:base] AS node WHERE ISDESCENDANTNODE([%s]) AND node.[sling:resourceType] = '%s'";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        JsonObject resultObject = new JsonObject();
        JsonArray blogArray = new JsonArray();
        JsonArray articleArray = new JsonArray();
        ResourceResolver resourceResolver = request.getResourceResolver();

        try {
            TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
            if (tagManager != null) {
                processTag(tagManager.resolve(BLOG_TAG_PATH), blogArray, resourceResolver, "blog");
                processTag(tagManager.resolve(ARTICLE_TAG_PATH), articleArray, resourceResolver, "article");
            }

            resultObject.add("blogs", blogArray);
            resultObject.add("articles", articleArray);

            response.setContentType("application/json");
            response.getWriter().write(resultObject.toString());
        } catch (Exception e) {
            LOG.error("Error retrieving pages tagged with Blog or Article", e);
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"An error occurred while retrieving pages\"}");
        }
    }

    private void processTag(Tag tag, JsonArray resultArray, ResourceResolver resourceResolver, String type) {
        if (tag == null) return;

        Iterable<Resource> taggedResources = () -> tag.find();
        StreamSupport.stream(taggedResources.spliterator(), false).forEach(resource -> {
            JsonObject pageJson = new JsonObject();

            pageJson.addProperty("pagePath", resource.getPath());
            pageJson.addProperty("type", type);
//
//            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
//            Page page = pageManager != null ? pageManager.getContainingPage(resource) : null;
//            if (page != null) {
                TagPageModel model = findComponentModel(resource);
                if (model != null) {
                    pageJson.addProperty("title", model.getTitle());
                    pageJson.addProperty("description", model.getDescription());
//                }
            }

            resultArray.add(pageJson);
        });

    }
    private TagPageModel findComponentModel(Resource contentResource) {
        if (contentResource == null) return null;
        String query = String.format(QUERY_TEMPLATE, contentResource.getPath(), COMPONENT_RESOURCE_TYPE);
        Iterator<Resource> resources = contentResource.getResourceResolver().findResources(query, "JCR-SQL2");
        while (resources.hasNext()) {
            TagPageModel model = resources.next().adaptTo(TagPageModel.class);
            if (model != null) return model;
        }
        return null;
    }
}