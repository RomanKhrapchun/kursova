package Kursova.service;

import Kursova.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO dto);

    UserDTO read(Long id);

    void update(UserDTO dto);

    void delete(Long id);

    List<UserDTO> getAll();
}
