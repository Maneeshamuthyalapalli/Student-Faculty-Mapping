package com.example.MENTORPROJECT;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MentorController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/faculty")
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        Faculty savedFaculty = facultyRepository.save(faculty);
        return ResponseEntity.ok(savedFaculty);
    }

    @PostMapping("/faculty/{facultyId}/students")
    public ResponseEntity<Faculty> addStudentToFaculty(@PathVariable Long facultyId, @RequestBody Student student) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(facultyId);
        if (!facultyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Faculty faculty = facultyOptional.get();

        student.setFaculty(faculty);
        studentRepository.save(student);

        faculty.getStudents().add(student);
        Faculty updatedFaculty = facultyRepository.save(faculty);

        return ResponseEntity.ok(updatedFaculty);
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(facultyId);
        if (!facultyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Faculty faculty = facultyOptional.get();
        return ResponseEntity.ok(faculty);
    }
}






