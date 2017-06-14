package com.vijay.searchImage.service;


import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import com.vijay.searchImage.repository.Image;
import com.vijay.searchImage.repository.ImageRepository;
import com.vijay.searchImage.utils.SearchStringUtils;
@Service
public class ImageServiceImpl implements ImageService{
	public static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
	private ImageRepository imageRepository;

	@Autowired
	public void setIMageRepository(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public List<Image> findByCaption(String caption, Pageable page){
		caption=SearchStringUtils.cleanseString(caption);
		logger.info("Search the repo with caption "+caption+" and Page offset "+page.getOffset()
		+" page size "+page.getPageSize());
		return imageRepository.findByCaption(caption, page);
	}

	@Override
	public List<Image> findByCaption(String caption){
		caption=SearchStringUtils.cleanseString(caption);
		logger.info("Search the repo with caption "+caption);
		return imageRepository.findByCaption(caption);
	}

	@Override
	public Iterable<Image> findAll(){
		return imageRepository.findAll();
	}

	@Override
	public Page<Image> searchByCaption(String caption, Pageable page){
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				//	.withQuery(QueryBuilders.queryStringQuery(caption))
				.withQuery(QueryBuilders.matchQuery("caption", caption)
						.fuzziness(Fuzziness.ONE)
						.prefixLength(3))
				.withPageable(page)
				.withSort(SortBuilders.fieldSort("_score").order(SortOrder.DESC))
				.build();

		return imageRepository.search(searchQuery);
	}

	@Override
	public void save(Image image){
		imageRepository.save(image);
	}
	
	@Override
	public Image findOne(String id){
		return imageRepository.findOne(id);
	}
}
