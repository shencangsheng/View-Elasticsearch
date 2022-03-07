/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: Query
 * Author:   shencangsheng
 * Date:     2022/3/7 3:37 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.query.model;

import com.shencangsheng.view.mappings.enums.QueryBoolEnum;
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
public class Query {
    private String key;
    private QueryBoolEnum boolType = QueryBoolEnum.FILTER;
    private List terms;
    private SuperRangeQuery<Number> range;
    private List<Query> bucket;
}
