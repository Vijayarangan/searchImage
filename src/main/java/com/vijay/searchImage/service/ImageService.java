package com.vijay.searchImage.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vijay.searchImage.repository.Image;


public interface ImageService {	

	public List<Image> findByCaption(String caption, Pageable page);
	public List<Image> findByCaption(String caption);
	public Iterable<Image> findAll();
	public Page<Image> searchByCaption(String caption, Pageable page);
	public void save(Image image);
	public Image findOne(String id);

}
