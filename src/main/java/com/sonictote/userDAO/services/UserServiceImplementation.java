/*
 * Consiste en una clase que se conecta a varios repositorios y en la que se implementan las funciones 
 * que se pueden hacer con ellos
 */
package com.sonictote.userDAO.services;

import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.sonictote.userDAO.entity.User;
import com.sonictote.userDAO.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service //Le indicamos a Spring que es un servicio
public class UserServiceImplementation implements UserService{

	//Haremos una instancia de la interfaz para poder hacer usos de los metedos que necesitamos
	@Autowired //Crea una instancia de la clase automaticamente
	private UserRepository userRepository;
	
	/*
	 * Muestra todos los usuarios
	 */
	@Override
	//@Transactional(readOnly = true) no funiona buscar info
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	/*
	 * Muestra la paginacion de los usuarios (Revisar)
	 */
	@Override //Mirar
	public Page<User> findAll(Pageable pageable) {
		return null;//userRepository.findAll(pageable);
	}

	/*
	 * Busca un usuario por id
	 */
	@Override
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	/*
	 * Guarda un usuario
	 */
	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	/*
	 * Elimina un usuario
	 */
	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

}
