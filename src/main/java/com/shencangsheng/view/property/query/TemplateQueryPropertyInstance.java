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
package com.shencangsheng.view.property.query;

import com.shencangsheng.view.mappings.AbstractPropertyInstance;
import com.shencangsheng.view.mappings.PropertyInstance;
import com.shencangsheng.view.mappings.enums.QueryPropertyEnum;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
public class TemplateQueryPropertyInstance extends AbstractPropertyInstance {

    public TemplateQueryPropertyInstance() {
        add("order_no", new PropertyInstance("order_no", QueryPropertyEnum.STRING));
        add("order_create_date", new PropertyInstance("order_create_date", QueryPropertyEnum.DATE));
        add("countries", new PropertyInstance("countries", QueryPropertyEnum.GROUP,
            new PropertyInstance("city", QueryPropertyEnum.GROUP,
                new PropertyInstance("provinces", QueryPropertyEnum.KEYWORD))));

        add("user_name", new PropertyInstance("user_name", QueryPropertyEnum.WILDCARD));
        add("age", new PropertyInstance("age", QueryPropertyEnum.LONG));
        add("number", new PropertyInstance("age", QueryPropertyEnum.DOUBLE));
        add("gender", new PropertyInstance("gender", QueryPropertyEnum.KEYWORD));
    }
}
