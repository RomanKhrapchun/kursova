package Kursova.mapper;

import Kursova.dto.UserDTO;
import Kursova.entity.User;
import org.springframework.stereotype.Component;


@Component
public class MapperUser {
    public UserDTO toDto(final User user) {
        final UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setCardNumber(user.getCardNumber());
        dto.setLocation(user.getLocation());
        return dto;
    }

    public User toEntity(final UserDTO dto) {
        final User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setCardNumber(dto.getCardNumber());
        user.setLocation(dto.getLocation());
        return user;
    }
}
