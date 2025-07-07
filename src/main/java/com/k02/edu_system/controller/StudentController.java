package com.k02.edu_system.controller;

import com.k02.edu_system.model.Student;
import com.k02.edu_system.model.Teacher;
import com.k02.edu_system.repository.StudentRepository;
import com.k02.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentRepository.findAll());

        return "student-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("student", new Student());
        model.addAttribute("teachers", teachers);

        return "student-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Student student) {
        studentRepository.save(student);

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Student student = studentRepository.findById(id);
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("student", student);
        model.addAttribute("teachers", teachers);

        return "student-form";
    };

    @PostMapping("/edit")
    public String edit(@ModelAttribute Student student) {
        studentRepository.update(student);

        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        studentRepository.deleteById(id);

        return "redirect:/students";
    }
}