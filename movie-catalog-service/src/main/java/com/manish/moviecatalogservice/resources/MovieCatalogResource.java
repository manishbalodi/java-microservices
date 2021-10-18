package com.manish.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.manish.moviecatalogservice.models.CatalogItem;
import com.manish.moviecatalogservice.models.Movie;
import com.manish.moviecatalogservice.models.Rating;
import com.manish.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId)
	{
		
		//get all rated movie id 
		UserRating ratings = restTemplate.getForObject("http://localhost:8082/ratingsdata/users/" + userId, UserRating.class);
		
		//for each movie id , call movie info service and get details
		return ratings.getUserRating().stream().map(rating ->
		{
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/foo" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Test description", rating.getRating());
		}
		).collect(Collectors.toList());
		
	}

}
