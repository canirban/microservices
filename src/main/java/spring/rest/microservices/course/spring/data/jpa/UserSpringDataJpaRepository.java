package spring.rest.microservices.course.spring.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.rest.microservices.user.User;

public interface UserSpringDataJpaRepository extends JpaRepository<User, Integer> {
}
