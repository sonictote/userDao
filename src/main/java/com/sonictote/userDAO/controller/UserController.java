package com.sonictote.userDAO.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonictote.userDAO.entity.User;
import com.sonictote.userDAO.services.UserService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController // Le indica a Spring boot que es un contralador y con esta anotacion indica que
				// el valor de retorno es el cuerpo de la respuesta
@RequestMapping("/api/users") // Le inidica la url desde donde se hacen las peticiones
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * Este metodo se va encargar de crear un usuario en la base de datos. Con la
	 * clase ResponseEntity, devolveremos si fue creado correctamente
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) { // Requestbody indica que debe serializar el json recibido
																// a Java Object
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	/*
	 * Este metodo se va a encargar de leer el usuario cuando en el enlace pongamos
	 * el id de un usuario
	 */
	@GetMapping("/{id}") // En get mapping se indica la infomacion que se necesita para buscar un usuario
							// entre llaves
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userId) { // En el caso de que el parametro no se
																				// llame igual que el parametro que
																				// pasamos a la url debemos usar value =
																				// "Elnombredelparametro"

		Optional<User> oUser = userService.findById(userId);

		if (!oUser.isPresent()) { // Si el Optional no es un usuario cosntruimos y devolvemos un not found (404)
			return ResponseEntity.notFound().build();
		} else { // Si existe, devolvemos un 200 y el JSON del usuario
			return ResponseEntity.ok(oUser);
		}
	}

	/*
	 * Este metodo mostrara todos los usuarios de la base de datos
	 */
	@GetMapping("/{id}") // En get mapping se indica la infomacion que se necesita para buscar un usuario
	// entre llaves
	public List<User> readAll() {
		List<User> users = StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return users;
	}

	/*
	 * Este metodo se encargara de actualizar el usuario
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId) {
		Optional<User> user = userService.findById(userId);

		if (!user.isPresent()) { // Si el Optional no es un usuario cosntruimos y devolvemos un not found (404)
			return ResponseEntity.notFound().build();
		} else { // Si existe, devolvemos un 200 y el JSON del usuario
			BeanUtils.copyProperties(userDetails, user.get()); // Copia todo el objeto
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
		}
	}

	/*
	 * Este metodo borra el usuario
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
		if (!userService.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			userService.deleteById(userId);
			return ResponseEntity.ok().build();
		}
	}

}
