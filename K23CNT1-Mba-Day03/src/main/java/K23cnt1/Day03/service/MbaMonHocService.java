package K23cnt1.Day03.service;

import K23cnt1.Day03.entity.MbaMonHoc;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MbaMonHocService {

    private final List<MbaMonHoc> list = new ArrayList<>();

    public MbaMonHocService() {
        list.addAll(Arrays.asList(
                new MbaMonHoc("MH01", "Toán cao cấp", 45),
                new MbaMonHoc("MH02", "Lập trình Java", 60),
                new MbaMonHoc("MH03", "Cơ sở dữ liệu", 50),
                new MbaMonHoc("MH04", "Kinh tế vi mô", 40),
                new MbaMonHoc("MH05", "Quản trị dự án", 35)
        ));
    }

    public List<MbaMonHoc> getAll() {
        return list;
    }

    public MbaMonHoc getById(String mbaMamh) {
        return list.stream().filter(m -> m.getMbaMamh().equals(mbaMamh)).findFirst().orElse(null);
    }

    public MbaMonHoc add(MbaMonHoc m) {
        list.add(m);
        return m;
    }

    public MbaMonHoc update(String mbaMamh, MbaMonHoc newInfo) {
        for (MbaMonHoc m : list) {
            if (m.getMbaMamh().equals(mbaMamh)) {
                m.setMbaTenmh(newInfo.getMbaTenmh());
                m.setMbaSotiet(newInfo.getMbaSotiet());
                return m;
            }
        }
        return null;
    }

    public boolean delete(String mbaMamh) {
        return list.removeIf(m -> m.getMbaMamh().equals(mbaMamh));
    }
}
