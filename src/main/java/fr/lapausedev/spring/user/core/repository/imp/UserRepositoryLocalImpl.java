package fr.lapausedev.spring.user.core.repository.imp;


import fr.lapausedev.spring.generic.repository.RepositoryException;
import fr.lapausedev.spring.user.core.entity.UserEntity;
import fr.lapausedev.spring.user.core.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class UserRepositoryLocalImpl implements UserRepository {

	private List<UserEntity> list = new ArrayList();
	private int lastId = 0;

	public UserRepositoryLocalImpl() {
		UserEntity user0 = new UserEntity();
		user0.setFirstName("John");
		user0.setLastName("Doe");
		create(user0);

		UserEntity user1 = new UserEntity();
		user1.setFirstName("Michel");
		user1.setLastName("Durant");
		create(user1);
	}

	@Override
	public UserEntity findOne(Integer primaryKey) throws RepositoryException {
		return list.stream()
				.filter(user -> user.getId() == primaryKey)
				.findAny().orElseThrow(() -> new RepositoryException());
	}

	@Override
	public List<UserEntity> findAll() throws RepositoryException {
		return new ArrayList(list);
	}

	@Override
	public UserEntity save(UserEntity user) throws RepositoryException {
		try {
			merge(user, findOne(user.getId()));
		} catch (RepositoryException exception) {
			create(user);
		}
		return user;
	}

	private void merge(UserEntity from, UserEntity to) {
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
	}

	private void create(UserEntity user) {
		lastId++;
		user.setId(lastId);
		list.add(user);
	}

	@Override
	public void delete(UserEntity userEntity) throws RepositoryException {
		list.remove(findOne(userEntity.getId()));
	}

}
