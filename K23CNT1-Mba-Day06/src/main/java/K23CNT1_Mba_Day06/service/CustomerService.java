package K23CNT1_Mba_Day06.service;

import K23CNT1_Mba_Day06.dto.CustomerDTO;
import K23CNT1_Mba_Day06.entity.Customer;
import K23CNT1_Mba_Day06.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Trong K23CNT1_Mba_Day06.service.CustomerService

    private CustomerDTO convertToDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        // Áp dụng kiểm tra NULL an toàn cho tất cả các trường String
        String password = (customer.getPassword() != null) ? customer.getPassword() : "";
        String address = (customer.getAddress() != null) ? customer.getAddress() : "";
        String phone = (customer.getPhone() != null) ? customer.getPhone() : "";
        String birthDay = (customer.getBirthDay() != null) ? customer.getBirthDay() : "";

        return CustomerDTO.builder()
                .id(customer.getId())
                .username(customer.getUsername())

                // SỬ DỤNG GIÁ TRỊ ĐÃ KIỂM TRA NULL
                .password(password)
                .address(address)
                .phone(phone)
                .birthDay(birthDay)

                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .active(customer.isActive()) // boolean nguyên thủy luôn an toàn
                .build();
    }
    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFullName(customerDTO.getFullName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setBirthDay(customerDTO.getBirthDay());
        customer.setActive(customerDTO.isActive());
        return customer;
    }

    // Lấy danh sách tất cả khách hàng
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Tìm khách hàng theo ID
    public Optional<CustomerDTO> findById(Long id) {
        return customerRepository.findById(id).map(this::convertToDto);
    }

    // Thêm mới/Lưu khách hàng
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }

    // Xóa khách hàng theo ID
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    // Cập nhật thông tin khách hàng theo ID
    public CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setUsername(updatedCustomerDTO.getUsername());
                    customer.setFullName(updatedCustomerDTO.getFullName());
                    customer.setAddress(updatedCustomerDTO.getAddress());
                    customer.setPhone(updatedCustomerDTO.getPhone());
                    customer.setEmail(updatedCustomerDTO.getEmail());
                    customer.setBirthDay(updatedCustomerDTO.getBirthDay());
                    customer.setActive(updatedCustomerDTO.isActive());

                    // Cập nhật mật khẩu nếu được cung cấp
                    if (updatedCustomerDTO.getPassword() != null && !updatedCustomerDTO.getPassword().isEmpty()) {
                        customer.setPassword(updatedCustomerDTO.getPassword());
                    }
                    return convertToDto(customerRepository.save(customer));
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
    }
}