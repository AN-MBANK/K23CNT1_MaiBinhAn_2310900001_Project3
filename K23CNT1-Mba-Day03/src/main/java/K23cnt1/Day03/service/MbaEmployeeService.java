package K23cnt1.Day03.service;

import K23cnt1.Day03.entity.MbaEmployee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MbaEmployeeService {

    private final List<MbaEmployee> list = new ArrayList<>();

    public MbaEmployeeService() {
        list.addAll(Arrays.asList(
                new MbaEmployee(1L, "Nguyễn Văn A", "Nam", 25, 1200),
                new MbaEmployee(2L, "Trần Thị B", "Nữ", 28, 1500),
                new MbaEmployee(3L, "Lê Văn C", "Nam", 30, 2000),
                new MbaEmployee(4L, "Phạm Thị D", "Nữ", 22, 1000),
                new MbaEmployee(5L, "Hoàng Văn E", "Nam", 35, 2500)
        ));
    }

    public List<MbaEmployee> getAll() {
        return list;
    }

    public MbaEmployee getById(Long mbaId) {
        return list.stream().filter(e -> e.getMbaId().equals(mbaId)).findFirst().orElse(null);
    }

    public MbaEmployee add(MbaEmployee e) {
        list.add(e);
        return e;
    }

    public MbaEmployee update(Long mbaId, MbaEmployee newInfo) {
        for (MbaEmployee e : list) {
            if (e.getMbaId().equals(mbaId)) {
                e.setMbaFullName(newInfo.getMbaFullName());
                e.setMbaGender(newInfo.getMbaGender());
                e.setMbaAge(newInfo.getMbaAge());
                e.setMbaSalary(newInfo.getMbaSalary());
                return e;
            }
        }
        return null;
    }

    public boolean delete(Long mbaId) {
        return list.removeIf(e -> e.getMbaId().equals(mbaId));
    }
}
