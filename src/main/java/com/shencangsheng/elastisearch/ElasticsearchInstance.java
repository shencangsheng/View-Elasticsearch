/**
 * Copyright (C), 2015-2022, XXX有限公司
 * FileName: ElasticsearchInstance
 * Author:   shencangsheng
 * Date:     2022/3/6 11:23 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.shencangsheng.elastisearch;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author shencangsheng
 * @create 2022/3/6
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticsearchInstance {
    private String url;
    private Integer port = 9200;
    private String user;
    private String password;
    private Integer connectTimeout = 5000;
    private Integer socketTimeout = 60000;

    public RestHighLevelClient instance() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        /** setting user password */
        if (StrUtil.isAllNotBlank(user, password)) {
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
        }
        RestClientBuilder builder = RestClient.builder(new HttpHost(url, port))
            .setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout));
        return new RestHighLevelClient(builder);
    }
}
