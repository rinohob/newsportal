package com.newsportal.core.models;

import com.adobe.cq.export.json.ComponentExporter;

public interface ProductCard  extends ComponentExporter {
    public String getContainerTitle();
    public String getDescription();
    public boolean isArticleStatus();
    public String getTitleType();
    public String getExportedType();
}
