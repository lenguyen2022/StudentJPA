package ca.lenguyen.studentjpa.controllers;

import ca.lenguyen.studentjpa.beans.Student;

import ca.lenguyen.studentjpa.database.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        //findAll is the method of CrudRepository
        model.addAttribute("studentList",studentRepository.findAll() );
        return "index";
    }
    @GetMapping("/addStudent")
    public String getAddStudent(Model model){
        //findAll is the method of CrudRepository
        model.addAttribute("studentList",studentRepository.findAll() );
        model.addAttribute("student", new Student());
        return "insertStudent";
    }
    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute Student student){
        studentRepository.save(student);
        ModelAndView modelAndView = new ModelAndView("insertStudent", "studentList", studentRepository.findAll());
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }
    @GetMapping("/editStudentById/{id}")
    public String editStudentById(Model model, @PathVariable Long id) {

        //Search for a student with the id from the link
        Optional <Student> student = studentRepository.findById(id);
        //pass this student object to the index  page
        model.addAttribute("student", student.get());
        //delete this student by id.
        studentRepository.deleteById(id);
        //update the student lists
        model.addAttribute("studentList", studentRepository.findAll());
        return "insertStudent";
    }

    @GetMapping("/deleteStudentById/{id}")
    public String deleteStudentById(Model model, @PathVariable Long id) {
        studentRepository.deleteById(id);
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", studentRepository.findAll());
        return "insertStudent";
    }

    @GetMapping("/studentList")
    public ModelAndView studentList(){
        //readStudentByNameStartingWith is our customized method.
        return new ModelAndView("index", "studentList", studentRepository.readStudentByNameStartingWith("A"));
    }
    @GetMapping("/studentBList")
    public ModelAndView studentBList(){
        //findStudentByName is our customized method.
        return new ModelAndView("index", "studentList", studentRepository.findStudentByName());
    }
}
