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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.shencangsheng.view.mappings.PropertyInstance;
import com.shencangsheng.view.mappings.enums.QueryBoolEnum;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

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
        PropertyInstance property = ctxt.readTreeAsValue(node.get("property"), PropertyInstance.class);
        SuperQuery superQuery = null;
        String key = node.get("key").asText();
        JsonNode valueJsonNode = node.get("value");
        Object value = null;
        switch (property.getQueryType()) {
            case KEYWORD:
            case STRING:
            case WILDCARD:
                superQuery = new TermQuery();
                value = ctxt.readTreeAsValue(valueJsonNode, List.class);
                break;
            case DATE:
            case LONG:
            case DOUBLE:
                superQuery = new RangeQuery();
                value = Lists.newArrayListWithCapacity(valueJsonNode.size());
                for (JsonNode element : valueJsonNode) {
                    ((List) value).add(ctxt.readTreeAsValue(element, SuperRangeQuery.class));
                }
                break;
            case GROUP:
                superQuery = new GroupQuery();
                value = Lists.newArrayListWithCapacity(valueJsonNode.size());
                for (JsonNode element : valueJsonNode) {
                    ((ObjectNode) element).putPOJO("property", property.getNext());
                    ((List) value).add(ctxt.readTreeAsValue(element, SuperQuery.class));
                }
                break;
            default:
                throw new IllegalArgumentException(format("Type %s was not found", property.getQueryType()));
        }
        JsonNode boolTypeJsonNode = node.get("boolType");
        if (Objects.nonNull(boolTypeJsonNode) || Objects.nonNull(boolTypeJsonNode = node.get("bool_type"))) {
            superQuery.setBoolType(QueryBoolEnum.valueOf(boolTypeJsonNode.asText()));
        }
        superQuery.setKey(key);
        superQuery.setValue(value);
        return superQuery;
    }
}
