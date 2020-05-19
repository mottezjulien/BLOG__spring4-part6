package fr.lapausedev.spring.user.core.repository;

import fr.lapausedev.spring.generic.repository.CrudRepository;
import fr.lapausedev.spring.user.core.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
