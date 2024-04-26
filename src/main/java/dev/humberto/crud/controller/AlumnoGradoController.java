package dev.humberto.crud.controller;

import dev.humberto.crud.model.AlumnoGrado;
import dev.humberto.crud.repositories.AlumnoGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumno-grado")
public class AlumnoGradoController {

    @Autowired
    private AlumnoGradoRepository alumnoGradoRepository;


    @PostMapping("/create")
    public ResponseEntity<AlumnoGrado> createAlumnoGrado(@RequestBody AlumnoGrado alumnoGrado) {
        AlumnoGrado savedAlumnoGrado = alumnoGradoRepository.save(alumnoGrado);
        return ResponseEntity.ok(savedAlumnoGrado);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlumnoGrado>> getAllAlumnoGrados() {
        List<AlumnoGrado> alumnoGrados = alumnoGradoRepository.findAll();
        return ResponseEntity.ok(alumnoGrados);
    }

    /*@GetMapping("/all")
    public ResponseEntity<List<AlumnoGrado>> getAllAlumnoGrados() {
        List<AlumnoGrado> alumnoGrados = alumnoGradoRepository.findAll();
        return ResponseEntity.ok(alumnoGrados);
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<AlumnoGrado> updateAlumnoGrado(@PathVariable Long id, @RequestBody AlumnoGrado alumnoGradoData) {
        Optional<AlumnoGrado> alumnoGrado = alumnoGradoRepository.findById(id);
        if (alumnoGrado.isPresent()) {
            AlumnoGrado updatedAlumnoGrado = alumnoGrado.get();
            updatedAlumnoGrado.setAlumnoId(alumnoGradoData.getAlumnoId());
            updatedAlumnoGrado.setGradoId(alumnoGradoData.getGradoId());
            updatedAlumnoGrado.setSeccion(alumnoGradoData.getSeccion());
            AlumnoGrado finalAlumnoGrado = alumnoGradoRepository.save(updatedAlumnoGrado);
            return ResponseEntity.ok(finalAlumnoGrado);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        Optional<AlumnoGrado> alumnoGrado = alumnoGradoRepository.findById(id);
        if (alumnoGrado.isPresent()) {
            alumnoGradoRepository.delete(alumnoGrado.get());
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
