package com.newsportal.core.models;


import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = ProductCardsModel.RESORCE_TYPE, adapters = ComponentExporter.class)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class ProductCardsModel implements ComponentExporter {

    protected static final String RESORCE_TYPE = "newsportal/components/productcards";
    protected static final String PATH = "/jcr:content/data/master";

    @ValueMapValue
    private String containerTitle;

    @SlingObject
    ResourceResolver resolver;

    @ValueMapValue
    private String containerDescription;

    @ValueMapValue
    private String status;

    @ValueMapValue
    private String category;

    @ValueMapValue
    private boolean loadProductsFromCF;

    @ValueMapValue
    private String[] cfProductPaths;


    @ChildResource
    List<ProductCardsListModel> productCardsList;


    @PostConstruct
    List<ProductCardsListModel> init() {
        if (loadProductsFromCF == true && cfProductPaths != null) {
            productCardsList = new ArrayList<>();
            for (String path : cfProductPaths) {
                Resource resource = resolver.getResource(path + PATH);
                if (resource != null) {
                    ProductCardsListModel cfList = resource.adaptTo(ProductCardsListModel.class);
                    if (cfList != null) {
                        productCardsList.add(cfList);
                    }
                }
            }
        }
        return productCardsList;
    }

    public String getContainerTitle() {
        return containerTitle;
    }

    public String getContainerDescription() {
        return containerDescription;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public List<ProductCardsListModel> getProductcardsList() {
        return productCardsList;
    }

    @Override
    public String getExportedType() {
        return RESORCE_TYPE;
    }
}