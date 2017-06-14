package com.vijay.searchImage.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "image", type = "image", shards = 1, replicas = 0, refreshInterval = "-1")
public class Image {

	@Id
	private long id;

	private String caption;

	private int height;

	private int width;

	private String imageURL;

	public Image(){
		id=0;
	}

	public Image(long id, String caption,String imageURL, int height, int width){
		this.id = id;
		this.caption = caption;
		this.imageURL = imageURL;
		this.height = height;
		this.width =width;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", imageURL=" + imageURL + ", caption=" + caption+ ", height=" + height + ", width=" + width +"]";
	}


}
