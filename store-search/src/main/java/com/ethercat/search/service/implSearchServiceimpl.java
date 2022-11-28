package com.ethercat.search.service;

import com.ethercat.doc.ProductDoc;
import com.ethercat.param.ProductSearchParam;
import com.ethercat.pojo.Product;
import com.ethercat.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-19 10:36
 **/

@Service
@Slf4j
public class implSearchServiceimpl implements SearchService{

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 根据关键字和分页进行数据库查询
     *  1.判断关键字是否为null null查全部
     *  2.添加分页属性
     *  3. es查询
     *  4.结果处理
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        SearchRequest searchRequest = new SearchRequest("product");

        String search = productSearchParam.getSearch();

        if (StringUtils.isEmpty(search)){
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else {
            searchRequest.source().query(QueryBuilders.matchQuery("all",search));
        }

        //进行分页数据的添加
        log.info("implSearchServiceimpl.search业务开始，结果：{}",(productSearchParam.getCurrentPage()-1)*productSearchParam.getPageSize());
        log.info("implSearchServiceimpl.search业务开始，PageSize结果：{}",productSearchParam.getPageSize());
        log.info("implSearchServiceimpl.search业务开始，search结果：{}",search);
        searchRequest.source().from((productSearchParam.getCurrentPage()-1)*productSearchParam.getPageSize());
        searchRequest.source().size(productSearchParam.getPageSize());

        SearchResponse searchResponse = null;
        try {
             searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询错误");
        }

        SearchHits hits = searchResponse.getHits();

        long total = hits.getTotalHits().value;

        SearchHit[] hitsHits = hits.getHits();

        ArrayList<Product> productList = new ArrayList<>();

        //json处理器
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit hitsHit : hitsHits) {
            String sourceAsString = hitsHit.getSourceAsString();

            Product product = null;
            try {
                //productDoc all - product 如果没有all属性，就会报错，jackson提供忽略没有属性的注解
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            productList.add(product);
        }


        R ok = R.ok(null, productList, total);
        log.info("implSearchServiceimpl.search业务结束，结果：{}",ok);

        return ok;
    }

    /**
     * 商品同步： 插入更新
     *
     * @param product
     * @return
     */
    @Override
    public R save(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());

        ProductDoc productDoc = new ProductDoc(product);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(productDoc);

        indexRequest.source(json, XContentType.JSON);

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        return R.ok("数据同步成功！");
    }

    /**
     * 进行es库的商品删除
     *
     * @param productId
     * @return
     */
    @Override
    public R remove(Integer productId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("product").id(productId.toString());

        restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);

        return R.ok("es库数据删除成功！");
    }
}
