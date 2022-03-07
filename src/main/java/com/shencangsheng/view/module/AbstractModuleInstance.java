/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: AbstractModuleInstance
 * Author:   shencangsheng
 * Date:     2022/3/7 4:50 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.module;

import com.shencangsheng.view.mappings.AbstractPropertyInstance;

import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
public abstract class AbstractModuleInstance {
    public Map<String, AbstractPropertyInstance> modules;

    public AbstractPropertyInstance getModule(String key) {
        return Objects.requireNonNull(modules.get(key), format("Type %s was not found", key));
    }
}
