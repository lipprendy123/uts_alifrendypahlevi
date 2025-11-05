package com.jwp.uts.uts_alifrendypahlevi.service;

import com.jwp.uts.uts_alifrendypahlevi.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private List<Course> courseList = new ArrayList<>();
    private int nextId = 1; 

    
    public List<Course> getAllCourses() {
        return courseList;
    }


    public Course addCourse(Course course) {
        if (course.getName() == null || course.getName().isEmpty() ||
            course.getDescription() == null || course.getDescription().isEmpty() ||
            course.getInstructor() == null || course.getInstructor().isEmpty()) {
            throw new IllegalArgumentException("Please complete all required fields");
        }

        course.setId(nextId++);
        courseList.add(course);
        return course;
    }

    
    public String deleteCourse(int id) {
        Optional<Course> course = courseList.stream()
                                             .filter(c -> c.getId() == id)
                                             .findFirst();
        if (course.isPresent()) {
            courseList.remove(course.get());
            return "Course deleted successfully";
        } else {
            return "Course not found";
        }
    }
}
