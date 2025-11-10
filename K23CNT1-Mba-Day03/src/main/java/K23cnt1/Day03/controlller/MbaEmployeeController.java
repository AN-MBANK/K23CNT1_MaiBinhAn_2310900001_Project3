package K23cnt1.Day03.controlller;

import K23cnt1.Day03.entity.MbaEmployee;
import K23cnt1.Day03.service.MbaEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mba-employees")
public class MbaEmployeeController {

    @Autowired
    private MbaEmployeeService service;

    @GetMapping
    public List<MbaEmployee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{mbaId}")
    public MbaEmployee getById(@PathVariable Long mbaId) {
        return service.getById(mbaId);
    }

    @PostMapping
    public MbaEmployee add(@RequestBody MbaEmployee e) {
        return service.add(e);
    }

    @PutMapping("/{mbaId}")
    public MbaEmployee update(@PathVariable Long mbaId, @RequestBody MbaEmployee e) {
        return service.update(mbaId, e);
    }

    @DeleteMapping("/{mbaId}")
    public boolean delete(@PathVariable Long mbaId) {
        return service.delete(mbaId);
    }
}
