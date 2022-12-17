package spring.rest.microservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users= new ArrayList<>();

    static {
        users.add(new User(1,"Anirban", LocalDate.now().minusYears(28)));
        users.add(new User(1,"Basanti", LocalDate.now().minusYears(49)));
        users.add(new User(1,"Pradip", LocalDate.now().minusYears(59)));
        users.add(new User(1,"Aditi", LocalDate.now().minusYears(32)));
        users.add(new User(1,"John", LocalDate.now().minusYears(43)));
        users.add(new User(1,"Alex", LocalDate.now().minusYears(22)));
        users.add(new User(1,"Jose", LocalDate.now().minusYears(78)));
        users.add(new User(1,"James", LocalDate.now().minusYears(55)));
        users.add(new User(1,"Max", LocalDate.now().minusYears(61)));
        users.add(new User(1,"Manu", LocalDate.now().minusYears(51)));
    }

    public List<User> findAll() {
        return users;
    }

    public User find(int id){
        User foundUser = users.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
        if(foundUser == null){
            throw new UserNotFoundException("id :"+id);
        }
        return foundUser;
    }

    public User save(User user){
        user.setId((int)System.currentTimeMillis());
        users.add(user);
        return user;
    }

    public void delete(int id){
       users.removeIf(user -> user.getId()==id);
    }

}
