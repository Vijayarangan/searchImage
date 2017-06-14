package com.vijay.searchImage.application;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

import com.vijay.searchImage.repository.ImageRepository;


@SpringBootApplication(scanBasePackages={"com.vijay.searchImage"},exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class SearchImageApplication {
	
	@Autowired
	public static ImageRepository imageRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SearchImageApplication.class, args);
	}
	
}
