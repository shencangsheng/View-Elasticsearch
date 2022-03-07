/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: QueryStd
 * Author:   shencangsheng
 * Date:     2022/3/7 3:21 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.query;

import com.shencangsheng.view.query.model.AbstractRangeQuery;
import org.elasticsearch.index.query.*;

import java.util.Collection;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
public class QueryBuilderUtil {
    public static TermsQueryBuilder terms(String name, Collection<?> value) {
        return QueryBuilders.termsQuery(name, value);
    }

    public static TermQueryBuilder term(String name, Object value) {
        return QueryBuilders.termQuery(name, value);
    }

    public static WildcardQueryBuilder wildcard(String name, String value) {
        return QueryBuilders.wildcardQuery(name, value);
    }

    public static RangeQueryBuilder range(String name, AbstractRangeQuery range) throws Exception {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(name);
        if (Objects.nonNull(range.getGt())) {
            rangeQueryBuilder.gt(range.getGt());
        }
        if (Objects.nonNull(range.getGte())) {
            rangeQueryBuilder.gte(range.getGte());
        }
        if (Objects.nonNull(range.getLt())) {
            rangeQueryBuilder.lt(range.getLt());
        }
        if (Objects.nonNull(range.getLte())) {
            rangeQueryBuilder.lte(range.getLte());
        }
        return rangeQueryBuilder;
    }

}
