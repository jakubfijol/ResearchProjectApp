package pl.jakub.ResearchProject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @PostMapping("")
    public int add(@RequestBody User user) {
        return userRepository.save(user);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        User userFromDb = userRepository.getByLogin(user.getLogin());
        if (!wrongPassword(userFromDb, user)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private boolean wrongPassword(User userFromDb, User user) {
        return !userFromDb.getPass().equals(user.getPass());
    }

    @PatchMapping("/{login}")
    public ResponseEntity changePassword(@PathVariable("login") String login, @RequestBody User updatedUser) {
        User user = userRepository.getByLogin(login);
        if (user != null) {
            if (updatedUser.getPass() != null) user.setPass(updatedUser.getPass());
            userRepository.update(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{login}")
    public int delete(@RequestHeader("status") String status, @PathVariable("login") String login) {
        if (status.equals("admin"))
            return userRepository.delete(login);
        else return -1;
    }

}