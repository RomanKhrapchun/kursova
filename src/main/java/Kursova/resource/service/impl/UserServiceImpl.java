package Kursova.resource.service.impl;

import Kursova.dto.UserDTO;
import Kursova.entity.User;
import Kursova.mapper.MapperUser;
import Kursova.repository.UserRepository;
import Kursova.resource.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperUser mapperUser = new MapperUser();

    @Override
    public UserDTO create(UserDTO dto) {
        User user = mapperUser.toEntity(dto);
        userRepository.save(user);
        return mapperUser.toDto(user);
    }

    @Override
    public UserDTO read(Long id) {
        return mapperUser.toDto(userRepository.findById(id).orElseThrow());
    }

    @Override
    public void update(UserDTO dto) {
        User user = mapperUser.toEntity(dto);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(mapperUser::toDto).collect(Collectors.toList());
    }
}
