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
public abstract class AbstractQuery<T> {
    private String key;
    private QueryBoolEnum boolType;
    private T value;
}
