package K23cnt1.Day03.controlller;

import K23cnt1.Day03.entity.MbaMonHoc;
import K23cnt1.Day03.service.MbaMonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mba-monhocs")
public class MbaMonHocController {

    @Autowired
    private MbaMonHocService service;

    @GetMapping
    public List<MbaMonHoc> getAll() {
        return service.getAll();
    }

    @GetMapping("/{mbaMamh}")
    public MbaMonHoc getById(@PathVariable String mbaMamh) {
        return service.getById(mbaMamh);
    }

    @PostMapping
    public MbaMonHoc add(@RequestBody MbaMonHoc m) {
        return service.add(m);
    }

    @PutMapping("/{mbaMamh}")
    public MbaMonHoc update(@PathVariable String mbaMamh, @RequestBody MbaMonHoc m) {
        return service.update(mbaMamh, m);
    }

    @DeleteMapping("/{mbaMamh}")
    public boolean delete(@PathVariable String mbaMamh) {
        return service.delete(mbaMamh);
    }
}
