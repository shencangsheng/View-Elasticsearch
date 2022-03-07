/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: QueryDefaultInstance
 * Author:   shencangsheng
 * Date:     2022/3/7 1:08 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.module;

import com.shencangsheng.view.mappings.AbstractPropertyInstance;
import com.shencangsheng.view.mappings.PropertyInstance;
import com.shencangsheng.view.mappings.enums.AggregationPropertyEnum;
import com.shencangsheng.view.mappings.enums.QueryPropertyEnum;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
public class TemplatePropertyInstance extends AbstractPropertyInstance {

    public TemplatePropertyInstance() {
        add("order_no", new PropertyInstance("order_no", QueryPropertyEnum.STRING));
        add("order_create_date", new PropertyInstance("order_create_date", QueryPropertyEnum.DATE));
        add("countries", new PropertyInstance("countries", QueryPropertyEnum.GROUP,
            new PropertyInstance("city", QueryPropertyEnum.GROUP,
                new PropertyInstance("provinces", QueryPropertyEnum.KEYWORD))));

        PropertyInstance userIdPropertyInstance = new PropertyInstance("user_id", AggregationPropertyEnum.CARDINALITY);
        add("user_id", userIdPropertyInstance);
        add("user_name", new PropertyInstance("user_name", QueryPropertyEnum.WILDCARD));
        add("age", new PropertyInstance("age", QueryPropertyEnum.LONG));
        add("gender", new PropertyInstance("gender", QueryPropertyEnum.KEYWORD, userIdPropertyInstance).setAggregationType(AggregationPropertyEnum.TERMS_CARDINALITY));
    }
}
