package K23CNT1_Mba_Day04.controller;

import K23CNT1_Mba_Day04.dto.MonHocDTO;
import K23CNT1_Mba_Day04.entity.MonHoc;
import K23CNT1_Mba_Day04.service.MonHocService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/monhoc")
public class MonHocController {

    @Autowired
    private MonHocService monHocService;

    // GET: Lấy tất cả môn học
    @GetMapping("/list")
    public List<MonHoc> getAllMonHoc() {
        return monHocService.findAll();
    }

    // POST: Thêm mới môn học (Có Validation)
    @PostMapping("/add")
    public ResponseEntity<?> addMonHoc(@Valid @RequestBody MonHocDTO dto) {
        if (monHocService.create(dto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mon Hoc created successfully!");
        }
        return ResponseEntity.badRequest().body("Mã môn học đã tồn tại!");
    }

    // PUT: Cập nhật môn học theo mã
    @PutMapping("/update/{mamh}")
    public ResponseEntity<String> updateMonHoc(@PathVariable String mamh, @Valid @RequestBody MonHocDTO dto) {
        if (monHocService.update(mamh, dto)) {
            return ResponseEntity.ok("Mon Hoc updated successfully.");
        }
        return ResponseEntity.badRequest().body("Không tìm thấy mã môn học để cập nhật.");
    }

    // DELETE: Xóa môn học theo mã
    @DeleteMapping("/delete/{mamh}")
    public ResponseEntity<String> deleteMonHoc(@PathVariable String mamh) {
        if (monHocService.delete(mamh)) {
            return ResponseEntity.ok("Mon Hoc deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}