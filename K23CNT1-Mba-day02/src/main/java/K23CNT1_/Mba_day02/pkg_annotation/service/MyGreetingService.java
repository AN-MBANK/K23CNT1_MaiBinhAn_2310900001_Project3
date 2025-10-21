package K23CNT1_.Mba_day02.pkg_annotation.service;
import org.springframework.stereotype.Service;

@Service
public class MyGreetingService {
    public String greet() {
        return "<h1>Hello from MyGreetingService!</h1>";
    }
}