package com.vijay.searchImage;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vijay.searchImage.application.SearchImageApplication;
import com.vijay.searchImage.repository.Image;
import com.vijay.searchImage.service.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchImageApplication.class)
public class SearchImageApplicationTests {

	@Autowired
	private ImageService imageService;

	@Before
	public void before() {
		/**[Vijay]: Please Do not uncomment this, as it might affect the data prepared for Experticity review and testing
		esTemplate.deleteIndex(Image.class);
		esTemplate.createIndex(Image.class);
		esTemplate.putMapping(Image.class);
		esTemplate.refresh(Image.class);
		*/
	}
	@Test
	public void testSearchByCaption(){
		List<Image> imageList=imageService.findByCaption("Blue");
		assertNotNull(imageList);
	}

	
	/**[Vijay]: Commenting out as index is properly set up for experticity review and testing
	@Test
	public void testFindOne() {

		Image image = new Image(1002000, "http://www.eschoolnews.com/files/2014/08/online_testing.3.jpg", "TESTING", 23,17);
		imageService.save(image);
		Image testImage = imageService.findOne("1002000");

		assertNotNull(testImage.getId());
		assertEquals(testImage.getCaption(), image.getCaption());
		assertEquals(testImage.getImageURL(), image.getImageURL());
		assertEquals(testImage.getHeight(), image.getHeight());

	}
	*/
	
	/**[Vijay]: Commenting out as these services are not required
	@Test
	public void testSave() {

		Image image = new Image(100000, "http://hi", "Blue Pant", 23,10);
		Image testImage = imageService.save(image);

		assertNotNull(testImage.getId());
		assertEquals(testImage.getCaption(), image.getCaption());
		assertEquals(testImage.getImageURL(), image.getImageURL());
		assertEquals(testImage.getHeight(), image.getHeight());

	}

	@Test
	public void testDelete() {

		Image image = new Image(1001188, "http://go.go", "Yello Tie", 23,17);
		imageService.save(image);
		imageService.delete(image);
		Image testImage = imageService.findOne(image.getId());
		assertNull(testImage);
	}
	*/

}
