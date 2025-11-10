package K23cnt1.Day03.controlller;

import K23cnt1.Day03.entity.MbaKhoa;
import K23cnt1.Day03.service.MbaKhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mba-khoas")
public class MbaKhoaController {

    @Autowired
    private MbaKhoaService service;

    @GetMapping
    public List<MbaKhoa> getAll() {
        return service.getAll();
    }

    @GetMapping("/{mbaMakh}")
    public MbaKhoa getById(@PathVariable String mbaMakh) {
        return service.getById(mbaMakh);
    }

    @PostMapping
    public MbaKhoa add(@RequestBody MbaKhoa k) {
        return service.add(k);
    }

    @PutMapping("/{mbaMakh}")
    public MbaKhoa update(@PathVariable String mbaMakh, @RequestBody MbaKhoa k) {
        return service.update(mbaMakh, k);
    }

    @DeleteMapping("/{mbaMakh}")
    public boolean delete(@PathVariable String mbaMakh) {
        return service.delete(mbaMakh);
    }
}
