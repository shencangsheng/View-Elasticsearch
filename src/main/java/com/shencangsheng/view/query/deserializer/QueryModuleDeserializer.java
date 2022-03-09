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
package com.shencangsheng.view.query.deserializer;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.shencangsheng.view.annotation.ModuleInstanceAnnotation;
import com.shencangsheng.view.mappings.AbstractPropertyInstance;
import com.shencangsheng.view.mappings.PropertyInstance;
import com.shencangsheng.view.module.AbstractModuleInstance;
import com.shencangsheng.view.query.model.QueryInstance;
import com.shencangsheng.view.query.model.SuperQuery;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/8
 * @since 1.0.0
 */
public class QueryModuleDeserializer extends StdDeserializer<QueryInstance> {

    public QueryModuleDeserializer() {
        this(null);
    }

    protected QueryModuleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public QueryInstance deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        QueryInstance queryInstance = new QueryInstance();
        JsonNode node = p.getCodec().readTree(p);
        String module = node.get("module").asText();
        AbstractModuleInstance moduleInstance = ReflectUtil.newInstance(queryInstance.getClass().getAnnotation(ModuleInstanceAnnotation.class).moduleInstance());
        AbstractPropertyInstance propertyInstance = moduleInstance.getModule(module);
        Map<String, PropertyInstance> properties = propertyInstance.getProperties();
        JsonNode query = node.get("query");
        List<SuperQuery> superQueries = Lists.newArrayListWithCapacity(query.size());
        for (JsonNode element : query) {
            String key = element.get("key").asText();
            PropertyInstance property = properties.get(key);
            ((ObjectNode) element).putPOJO("property", property);
            superQueries.add(ctxt.readTreeAsValue(element, SuperQuery.class));
        }
        queryInstance.setModule(module);
        queryInstance.setQuery(superQueries);
        return queryInstance;
    }
}
