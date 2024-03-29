package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>,
		PagingAndSortingRepository<Product, Long> {
	
	public Product findByProductnameAndSize(String productname,String size);
	
	
	
	//public User findByIdNotAndUsername(Long id, String username);

}
