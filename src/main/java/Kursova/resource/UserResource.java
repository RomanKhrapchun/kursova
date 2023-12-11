package Kursova.resource;

import Kursova.dto.UserDTO;
import Kursova.repository.UserRepository;
import Kursova.service.UserService;
import Kursova.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserResource {
    @Autowired
    private UserService userService = new UserServiceImpl();
    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public String create(@Valid @RequestBody final UserDTO userDTO) {
        userService.create(userDTO);
        return "All good";
    }

    @GetMapping("/{id}")
    public UserDTO read(final @PathVariable Long id) {
        return userService.read(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(final @PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        userService.update(userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(final @PathVariable("id") Long id) {
        UserDTO userDTO = userService.read(id);
        userService.delete(id);
        return "User:" + userDTO.toString();
    }

    @GetMapping(value = "/all")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }
}
