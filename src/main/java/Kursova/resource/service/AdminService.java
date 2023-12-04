package Kursova.resource.service;

import Kursova.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    AdminDTO create(AdminDTO dto);

    AdminDTO read(Long code);

    void update(AdminDTO dto);

    void delete(Long code);

    List<AdminDTO> getAll();
}
