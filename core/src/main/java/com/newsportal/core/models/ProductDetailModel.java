package com.newsportal.core.models;

public interface ProductDetailModel {

    String getCategory();
    String[] getProductTags();

    // New methods to read component details
    String getImagePath();
    String getTitle();
    String getText();
    String getButtonText();
    String getButtonLink();
}
