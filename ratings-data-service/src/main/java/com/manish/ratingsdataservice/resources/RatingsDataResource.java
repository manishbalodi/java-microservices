package com.manish.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.ratingsdataservice.models.Rating;
import com.manish.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		return new Rating(movieId , 4);
	}
	
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId)
	{
		UserRating userRating = new UserRating();
		userRating.setUserRating((List<Rating>) Arrays.asList(new Rating("1234", 4), new Rating("5678",3)));
		return userRating;
	}

}
