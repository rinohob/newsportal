package com.newsportal.core.models;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class





})
public class FotterNavigationModel {
    private static final Logger log = LoggerFactory.getLogger(FotterNavigationModel.class);
    private  String test2;

    @SlingObject
    ResourceResolver resourceResolver;

    public String getTest2() {
        return test2;
    }

    @SlingObject
    Resource resource; // inject the Resource object

    List<String> items;

    private String currentPagePath;

    @PostConstruct
    protected void init() {
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        Page currentpage = Optional.ofNullable(pageManager)
            .map(pm -> pm.getContainingPage(resource)).orElse(null);
           currentPagePath= Optional.ofNullable(currentpage)
           .map(Page::getPath).orElse("");
            
             test2  =  currentpage.getTitle();
           
    }


}
