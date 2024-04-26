package dev.humberto.crud.controller;


import dev.humberto.crud.model.Grado;
import dev.humberto.crud.repositories.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/grados")
public class GradoController {

    @Autowired
    private GradoRepository gradoRepository;

    @PostMapping("/create")
    public ResponseEntity<Grado> createGrado(@RequestBody Grado grado) {
        Grado savedGrado = gradoRepository.save(grado);
        return ResponseEntity.ok(savedGrado);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Grado>> getAllGrados() {
        List<Grado> grados = gradoRepository.findAll();
        return ResponseEntity.ok(grados);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Grado> updateGrado(@PathVariable Long id, @RequestBody Grado gradoData) {
        Optional<Grado> grado = gradoRepository.findById(id);
        if (grado.isPresent()) {
            Grado updatedGrado = grado.get();
            updatedGrado.setProfesorId(gradoData.getProfesorId());
            updatedGrado.setNombre(gradoData.getNombre());
            Grado finalGrado = gradoRepository.save(updatedGrado);
            return ResponseEntity.ok(finalGrado);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGrado(@PathVariable Long id) {
        Optional<Grado> grado = gradoRepository.findById(id);
        if (grado.isPresent()) {
            gradoRepository.delete(grado.get());
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
