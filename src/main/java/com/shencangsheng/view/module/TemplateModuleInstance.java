/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: TemplateModuleInstance
 * Author:   shencangsheng
 * Date:     2022/3/9 2:30 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.module;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/9
 * @since 1.0.0
 */
public class TemplateModuleInstance extends AbstractModuleInstance {

    public TemplateModuleInstance() {
        this.modules = Map.of("template", new TemplatePropertyInstance(),
            "nested", new NestedPropertyInstance());
    }
}
