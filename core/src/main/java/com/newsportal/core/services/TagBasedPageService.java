package com.newsportal.core.services;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.newsportal.core.models.BlogPageModel;
import com.newsportal.core.models.JcrTitleModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


@Component(service = TagBasedPageService.class, immediate = true)
public class TagBasedPageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagBasedPageService.class);

    public List<BlogPageModel> getPagesByTag(String mainTagName, String childTagName, ResourceResolver resolver) {
        List<BlogPageModel> blogPageModels = new ArrayList<>();

        if (StringUtils.isBlank(mainTagName) || StringUtils.isBlank(childTagName) || resolver == null) {
            LOGGER.error("Invalid input parameters provided: mainTagName={}, childTagName={}, resolver={}", mainTagName, childTagName, resolver);
            return blogPageModels;
        }

        try {
            PageManager pageManager = resolver.adaptTo(PageManager.class);
            TagManager tagManager = resolver.adaptTo(TagManager.class);

            if (Objects.nonNull(tagManager) && Objects.nonNull(pageManager)) {
                Tag mainTag = tagManager.resolve(mainTagName);
                if (Objects.nonNull(mainTag)) {
                    Iterator<Resource> taggedResourcesIterator = mainTag.find();

                    while (taggedResourcesIterator.hasNext()) {
                        Resource pageResource = taggedResourcesIterator.next();
                        Page page = pageManager.getContainingPage(pageResource);

                        if (Objects.nonNull(page)) {
                            String[] pageTags = page.getProperties().get("cq:tags", String[].class);
                            Tag childTag = tagManager.resolve(childTagName);

                            if (Objects.nonNull(childTag) && Objects.nonNull(pageTags)) {
                                String childTagId = StringUtils.removeStart(childTagName, "/content/cq:tags/");
                                for (String pageTag : pageTags) {
                                    String normalizedPageTag = StringUtils.removeStart(pageTag, "/content/cq:tags/");
                                    if (childTagId.equals(normalizedPageTag)) {
                                        BlogPageModel blogPageModel = page.getContentResource().adaptTo(JcrTitleModel.class).getBlogPageModel();
                                        if (Objects.nonNull(blogPageModel)) {
                                            blogPageModels.add(blogPageModel);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching pages by tag", e);
        }
        return blogPageModels;
    }
}
