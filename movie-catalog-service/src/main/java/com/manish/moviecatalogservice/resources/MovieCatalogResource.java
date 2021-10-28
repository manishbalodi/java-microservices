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
import com.manish.moviecatalogservice.service.MovieInfo;
import com.manish.moviecatalogservice.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod="getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable String userId)
	{
		
		//get all rated movie id 
		UserRating ratings = userRatingInfo.getUserRating(userId);
		//for each movie id , call movie info service and get details
		return ratings.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());
		
	}
	
//	public List<CatalogItem> getFallbackCatalog(@PathVariable String userId)
//	{
//		return Arrays.asList(new CatalogItem("No Movie","",0));
//	}

}
