package edu.epam.fop.web.controller;

import edu.epam.fop.web.entity.Course;
import edu.epam.fop.web.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/new")
    public String newCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findById(id).orElseThrow();
        model.addAttribute("course", course);
        return "course_form";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute Course course) {
        Course existing = courseService.findById(id).orElseThrow();
        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        courseService.save(existing);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/";
    }
}
