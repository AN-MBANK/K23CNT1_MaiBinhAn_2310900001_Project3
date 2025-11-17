package K23CNT1_Mba_Day04.service;

import K23CNT1_Mba_Day04.dto.KhoaDTO;
import K23CNT1_Mba_Day04.entity.Khoa;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class KhoaService {
    private final List<Khoa> khoaList = new ArrayList<>();

    public KhoaService() {
        // Khởi tạo 5 phần tử ban đầu theo yêu cầu
        khoaList.add(new Khoa("KH01", "Công nghệ thông tin"));
        khoaList.add(new Khoa("KH02", "Quản trị kinh doanh"));
        khoaList.add(new Khoa("KH03", "Kỹ thuật"));
        khoaList.add(new Khoa("KH04", "Ngoại ngữ"));
        khoaList.add(new Khoa("KH05", "Thiết kế đồ họa"));
    }

    // Lấy toàn bộ danh sách
    public List<Khoa> findAll() {
        return khoaList;
    }

    // Lấy danh sách theo makh
    public Khoa findByMaKh(String makh) {
        return khoaList.stream()
                .filter(khoa -> khoa.getMakh().equalsIgnoreCase(makh))
                .findFirst()
                .orElse(null);
    }

    // Thêm mới một khoa
    public boolean create(KhoaDTO dto) {
        // Kiểm tra trùng mã
        if (findByMaKh(dto.getMakh()) != null) {
            return false;
        }
        Khoa newKhoa = Khoa.builder()
                .makh(dto.getMakh())
                .tenkh(dto.getTenkh())
                .build();
        khoaList.add(newKhoa);
        return true;
    }

    // Sửa đổi thông tin khoa theo mã
    public boolean update(String makh, KhoaDTO dto) {
        Khoa existingKhoa = findByMaKh(makh);
        if (existingKhoa != null) {
            // Cập nhật tên khoa
            existingKhoa.setTenkh(dto.getTenkh());
            return true;
        }
        return false;
    }

    // Xóa thông tin khoa theo mã
    public boolean delete(String makh) {
        return khoaList.removeIf(khoa -> khoa.getMakh().equalsIgnoreCase(makh));
    }
}