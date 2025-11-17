package K23CNT1_Mba_Day04.service;

import K23CNT1_Mba_Day04.dto.MonHocDTO;
import K23CNT1_Mba_Day04.entity.MonHoc;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonHocService {
    private final List<MonHoc> monHocList = new ArrayList<>();

    public MonHocService() {
        // Khởi tạo dữ liệu mẫu
        monHocList.add(new MonHoc("MH1", "Lập trình Web", 60));
        monHocList.add(new MonHoc("MH2", "Cơ sở dữ liệu", 45));
        monHocList.add(new MonHoc("MH3", "Cấu trúc dữ liệu", 75));
    }

    // 1. Lấy toàn bộ danh sách
    public List<MonHoc> findAll() {
        return monHocList;
    }

    // 2. Lấy danh sách theo mã (mamh)
    public MonHoc findByMaMh(String mamh) {
        return monHocList.stream()
                .filter(mh -> mh.getMamh().equalsIgnoreCase(mamh))
                .findFirst()
                .orElse(null);
    }

    // 3. Thêm mới một môn học
    public boolean create(MonHocDTO dto) {
        if (findByMaMh(dto.getMamh()) != null) {
            return false; // Mã đã tồn tại
        }
        MonHoc newMonHoc = MonHoc.builder()
                .mamh(dto.getMamh())
                .tenmh(dto.getTenmh())
                .sotiet(dto.getSotiet())
                .build();
        monHocList.add(newMonHoc);
        return true;
    }

    // 4. Sửa đổi thông tin môn học theo mã
    public boolean update(String mamh, MonHocDTO dto) {
        MonHoc existingMonHoc = findByMaMh(mamh);
        if (existingMonHoc != null) {
            // Chỉ cập nhật tên và số tiết
            existingMonHoc.setTenmh(dto.getTenmh());
            existingMonHoc.setSotiet(dto.getSotiet());
            return true;
        }
        return false;
    }

    // 5. Xóa thông tin môn học theo mã
    public boolean delete(String mamh) {
        return monHocList.removeIf(mh -> mh.getMamh().equalsIgnoreCase(mamh));
    }
}