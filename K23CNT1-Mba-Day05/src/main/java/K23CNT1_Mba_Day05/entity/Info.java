package K23CNT1_Mba_Day05.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; // Thêm NoArgsConstructor để tránh lỗi nếu cần

@Data // Tự động tạo getters, setters, toString, equals, hashCode
@AllArgsConstructor // Tự động tạo constructor với tất cả các trường
@NoArgsConstructor // Tự động tạo constructor mặc định (nên có)
public class Info {
    private String name;
    private String nickName;
    private String email;
    private String website;
}