package com.newsportal.core.models;

import com.newsportal.core.services.TagBasedPageService;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Getter
public class ArticalListingModelImpl {
    private static final  String BLOG_PATH = "newsportal:";

    @SlingObject
    private ResourceResolver resolver;


    @ValueMapValue
    private String rootPath;


    @ValueMapValue
    private String articleType;

    @ValueMapValue
    private String category;

    @ValueMapValue
    private String numberOfArticles;

    @OSGiService
    private TagBasedPageService tagBasedPageService;

    @Getter
    List<BlogPageModel> pagesList = new ArrayList<>();

    @PostConstruct
    public void init() {

        String articleTypeTag = BLOG_PATH + articleType;
        String categoryTag = category;

        List<BlogPageModel> tagPages = tagBasedPageService.getPagesByTag(articleTypeTag,categoryTag, resolver);
        if (tagPages != null) {
            int limit = getValidNumberOfTags();
            pagesList = tagPages.stream()
                    .limit(limit)
                    .collect(Collectors.toList());
        }
    }

    private int getValidNumberOfTags() {
        return StringUtils.isNumeric(numberOfArticles) ? Integer.parseInt(numberOfArticles) : Integer.MAX_VALUE;

    }
}