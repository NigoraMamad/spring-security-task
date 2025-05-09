package edu.epam.fop.web.controller;

import edu.epam.fop.web.entity.Course;
import edu.epam.fop.web.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
//handling courses too
    private final CourseService courseService;

    public HomeController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/courses/new")
    public String newCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @PostMapping("/courses")
    public String createCourse(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findById(id).orElseThrow();
        model.addAttribute("course", course);
        return "course_form";
    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute Course course) {
        Course existing = courseService.findById(id).orElseThrow();
        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        courseService.save(existing);
        return "redirect:/";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteById(id);
        return "redirect:/";
    }
}
