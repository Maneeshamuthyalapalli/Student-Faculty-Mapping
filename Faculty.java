package com.example.MENTORPROJECT;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faculties")
@Data
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();
}
