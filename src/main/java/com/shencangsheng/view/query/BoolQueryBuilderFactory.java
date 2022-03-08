/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: BoolQueryBuilderFarctoy
 * Author:   shencangsheng
 * Date:     2022/3/7 3:30 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.query;

import com.shencangsheng.view.mappings.AbstractPropertyInstance;
import com.shencangsheng.view.mappings.PropertyInstance;
import com.shencangsheng.view.mappings.enums.QueryBoolEnum;
import com.shencangsheng.view.module.AbstractModuleInstance;
import com.shencangsheng.view.query.model.*;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

/**
 * Generate BoolQueryBuilder Factory
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
public class BoolQueryBuilderFactory {

    public static BoolQueryBuilder boolQueryBuilderFactory(List<QueryInstance> queryInstances, AbstractModuleInstance moduleInstance) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (QueryInstance queryInstance : queryInstances) {
            AbstractPropertyInstance propertyInstance = moduleInstance.getModule(queryInstance.getModule());
            if (propertyInstance.isNested()) {
                boolQueryBuilder.filter(nested(propertyInstance, queryInstance));
            } else {
                for (SuperQuery query : queryInstance.getQuery()) {
                    boolQueryBuilderFn(boolQueryBuilder, propertyInstance.getProperties().get(query.getKey()), query);
                }
            }
        }
        return boolQueryBuilder;
    }

    public static AbstractQueryBuilder boolQueryBuilderFn(BoolQueryBuilder boolQueryBuilder, PropertyInstance propertyInstance, SuperQuery query) throws Exception {
        AbstractQueryBuilder abstractQueryBuilder = queryBuilderFn(propertyInstance, query);
        switch (query.getBoolType()) {
            case FILTER:
                boolQueryBuilder.filter(abstractQueryBuilder);
                break;
            case SHOULD:
                boolQueryBuilder.minimumShouldMatch(propertyInstance.getMinimumShouldMatch());
                boolQueryBuilder.should(abstractQueryBuilder);
                break;
            case MUST_NOT:
                boolQueryBuilder.mustNot(abstractQueryBuilder);
                break;
            default:
                throw new IllegalArgumentException(String.format("Type %s was not found", query.getBoolType()));
        }
        return abstractQueryBuilder;
    }

    public static AbstractQueryBuilder queryBuilderFn(PropertyInstance propertyInstance, SuperQuery query) throws Exception {
        switch (propertyInstance.getQueryType()) {
            case KEYWORD:
            case STRING:
                return terms(propertyInstance, (TermQuery) query);
            case DATE:
            case LONG:
            case DOUBLE:
                return range(propertyInstance, (RangeQuery) query);
            case WILDCARD:
                return wildcard(propertyInstance, (TermQuery) query);
            case GROUP:
                return group(propertyInstance, (GroupQuery) query);
            default:
                throw new IllegalArgumentException(format("Type %s was not found", propertyInstance.getQueryType()));
        }
    }

    public static RangeQueryBuilder range(PropertyInstance instance, RangeQuery query) throws Exception {
        return QueryBuilderUtil.range(instance.getKey(), query.getValue());
    }

    public static TermsQueryBuilder terms(PropertyInstance instance, TermQuery query) throws Exception {
        return QueryBuilderUtil.terms(instance.getKey(), query.getValue());
    }

    public static BoolQueryBuilder wildcard(PropertyInstance instance, TermQuery query) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().minimumShouldMatch(1);
        query.getValue().forEach(element -> boolQueryBuilder.should(QueryBuilders.wildcardQuery(instance.getKey(), (String) element)));
        return boolQueryBuilder;
    }


    public static AbstractQueryBuilder group(PropertyInstance instance, GroupQuery query) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (SuperQuery element : query.getValue()) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().filter(QueryBuilderUtil.term(instance.getKey(), element.getKey()));
            boolQueryBuilderFn(queryBuilder, instance.getNext(), element);
            boolQueryBuilder.should(queryBuilder);
        }
        return boolQueryBuilder;
    }

    public static AbstractQueryBuilder nested(AbstractPropertyInstance propertyInstance, QueryInstance queryInstance) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder mustNotBoolQuery = null;
        BoolQueryBuilder boolQuery = null;
        for (SuperQuery query : queryInstance.getQuery()) {
            if (query.getBoolType() == QueryBoolEnum.MUST_NOT) {
                query.setBoolType(QueryBoolEnum.FILTER);
                if (Objects.isNull(mustNotBoolQuery)) {
                    mustNotBoolQuery = QueryBuilders.boolQuery();
                }
                boolQueryBuilderFn(mustNotBoolQuery, propertyInstance.getProperties().get(query.getKey()), query);
            } else {
                if (Objects.isNull(boolQuery)) {
                    boolQuery = QueryBuilders.boolQuery();
                }
                boolQueryBuilderFn(boolQuery, propertyInstance.getProperties().get(query.getKey()), query);
            }
        }
        if (Objects.nonNull(boolQuery)) {
            boolQueryBuilder.filter(QueryBuilders.nestedQuery(propertyInstance.getPath(), boolQuery, ScoreMode.None));
        }
        if (Objects.nonNull(mustNotBoolQuery)) {
            boolQueryBuilder.mustNot(QueryBuilders.nestedQuery(propertyInstance.getPath(), boolQuery, ScoreMode.None));
        }
        return boolQueryBuilder;
    }
}
