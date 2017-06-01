package fr.jmottez.lessons.spring.spring4.generic.repository;


import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Collection;


public interface CrudRepository<Entity, ID extends Serializable> extends Repository<Entity, ID> {

	<EntityChild extends Entity> EntityChild save(EntityChild entity) throws RepositoryException;

	Entity findOne(ID primaryKey) throws RepositoryException;

	Collection<Entity> findAll() throws RepositoryException;

	void delete(Entity entity) throws RepositoryException;

}