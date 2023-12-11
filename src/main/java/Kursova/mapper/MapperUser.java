package Kursova.mapper;

import Kursova.dto.UserDTO;
import Kursova.entity.User;
import org.springframework.stereotype.Component;


@Component
public class MapperUser {
    public UserDTO toDto(final User user) {
        final UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User toEntity(final UserDTO dto) {
        final User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
