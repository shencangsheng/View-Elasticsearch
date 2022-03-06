/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: NestedPropertyInstance
 * Author:   shencangsheng
 * Date:     2022/3/7 1:35 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.module;

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
public class NestedPropertyInstance extends AbstractPropertyInstance {
    public NestedPropertyInstance() {
        setNested("state");
        add("create_date", new PropertyInstance("create_date", QueryPropertyEnum.DATE));
        add("type", new PropertyInstance("type", QueryPropertyEnum.KEYWORD));
    }
}
