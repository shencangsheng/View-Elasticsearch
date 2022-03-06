/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: QueryBoolEnum
 * Author:   shencangsheng
 * Date:     2022/3/6 11:47 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.mappings.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum QueryBoolEnum {
    FILTER,
    SHOULD,
    MUST_NOT;
}
