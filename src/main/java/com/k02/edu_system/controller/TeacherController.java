package com.k02.edu_system.controller;

import com.k02.edu_system.model.Teacher;
import com.k02.edu_system.repository.StudentRepository;
import com.k02.edu_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teacher-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Teacher teacher) {
        teacherRepository.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("teacher", teacherRepository.findById(id));
        return "teacher-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Teacher teacher) {
        teacherRepository.update(teacher);
        return "redirect:/teachers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        int count = studentRepository.countByTeacherId(id);

        if (count > 0) {
            model.addAttribute("errorMessage", "해당 교사에게 배정된 학생이 있어 삭제할 수 없습니다.");
            model.addAttribute("teachers", teacherRepository.findAll());
            return "teacher-list";
        }

        teacherRepository.deleteById(id);
        return "redirect:/teachers";
    }
}
