package com.vijay.searchImage.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImageRepository extends ElasticsearchRepository<Image, String> {
	
	List<Image> findByCaption (String caption,Pageable page);
	
	List<Image> findByCaption (String caption);

}
