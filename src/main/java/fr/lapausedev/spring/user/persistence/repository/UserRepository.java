package fr.lapausedev.spring.user.persistence.repository;

import fr.lapausedev.spring.generic.repository.CrudRepository;
import fr.lapausedev.spring.user.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
