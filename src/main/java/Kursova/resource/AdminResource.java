package Kursova.resource;

import Kursova.dto.AdminDTO;
import Kursova.repository.AdminRepository;
import Kursova.service.AdminService;
import Kursova.service.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminResource {
    @Autowired
    private AdminService adminService = new AdminServiceImpl();
    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/create")
    public String create(@Valid @RequestBody final AdminDTO adminDTO) {
        adminService.create(adminDTO);
        return "All good";
    }

    @GetMapping("/{id}")
    public AdminDTO read(final @PathVariable Long id) {
        return adminService.read(id);
    }

    @PutMapping("/update")
    public ResponseEntity<AdminDTO> update(@RequestBody AdminDTO adminDTO) {
        adminService.update(adminDTO);
        return ResponseEntity.ok(adminDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(final @PathVariable("id") Long id) {
        AdminDTO adminDTO = adminService.read(id);
        adminService.delete(id);
        return "Admin:" + adminDTO.toString();
    }

    @GetMapping(value = "/all")
    public List<AdminDTO> getAll() {
        return adminService.getAll();
    }
}
