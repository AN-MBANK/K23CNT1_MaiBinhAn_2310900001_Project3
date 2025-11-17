package K23CNT1_Mba_Day06.controller;

import K23CNT1_Mba_Day06.dto.CustomerDTO;
import K23CNT1_Mba_Day06.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers") // Tất cả các endpoint bắt đầu bằng /customers
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * READ ALL: Hiển thị danh sách khách hàng
     * GET /customers
     */
    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers/customer-list";
    }

    /**
     * CREATE - Show Form: Hiển thị form thêm mới
     * GET /customers/add-new
     */
    @GetMapping("/add-new")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "customers/customer-add.html";
    }

    /**
     * CREATE - Process Form: Lưu dữ liệu mới
     * POST /customers
     */
    @PostMapping
    public String saveCustomer(@ModelAttribute("customer") CustomerDTO customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    /**
     * UPDATE - Show Form: Hiển thị form sửa
     * GET /customers/edit/{id}
     */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        CustomerDTO customerDTO = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

        model.addAttribute("customer", customerDTO);
        return "customers/customer-edit";
    }

    /**
     * UPDATE - Process Form: Cập nhật dữ liệu
     * POST /customers/update/{id}
     */
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id,
                                 @ModelAttribute("customer") CustomerDTO customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers";
    }

    /**
     * DELETE: Xóa khách hàng
     * GET /customers/delete/{id}
     */
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}