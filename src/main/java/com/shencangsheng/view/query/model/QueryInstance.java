/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: QueryInstance
 * Author:   shencangsheng
 * Date:     2022/3/7 4:37 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.query.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shencangsheng.view.annotation.ModuleInstanceAnnotation;
import com.shencangsheng.view.module.query.TemplateModuleInstance;
import com.shencangsheng.view.query.deserializer.QueryModuleDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ModuleInstanceAnnotation(moduleInstance = TemplateModuleInstance.class)
@JsonDeserialize(using = QueryModuleDeserializer.class)
public class QueryInstance {
    private String module;
    private List<SuperQuery> query;
}
