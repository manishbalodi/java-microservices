package com.manish.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.moviecatalogservice.models.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId)
	{
		List<CatalogItem> list = new ArrayList<>();
		CatalogItem item = new CatalogItem("Transformers", "Test Movie", 4);
		list.add(item);
		return list;
	}

}
