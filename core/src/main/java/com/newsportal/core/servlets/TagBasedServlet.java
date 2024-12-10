package com.newsportal.core.servlets;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.newsportal.core.models.BlogPageModel;
import com.newsportal.core.models.JcrTitleModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/newsportal/modifiblr2")
public class TagBasedServlet extends SlingSafeMethodsServlet {


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        try {
            JsonObject resultJson = new JsonObject();
            ResourceResolver resourceResolver = request.getResourceResolver();
            TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

            if (tagManager != null && pageManager != null) {
                String[] mainTags = {"newsportal:blogs", "newsportal:articals"};

                for (String mainTag : mainTags) {
                    JsonObject mainTagJson = new JsonObject();

                    Tag mainTagObj = tagManager.resolve(mainTag);
                    if (mainTagObj != null) {
                        Iterator<Resource> taggedResources = mainTagObj.find();

                        while (taggedResources.hasNext()) {
                            Resource pageResource = taggedResources.next();
                            Page page = pageManager.getContainingPage(pageResource);

                            if (page != null) {
                                JsonObject pageJson = new JsonObject();
                                BlogPageModel blogPageModel = page.getContentResource().adaptTo(JcrTitleModel.class).getBlogPageModel();

                                if (Objects.nonNull(blogPageModel)) {
                                    pageJson.addProperty("pageTitle", page.getTitle());
                                    pageJson.addProperty("componentTitle", blogPageModel.getTitle());
                                    pageJson.addProperty("componentDescription", blogPageModel.getDescription());
                                    pageJson.addProperty("componentImage", blogPageModel.getImage());

                                    String[] pageTags = page.getProperties().get("cq:tags", String[].class);
                                    if (Objects.nonNull(pageTags)) {
                                        for (String pageTag : pageTags) {
                                            if (pageTag.startsWith("newsportal:categories/")) {
                                                String subCategory = pageTag.substring("newsportal:categories/".length());

                                                if (!mainTagJson.has(subCategory)) {
                                                    mainTagJson.add(subCategory, new JsonArray());
                                                }
                                                mainTagJson.getAsJsonArray(subCategory).add(pageJson);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //Adds the mainTagJson to the result JSON using the tag name (excluding newsportal:) as the key
                    String tagKey = mainTag.substring("newsportal:".length());
                    resultJson.add(tagKey, mainTagJson);
                }
            }

            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(resultJson));
        } catch (Exception e) {
            JsonObject errorJson = new JsonObject();
            errorJson.addProperty("error", e.getMessage());
            response.getWriter().write(errorJson.toString());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
    }
}