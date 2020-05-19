package fr.lapausedev.spring.user.facade.controller;


import fr.lapausedev.spring.generic.facade.exception.InternalServerErrorException;
import fr.lapausedev.spring.generic.facade.exception.ResourceNotFoundException;
import fr.lapausedev.spring.generic.repository.RepositoryException;
import fr.lapausedev.spring.user.core.repository.UserRepository;
import fr.lapausedev.spring.user.facade.transport.assembler.UserDTOAssembler;
import fr.lapausedev.spring.user.facade.transport.object.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/rest/user")
public class UserSpringRestController {

	@Autowired
	private UserRepository repository;

	private UserDTOAssembler assembler = new UserDTOAssembler();

	@RequestMapping(method = RequestMethod.GET, path = "/{userId}")
	public
	@ResponseBody
	UserDTO findById(@PathVariable(value = "userId") String idStr) {
		try {
			return assembler.fromEntity(repository.findOne(Integer.parseInt(idStr)));
		} catch (RepositoryException e) {
			throw new ResourceNotFoundException();
		} catch (NumberFormatException e) {
			throw new InternalServerErrorException("error when parse " + idStr + " in integer value");
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public
	@ResponseBody
	List<UserDTO> list() {
		try {
			return repository.findAll()
					.stream()
					.map(entity -> assembler.fromEntity(entity))
					.collect(Collectors.toList());
		} catch (RepositoryException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}


	@RequestMapping(method = RequestMethod.POST, name = "/")
	public
	@ResponseBody
	UserDTO save(@RequestBody UserDTO input) {
		try {
			return assembler.fromEntity(repository.save(assembler.toEntity(input)));
		} catch (RepositoryException e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{userId}")
	public ResponseEntity<Void> delete(@PathVariable(value = "userId") String idStr) {
		try {
			repository.delete(repository.findOne(Integer.parseInt(idStr)));
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (RepositoryException e) {
			throw new ResourceNotFoundException();
		} catch (NumberFormatException e) {
			throw new InternalServerErrorException("error when parse " + idStr + " in integer value");
		}
	}

}

