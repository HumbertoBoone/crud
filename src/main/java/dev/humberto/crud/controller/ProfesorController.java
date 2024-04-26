package dev.humberto.crud.controller;


import dev.humberto.crud.model.Profesor;
import dev.humberto.crud.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @PostMapping("/create")
    public ResponseEntity<Profesor> createProfesor(@RequestBody Profesor profesor) {
        Profesor savedProfesor = profesorRepository.save(profesor);
        return ResponseEntity.ok(savedProfesor);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Profesor>> getAllProfesores() {
        List<Profesor> profesores = profesorRepository.findAll();
        return ResponseEntity.ok(profesores);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable Long id, @RequestBody Profesor profesorData) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (profesor.isPresent()) {
            Profesor updatedProfesor = profesor.get();
            updatedProfesor.setApellidos(profesorData.getApellidos());
            updatedProfesor.setGenero(profesorData.getGenero());
            updatedProfesor.setNombre(profesorData.getNombre());
            Profesor finalProfesor = profesorRepository.save(updatedProfesor);
            return ResponseEntity.ok(finalProfesor);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (profesor.isPresent()) {
            profesorRepository.delete(profesor.get());
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
