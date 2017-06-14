package com.vijay.searchImage.application;

/**[Vijay]: Uncomment for Transport Connection
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import java.net.InetAddress;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.shield.ShieldPlugin;
import org.springframework.context.annotation.Bean;
*/
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.vijay.searchImage.repository")
public class SearchImageConfig {
	
	@Value("${elasticsearch.host}")
	private String EsHost;

	@Value("${elasticsearch.port}")
	private int EsPort;

	@Value("${elasticsearch.clustername}")
	private String EsClusterName;

	/** [Vijay]: Commenting out this, as this is Transport Connection at 9343 (AWS unknownhost issue)
	@Bean
	public Client client() throws Exception {
		boolean enableSsl = true;
		Settings settings = Settings.settingsBuilder()
				.put("transport.ping_schedule", "5s")
				.put("cluster.name", EsClusterName)
				.put("transport.ignore_cluster_name",true)
				.put("action.bulk.compress", false)
				.put("shield.transport.ssl", enableSsl)
				.put("request.headers.X-Found-Cluster", EsClusterName)
				.put("shield.user", "admin:admin") // your shield username and password
				.build();
		Client client = TransportClient.builder()
				.addPlugin(ShieldPlugin.class)
				.settings(settings)
				.build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));

		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
	**/

}
