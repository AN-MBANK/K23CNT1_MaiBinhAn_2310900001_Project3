package K23CNT1_.Mba_day02.pkg_annotation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyBeanController {

    @Autowired
    private String appName;

    @GetMapping("/my-bean")
    public String showBean() {
        return appName;
    }
}
//@Configuration: Spring biết AppConfig chứa các bean thủ công.
//
//@Bean: Tạo một bean kiểu String tên là appName.
//
//@Autowired: Inject giá trị appName vào controller.