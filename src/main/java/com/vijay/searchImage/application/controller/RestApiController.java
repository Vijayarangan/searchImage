package com.vijay.searchImage.application.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.searchImage.repository.Image;
import com.vijay.searchImage.exception.CustomExceptionType;
import com.vijay.searchImage.service.ImageService;

@RestController
@RequestMapping("/api")
public class RestApiController {
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ImageService imageService;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/image/{searchText}", method = RequestMethod.GET)
	public ResponseEntity<List<Image>> getImages(@PathVariable("searchText") String searchText,
			@RequestParam(required =false, value="pageStart") String pageStart,
			@RequestParam(required =false, value="pageSize") String pageSize) {
		logger.info("Fetching Images with searchText {}", searchText);
		List<Image> imageList=new ArrayList<Image>();
		if (pageStart!=null && pageSize!=null){
			int pageStartsAt=Integer.parseInt(pageStart);
			int pageSizeTo=Integer.parseInt(pageSize);
			logger.info("Page Info --> Start at "+ pageStartsAt  + "and get "+pageSizeTo);
			imageList = imageService.searchByCaption(searchText, new PageRequest(pageStartsAt,pageSizeTo)).getContent();
		}else{//Default to 50
			imageList = imageService.searchByCaption(searchText, new PageRequest(0,50)).getContent();
		}
		logger.info("No.Of Images Retrieved {}", imageList.size());
		if (imageList!=null && imageList.isEmpty()) {
			logger.error("No Images found with searchText {}", searchText);
			return new ResponseEntity(new CustomExceptionType("Images with Caption " + searchText 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Image>>(imageList, HttpStatus.OK);
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/image/", method = RequestMethod.GET)
	public ResponseEntity<List<Image>> listAllUsers() {
		logger.info("Fetching All Images");
		Iterable<Image> images = imageService.findAll();
		if (!images.iterator().hasNext()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		Iterator<Image> imageIterator=images.iterator();
		List<Image> imageList = new ArrayList<Image>();
		while (imageIterator.hasNext()) {
			imageList.add(imageIterator.next());
		}
		logger.info("No.Of Images Found {}", imageList.size());
		return new ResponseEntity<List<Image>>(imageList, HttpStatus.OK);
	}

}
