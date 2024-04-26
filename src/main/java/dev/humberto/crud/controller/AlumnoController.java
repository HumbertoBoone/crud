package dev.humberto.crud.controller;


import dev.humberto.crud.model.Alumno;
import dev.humberto.crud.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;
    @PostMapping("/create")
    public String createAlumno(@ModelAttribute Alumno alumno) {
        Alumno toSaveAlumno = new Alumno();
        toSaveAlumno.setId(null);
        toSaveAlumno.setNombre(alumno.getNombre());
        toSaveAlumno.setApellidos(alumno.getApellidos());
        toSaveAlumno.setGenero(alumno.getGenero());
        toSaveAlumno.setFechaDeNacimiento(alumno.getFechaDeNacimiento());
        Alumno savedAlumno = alumnoRepository.save(toSaveAlumno);
        return "redirect:/alumnos/form";
    }

    @GetMapping("/form")
    public ModelAndView showFormAndList() {
        ModelAndView mav = new ModelAndView("alumno_page");
        Alumno alumno = new Alumno();
        List<Alumno> alumnos = alumnoRepository.findAll();
        mav.addObject("alumno", alumno);
        mav.addObject("alumnos", alumnos);
        return mav;
    }

    @PutMapping("/update/{id}")
    public String updateAlumno(@PathVariable Long id, @ModelAttribute Alumno alumnoData) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if (alumno.isPresent()) {
            Alumno updatedAlumno = alumno.get();
            updatedAlumno.setNombre(alumnoData.getNombre());
            updatedAlumno.setApellidos(alumnoData.getApellidos());
            updatedAlumno.setGenero(alumnoData.getGenero());
            updatedAlumno.setFechaDeNacimiento(alumnoData.getFechaDeNacimiento());
            alumnoRepository.save(updatedAlumno);
        }
        return "redirect:/alumnos/form";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAlumno(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if (alumno.isPresent()) {
            alumnoRepository.delete(alumno.get());
        }
        return "redirect:/alumnos/form";
    }

}
