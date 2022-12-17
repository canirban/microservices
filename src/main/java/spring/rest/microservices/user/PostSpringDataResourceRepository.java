package spring.rest.microservices.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostSpringDataResourceRepository extends JpaRepository<Post, Integer> {
}
