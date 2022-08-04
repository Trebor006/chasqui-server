package com.mibu.chasquiserver.service;

import com.google.gson.Gson;
import com.mibu.chasquiserver.dto.ChildResponse;
import com.mibu.chasquiserver.dto.ElasticResponse;
import com.mibu.chasquiserver.dto.ProductDto;
import com.mibu.chasquiserver.utils.ProductConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {
    @Autowired
    private ElasticService elasticService;

    public List<ProductDto> getProductFromElastic(String productName) {
        String query = String.format(ProductConstants.QUERY_SEARCH_BY_TITLE, productName);
        String resultStringJson = elasticService.callPost(ProductConstants.PRODUCTS_SEARCH, query);

        return parseResponse(resultStringJson);
    }

    private List<ProductDto> parseResponse(String resultStringJson) {
        Gson g = new Gson();
        ElasticResponse elasticResponse = g.fromJson(resultStringJson, ElasticResponse.class);

        return getProducts(elasticResponse);
    }

    private List<ProductDto> getProducts(ElasticResponse elasticResponse) {
        return elasticResponse.getHits().getHits().stream().map(ChildResponse::get_source).collect(Collectors.toList());
    }

    public ProductDto saveNewProduct(String title, String type) {
        Gson g = new Gson();
        ProductDto toSaveProduct = new ProductDto();
        toSaveProduct.setTitle(title);
        toSaveProduct.setType(type);

        String query = g.toJson(toSaveProduct);

        String result = elasticService.callPost(ProductConstants.PRODUCTS_DOC, query);

        return toSaveProduct;
    }
}
