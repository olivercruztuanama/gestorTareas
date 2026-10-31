package com.example.gestortareas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tareas")
public class Tarea {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, length = 150)
	    private String titulo;

	    @Column(columnDefinition = "TEXT")
	    private String descripcion;

	    @Column(name = "fecha_creacion", insertable = false, updatable = false)
	    private LocalDateTime fechaCreacion;

	    // Getters y Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getTitulo() { return titulo; }
	    public void setTitulo(String titulo) { this.titulo = titulo; }
	    public String getDescripcion() { return descripcion; }
	    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
	    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
	}