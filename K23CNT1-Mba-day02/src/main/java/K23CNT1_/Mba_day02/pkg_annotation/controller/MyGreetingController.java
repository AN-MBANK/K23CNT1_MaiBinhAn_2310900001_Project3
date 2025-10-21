package K23CNT1_.Mba_day02.pkg_annotation.controller;


import K23CNT1_.Mba_day02.pkg_annotation.service.MyGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyGreetingController {

    @Autowired
    private MyGreetingService greetService;

    @GetMapping("/my-greet")
    public String greet() {
        return greetService.greet();
    }
}
//@Service: Spring tự động tạo bean MyGreetingService và đưa vào container.
//
//@RestController: Kết hợp @Controller + @ResponseBody, trả kết quả trực tiếp ra trình duyệt.
//
//@Autowired: Tự động inject đối tượng MyGreetingService vào controller.