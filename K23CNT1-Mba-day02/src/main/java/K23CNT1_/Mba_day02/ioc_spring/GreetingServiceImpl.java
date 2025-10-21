package K23CNT1_.Mba_day02.ioc_spring;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        return "<h1>Dev [Spring Boot]</h1> Xin chào, " +
                "<h3 style='color:red; text-align:center'>" + name + "</h3>";
    }
}