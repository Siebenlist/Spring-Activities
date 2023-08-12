package com.example.students.controllers;

import com.example.students.models.Contact;
import com.example.students.models.Student;
import com.example.students.services.ContactService;
import com.example.students.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final ContactService contactService;

    public StudentController(StudentService studentService, ContactService contactService){
        this.studentService = studentService;
        this.contactService = contactService;
    }

    @GetMapping("/students/new")
    public String studentForm(Model model){
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    @PostMapping("/students/new")
    public String createStudent(@Valid @ModelAttribute("student")Student s, BindingResult result){
        if(result.hasErrors()){
            return "studentForm";
        }
        studentService.createStudent(s);
        return "redirect:/students";
    }

    @GetMapping("/contacts/new")
    public String contactForm(Model model){
        model.addAttribute("contact", new Contact());
        List<Student> allStudents = studentService.allStudents();
        model.addAttribute("allStudents", allStudents);
        return "contactForm";
    }

    @PostMapping("/contacts/new")
    public String createContact(@RequestParam("studentName")Long id,
                                @RequestParam("address")String address,
                                @RequestParam("city")String city,
                                @RequestParam("state")String state){
        Contact contact = new Contact();
        Student student = studentService.findStudentById(id);

        if(student != null){
            contact.setStudent(student);
        }

        contact.setAddress(address);
        contact.setCity(city);
        contact.setState(state);
        contactService.createContact(contact);

        return "redirect:/students";
    }

    @GetMapping("/students")
    public String showStudents(Model model){
        List<Student> allStudents = studentService.allStudents();
        List<Contact> allContacts = contactService.allContacts();
        model.addAttribute("students", allStudents);
        model.addAttribute("contacts", allContacts);
        return "students";
    }
}
