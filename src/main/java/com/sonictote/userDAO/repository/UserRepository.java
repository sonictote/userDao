/*
 * La clase repositorio se encarga de encapsular, almacenar, buscar y recuperar la coleccion de objetos de la bbdd
 */
package com.sonictote.userDAO.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.sonictote.userDAO.entity.User;

/*Esta interfaz extiende de JpaRepository ya que contiene todos los metodos para hacer todas las acciones de un CRUD
 * y ademas permite hacer "Paginacion". Tambien se podria usar CrudRepository aunque este no contiene pagina
 */
@Repository //Le dice a Spring que es un repositorio
public interface UserRepository extends JpaRepository<User, Long>{
	
}
