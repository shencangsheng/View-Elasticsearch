/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: QueryTest
 * Author:   shencangsheng
 * Date:     2022/3/7 10:09 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import cn.hutool.core.io.file.FileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shencangsheng.view.module.AbstractModuleInstance;
import com.shencangsheng.view.module.NestedPropertyInstance;
import com.shencangsheng.view.module.TemplatePropertyInstance;
import com.shencangsheng.view.query.BoolQueryBuilderFactory;
import com.shencangsheng.view.query.model.QueryInstance;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/7
 * @since 1.0.0
 */
public class QueryTest extends AbstractModuleInstance {
    public QueryTest() {
        this.modules = Map.of("template", new TemplatePropertyInstance(),
            "nested", new NestedPropertyInstance());
    }

    public static void main(String[] args) throws Exception {
        FileReader templateJson = new FileReader("template.json");
        String queryJson = templateJson.readString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<QueryInstance> queryInstances = objectMapper.readValue(queryJson, new TypeReference<>() {
        });
        BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderFactory.boolQueryBuilderFactory(queryInstances, new QueryTest());
        System.out.println(boolQueryBuilder);
    }
}
