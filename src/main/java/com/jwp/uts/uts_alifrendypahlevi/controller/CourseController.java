package com.jwp.uts.uts_alifrendypahlevi.controller;

import com.jwp.uts.uts_alifrendypahlevi.model.Course;
import com.jwp.uts.uts_alifrendypahlevi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        try {
            Course addedCourse = courseService.addCourse(course);
            return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        String result = courseService.deleteCourse(id);
        if (result.equals("Course not found")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
