package spring.rest.microservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.rest.microservices.course.spring.data.jpa.UserSpringDataJpaRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class JpaUserResouce {

    @Autowired
    private UserSpringDataJpaRepository repository;

    @Autowired
    private PostSpringDataResourceRepository postRepository;


    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public User getUser(@PathVariable int id){
        return repository.findById(id).get();
    }

    @PostMapping("/jpa/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/user/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        return user.get().getPosts();
    }
    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Post> createPost(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
