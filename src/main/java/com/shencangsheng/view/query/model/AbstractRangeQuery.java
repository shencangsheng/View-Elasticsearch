/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: AbstractRangeQuery
 * Author:   shencangsheng
 * Date:     2022/3/7 3:54 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.query.model;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
@Data
public abstract class AbstractRangeQuery<T> {
    private T gt;
    private T gte;
    private T lt;
    private T lte;
}
