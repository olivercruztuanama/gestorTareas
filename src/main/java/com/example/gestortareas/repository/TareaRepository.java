package com.example.gestortareas.repository;

import com.example.gestortareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long>{

}