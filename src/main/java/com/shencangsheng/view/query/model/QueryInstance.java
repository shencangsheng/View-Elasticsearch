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

import lombok.AllArgsConstructor;
import lombok.Data;

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
public class QueryInstance {
    private String module;
    private List<AbstractQuery> query;
}
