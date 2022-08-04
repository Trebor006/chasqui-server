package com.mibu.chasquiserver.utils;

public class ProductConstants {
    public static final String INDEX = "products";
    public static final String PRODUCTS_SEARCH = "/" + INDEX + "/_search";
    public static final String PRODUCTS_DOC = "/" + INDEX + "/_doc";
    public static final String QUERY_SEARCH_BY_TITLE = "{\n" +
            "  \"query\": {\n" +
            "    \"match\": {\n" +
            "      \"title\": \" %s \"\n" +
            "    }\n" +
            "  }\n" +
            "}\n";
}
