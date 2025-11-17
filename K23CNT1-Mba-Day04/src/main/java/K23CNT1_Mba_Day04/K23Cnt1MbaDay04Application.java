package K23CNT1_Mba_Day04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration; // Import thêm

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class K23Cnt1MbaDay04Application {

    public static void main(String[] args) {
        SpringApplication.run(K23Cnt1MbaDay04Application.class, args);
    }

}