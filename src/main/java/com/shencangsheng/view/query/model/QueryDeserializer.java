/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: QueryDeserializer
 * Author:   shencangsheng
 * Date:     2022/3/8 9:40 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.view.query.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/8
 * @since 1.0.0
 */
public class QueryDeserializer extends StdDeserializer<SuperQuery> {

    public QueryDeserializer() {
        this(null);
    }

    protected QueryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SuperQuery deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNodeType jsonNodeType = node.get("value").getNodeType();
        Type type = jsonNodeType.getClass().getGenericSuperclass();
        return null;
    }
}
