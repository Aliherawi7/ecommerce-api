package com.ecommerce.ecommerceapi.constant;

public class APIEndpoints {
    private APIEndpoints() {
    }


    public final static String BASE_URL = "https://master.demo.sylius.com/";

    /* Customer Endpoints*/
    public final static String SHOP_CUSTOMERS = "/api/v2/shop/customers";
    public final static String ADMIN_CUSTOMERS = "/api/v2/admin/customers";

    /* Product Endpoints*/
    public final static String ADD_PRODUCT = "/api/v2/admin/products";
    public final static String PRODUCT_INFO = "/api/v2/shop/products/";

    /* Authentication Endpoints*/
    public final static String ADMIN_TOKEN = "/api/v2/admin/authentication-token";
    public final static String SHOP_CUSTOMER_TOKEN = "/api/v2/shop/authentication-token";


}


