/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: ModuleInstance
 * Author:   shencangsheng
 * Date:     2022/3/9 2:24 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.annotation;

import com.shencangsheng.view.module.AbstractModuleInstance;

import java.lang.annotation.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/9
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleInstanceAnnotation {
    Class<? extends AbstractModuleInstance> moduleInstance();
}
