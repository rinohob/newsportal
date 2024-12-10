//package com.newsportal.core.models;
//
//import io.wcm.testing.mock.aem.junit5.AemContext;
//import io.wcm.testing.mock.aem.junit5.AemContextExtension;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith({AemContextExtension.class})
//class ProductCardsModelTest {
//
//    AemContext context = new AemContext();
//
//    ProductCardsModel productCardsModel;
//
//    ProductCardsListModel productCardsListModel;
//
//    @BeforeEach
//    void setUp() {
//        context.load().json("/com/newsportal/core/models/productCardsModel.json", "/content");
//        context.addModelsForClasses(ProductCardsModel.class);
//        context.currentResource("/content/products");
//        productCardsModel = context.request().adaptTo(ProductCardsModel.class);
//    }
//
//
//    @Test
//    void testContainer() {
//        assertEquals("Description of product cards", productCardsModel.getContainerDescription());
//        assertEquals("Product Cards Container", productCardsModel.getContainerTitle());
//        assertEquals("active", productCardsModel.getStatus());
//        assertEquals("Electronics", productCardsModel.getCategory());
//        assertEquals("newsportal/components/productcards", productCardsModel.getExportedType());
//
//    }
//
//
//    @Test
//    void testProductCard(){
//
//       List<ProductCardsListModel>  productCardsListModels =productCardsModel.getProductcardsList();
//
//
//
//
//
//    }
//
//
//
//}