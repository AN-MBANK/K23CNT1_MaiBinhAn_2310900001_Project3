package K23cnt1.Day03.service;

import K23cnt1.Day03.entity.MbaKhoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MbaKhoaService {

    private final List<MbaKhoa> list = new ArrayList<>();

    public MbaKhoaService() {
        list.addAll(Arrays.asList(
                new MbaKhoa("KH01", "Công nghệ thông tin"),
                new MbaKhoa("KH02", "Kinh tế"),
                new MbaKhoa("KH03", "Quản trị kinh doanh"),
                new MbaKhoa("KH04", "Điện tử viễn thông"),
                new MbaKhoa("KH05", "Luật")
        ));
    }

    public List<MbaKhoa> getAll() {
        return list;
    }

    public MbaKhoa getById(String mbaMakh) {
        return list.stream().filter(k -> k.getMbaMakh().equals(mbaMakh)).findFirst().orElse(null);
    }

    public MbaKhoa add(MbaKhoa k) {
        list.add(k);
        return k;
    }

    public MbaKhoa update(String mbaMakh, MbaKhoa newInfo) {
        for (MbaKhoa k : list) {
            if (k.getMbaMakh().equals(mbaMakh)) {
                k.setMbaTenkh(newInfo.getMbaTenkh());
                return k;
            }
        }
        return null;
    }

    public boolean delete(String mbaMakh) {
        return list.removeIf(k -> k.getMbaMakh().equals(mbaMakh));
    }
}
