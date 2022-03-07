/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: MappingInstance
 * Author:   shencangsheng
 * Date:     2022/3/6 11:49 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.mappings;

import com.shencangsheng.view.mappings.enums.AggregationPropertyEnum;
import com.shencangsheng.view.mappings.enums.QueryBoolEnum;
import com.shencangsheng.view.mappings.enums.QueryPropertyEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/6
 * @since 1.0.0
 */
@Getter
public class PropertyInstance {
    @Setter
    private String key;
    private QueryPropertyEnum queryType;
    private AggregationPropertyEnum aggregationType;
    private QueryBoolEnum bool = QueryBoolEnum.FILTER;
    private Integer minimumShouldMatch = 1;
    @Setter
    private PropertyInstance next;

    public PropertyInstance(String key, AggregationPropertyEnum aggregationType) {
        this.key = key;
        this.aggregationType = aggregationType;
    }

    public PropertyInstance(String key, QueryPropertyEnum type) {
        this.key = key;
        this.queryType = type;
        this.aggregationType = type.getAggregationProperty();
    }

    public PropertyInstance(String key, QueryPropertyEnum type, PropertyInstance next) {
        this(key, type);
        this.next = next;
    }

    public PropertyInstance setAggregationType(AggregationPropertyEnum aggregationType) {
        this.aggregationType = aggregationType;
        return this;
    }
}
