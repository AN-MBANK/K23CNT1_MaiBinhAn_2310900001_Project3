package K23CNT1_Mba_Day08.controller;

import K23CNT1_Mba_Day08.entity.Configuration;
import K23CNT1_Mba_Day08.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/configurations")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    // Hiển thị danh sách các loại cấu hình
    @GetMapping
    public String listConfigurations(Model model) {
        List<Configuration> configurations = configurationService.getAllConfigurations();
        model.addAttribute("configurations", configurations);
        return "configurations/configuration-list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("configuration", new Configuration());
        return "configurations/configuration-form";
    }

    // Xử lý lưu (Thêm mới/Cập nhật)
    @PostMapping("/new")
    public String saveConfiguration(@ModelAttribute Configuration configuration, RedirectAttributes redirectAttributes) {
        try {
            configurationService.saveConfiguration(configuration);
            redirectAttributes.addFlashAttribute("successMessage", "Configuration saved successfully!");
            return "redirect:/configurations";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error saving Configuration: " + e.getMessage());
            return "redirect:/configurations/new";
        }
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Configuration config = configurationService.getConfigurationById(id);
        if (config == null) {
            return "redirect:/configurations";
        }
        model.addAttribute("configuration", config);
        return "configurations/configuration-form";
    }

    // Xử lý xóa
    @GetMapping("/delete/{id}")
    public String deleteConfiguration(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        configurationService.deleteConfiguration(id);
        redirectAttributes.addFlashAttribute("successMessage", "Configuration deleted successfully!");
        return "redirect:/configurations";
    }
}