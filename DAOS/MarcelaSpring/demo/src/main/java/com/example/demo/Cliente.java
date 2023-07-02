package com.example.demo;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")


public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	
	private String dni;
	private String nombre;
	private String apellido;
	private String domicilio;
	private String email;
	private LocalDate fechaDeNacimiento;
	private String numeroDePasaporte;
	private String FechaDeVencimientoDePasaporte;
	
	public String getDni() {
		return dni;
	}
}
