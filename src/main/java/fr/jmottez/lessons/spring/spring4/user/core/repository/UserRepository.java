package fr.jmottez.lessons.spring.spring4.user.core.repository;

import fr.jmottez.lessons.spring.spring4.generic.repository.CrudRepository;
import fr.jmottez.lessons.spring.spring4.user.core.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
