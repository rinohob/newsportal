package com.newsportal.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.Resource;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class AricalPages {

    @ValueMapValue
    public String Title;

    public String getTitle() {
        return Title;
    }
}
