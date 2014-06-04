package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.User;


public interface SpringDataUserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);

}
