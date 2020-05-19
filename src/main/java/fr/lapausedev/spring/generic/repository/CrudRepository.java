package fr.lapausedev.spring.generic.repository;


import java.io.Serializable;
import java.util.Collection;


public interface CrudRepository<Entity, ID extends Serializable> {

	<EntityChild extends Entity> EntityChild save(EntityChild entity) throws RepositoryException;

	Entity findOne(ID primaryKey) throws RepositoryException;

	Collection<Entity> findAll() throws RepositoryException;

	void delete(Entity entity) throws RepositoryException;

}