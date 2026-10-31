package com.example.gestortareas.service;

import com.example.gestortareas.model.Tarea;
import com.example.gestortareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
	@Autowired
    private TareaRepository repository;

    public List<Tarea> obtenerTodas() { return repository.findAll(); }
    
    public Optional<Tarea> obtenerPorId(Long id) { return repository.findById(id); }

    public Tarea guardar(Tarea tarea) { return repository.save(tarea); }

    public void eliminar(Long id) { repository.deleteById(id); }
}
