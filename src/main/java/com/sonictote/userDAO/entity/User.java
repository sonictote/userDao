/*
 * Esta clase es la que define que atributos debe tener un User
 */
package com.sonictote.userDAO.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //Le dice a Spring boot que es una entidad (Es decir las caracteristicas que definen a una "cosa")
@Table(name = "users") // Estamos diciendo a JPA que cree una tabla con el nombre "users"
@Data //Le hemos especificado a lombok que cree todos los metodos de la clase
public class User implements Serializable{
	
	private static final long serialVersionUID = 3208333337227430025L;

	@Id //Le decimos a JPA que esta sera la clave primaria de la tabla "users"
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Con esto generamos la id de manera automatica
	private Long id;
	
	@Column (length = 50, nullable = false) // Le decimos que el dato no puede contener mas de 50 caracteres
	private String name;
	
	@Column (length = 50, nullable = false) //Con nuleable le decimos que los datos no pueden ser nulos
	private String surname;
	
	
}
