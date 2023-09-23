/*
 * Esta interfaz no se va anotar como "service" ya que es "clase fachada", que definira los metodos que se pueden usar
 */
package com.sonictote.userDAO.services;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import com.sonictote.userDAO.entity.User;

public interface UserService {
	
	public Iterable<User> findAll();
	
	public Page<User> findAll(Pageable pageable);
	
	//Usamos optional asi evitamos los nulos
	public Optional<User> findById(long id);
	
	public User save (User user);
	
	public void deleteById (Long id);

}
