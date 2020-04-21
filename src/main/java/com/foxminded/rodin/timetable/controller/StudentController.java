package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.service.GroupService;
import com.foxminded.rodin.timetable.service.SlotService;
import com.foxminded.rodin.timetable.service.StudentService;

@Controller
public class StudentController {

    private static final String STUDENT_FORM_RESOURSE_NAME = "student";
    private static final String STUDENTS_LIST_FORM_RESOURSE_NAME = "students";

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private SlotService slotService;

    @GetMapping("/students")
    public String getAllStudents(Model model, Principal principal) {

        List<Student> students = studentService.findAll();

        model.addAttribute("students", students);
        model.addAttribute("activeAll", true);

        return STUDENTS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/students/new")
    public String showNewStudentForm(Model model, Principal principal) {
        Student student = new Student();
        model.addAttribute("student", student);
        return STUDENT_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/students/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return STUDENT_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/students/{id}/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/" + STUDENTS_LIST_FORM_RESOURSE_NAME;
    }

    @ModelAttribute("studentGroups")
    public List<Group> populateVarietiesGroups(Student student, Model model) {
        return groupService.findByStudenstId(student.getId());
    }

    @ModelAttribute("slots")
    public List<Slot> populateVarietiesScheduleSlots(Student student, Model model) {
        return slotService.findSlotsByStudentId(student.getId());
    }

}
