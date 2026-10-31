package com.example.gestortareas.controller;

import com.example.gestortareas.model.Tarea;
import com.example.gestortareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
//@CrossOrigin(origins = "http://localhost:4200") // Permite peticiones desde Angular
public class TareaController {
	@Autowired
    private TareaService service;

    //Token simulado: "{"sub": "app-gestorTareas","name": "Oliver CT","admin": true,"iat": 1516239022}"
    private boolean esTokenValido(String authHeader) {
        return authHeader != null && authHeader.equals("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwibmFtZSI6IkVzdHVkaWFudGUiLCJyb2xlIjoiVVNVQVJJT19QUkFDVElDQSJ9.c2VjcmV0LWtleS1zaW11bGFkby0yMDI2");
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listar(@RequestHeader(value = "Authorization", required = false) String token) {
        if (!esTokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody Tarea tarea) {
        if (!esTokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(tarea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable Long id, @RequestBody Tarea tareaDetalles) {
        if (!esTokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
        return service.obtenerPorId(id).map(tarea -> {
            tarea.setTitulo(tareaDetalles.getTitulo());
            tarea.setDescripcion(tareaDetalles.getDescripcion());
            return ResponseEntity.ok(service.guardar(tarea));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable Long id) {
        if (!esTokenValido(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (service.obtenerPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}