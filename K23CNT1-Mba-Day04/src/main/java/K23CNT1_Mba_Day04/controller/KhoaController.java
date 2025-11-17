package K23CNT1_Mba_Day04.controller;

import K23CNT1_Mba_Day04.dto.KhoaDTO;
import K23CNT1_Mba_Day04.entity.Khoa;
import K23CNT1_Mba_Day04.service.KhoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/khoa")
public class KhoaController {

    @Autowired
    private KhoaService khoaService;

    // GET: Lấy tất cả khoa
    @GetMapping("/list")
    public List<Khoa> getAllKhoa() {
        return khoaService.findAll();
    }

    // GET: Lấy khoa theo mã
    @GetMapping("/{makh}")
    public ResponseEntity<Khoa> getKhoaByMa(@PathVariable String makh) {
        Khoa khoa = khoaService.findByMaKh(makh);
        if (khoa != null) {
            return ResponseEntity.ok(khoa);
        }
        return ResponseEntity.notFound().build();
    }

    // POST: Thêm mới một khoa (có Validation)
    @PostMapping("/add")
    public ResponseEntity<?> addKhoa(@Valid @RequestBody KhoaDTO dto) {
        if (khoaService.create(dto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Khoa created successfully!");
        }
        // Giả sử lỗi ở đây là trùng mã
        return ResponseEntity.badRequest().body("Mã khoa đã tồn tại!");
    }

    // PUT: Sửa đổi thông tin khoa theo mã
    @PutMapping("/update/{makh}")
    public ResponseEntity<String> updateKhoa(@PathVariable String makh, @Valid @RequestBody KhoaDTO dto) {
        if (khoaService.update(makh, dto)) {
            return ResponseEntity.ok("Khoa updated successfully.");
        }
        return ResponseEntity.badRequest().body("Không tìm thấy mã khoa để cập nhật.");
    }

    // DELETE: Xóa thông tin khoa theo mã
    @DeleteMapping("/delete/{makh}")
    public ResponseEntity<String> deleteKhoa(@PathVariable String makh) {
        if (khoaService.delete(makh)) {
            return ResponseEntity.ok("Khoa deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}