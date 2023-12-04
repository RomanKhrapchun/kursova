package Kursova.service.impl;

import Kursova.dto.AdminDTO;
import Kursova.entity.Admin;
import Kursova.mapper.MapperAdmin;
import Kursova.repository.AdminRepository;
import Kursova.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MapperAdmin mapperAdmin = new MapperAdmin();

    @Override
    public AdminDTO create(AdminDTO dto) {
        Admin admin = mapperAdmin.toEntity(dto);
        adminRepository.save(admin);
        return mapperAdmin.toDto(admin);
    }

    @Override
    public AdminDTO read(Long code) {
        return mapperAdmin.toDto(adminRepository.findById(code).orElseThrow());
    }

    @Override
    public void update(AdminDTO dto) {
        Admin admin = mapperAdmin.toEntity(dto);
        adminRepository.save(admin);
    }

    @Override
    public void delete(Long code) {
        adminRepository.delete(adminRepository.findById(code).orElseThrow());
    }

    @Override
    public List<AdminDTO> getAll() {
        return adminRepository.findAll().stream().map(mapperAdmin::toDto).collect(Collectors.toList());
    }
}
