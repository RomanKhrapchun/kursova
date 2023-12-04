package Kursova.mapper;

import Kursova.dto.AdminDTO;
import Kursova.entity.Admin;
import org.springframework.stereotype.Component;


@Component
public class MapperAdmin {
    public AdminDTO toDto(final Admin admin) {
        final AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setPassword(admin.getPassword());
        dto.setName(admin.getName());
        dto.setAdminRole(admin.getAdminRole());
        return dto;
    }

    public Admin toEntity(final AdminDTO dto) {
        final Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setPassword(dto.getPassword());
        admin.setName(dto.getName());
        admin.setAdminRole(dto.getAdminRole());
        return admin;
    }
}
