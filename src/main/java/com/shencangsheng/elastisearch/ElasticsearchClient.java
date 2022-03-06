/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: ElasticsearchClient
 * Author:   shencangsheng
 * Date:     2022/3/6 11:25 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.elastisearch;

import lombok.Data;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/6
 * @since 1.0.0
 */
@Data
public class ElasticsearchClient {
    private RestHighLevelClient client;
    private TimeValue timeValue = TimeValue.timeValueMinutes(1);
    private SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    private SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);

    public SearchSourceBuilder createSearchSourceBuilder(final Integer size, final boolean _source) {
        return searchSourceBuilder.size(size).fetchSource(_source).timeout(timeValue);
    }

    public ElasticsearchClient setCollapse(final CollapseBuilder collapse) {
        searchSourceBuilder.collapse(collapse);
        return this;
    }

    public ElasticsearchClient setIndex(final String index) {
        searchRequest.indices(index);
        return this;
    }

    public ElasticsearchClient(final String index) {
        searchRequest.indices(index);
    }

    public ElasticsearchClient sort(final String name, final SortOrder order) {
        searchSourceBuilder.sort(name, order);
        return this;
    }

    public ElasticsearchClient setFetchSource(String[] includes, String[] excludes) {
        searchSourceBuilder.fetchSource(true);
        searchSourceBuilder.fetchSource(includes, excludes);
        return this;
    }

    public ElasticsearchClient setFrom(int from) {
        searchSourceBuilder.from(from);
        return this;
    }

    public ElasticsearchClient setQuery(QueryBuilder queryBuilder) {
        searchSourceBuilder.query(queryBuilder);
        return this;
    }

    public ElasticsearchClient addAggregation(AggregationBuilder aggregationBuilder) {
        searchSourceBuilder.aggregation(aggregationBuilder);
        return this;
    }

    public SearchResponse get() throws IOException {
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }
}
