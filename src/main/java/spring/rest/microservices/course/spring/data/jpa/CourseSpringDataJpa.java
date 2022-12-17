package spring.rest.microservices.course.spring.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.rest.microservices.course.Course;

public interface CourseSpringDataJpa extends JpaRepository<Course, Long> {
}
