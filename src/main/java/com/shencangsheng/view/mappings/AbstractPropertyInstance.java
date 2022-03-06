/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: AbstractPropertyInstance
 * Author:   shencangsheng
 * Date:     2022/3/7 12:40 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.mappings;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
@Getter
public abstract class AbstractPropertyInstance {
    @Setter
    private String path;
    private boolean isNested = false;
    private Map<String, PropertyInstance> properties = new HashMap<>();

    public void setNested(String path) {
        this.isNested = true;
        this.path = path;
    }

    public AbstractPropertyInstance add(String name, PropertyInstance model) {
        if (Objects.nonNull(path)) {
            model.setKey(path + "." + model.getKey());
        }
        properties.put(name, model);
        return this;
    }
}
