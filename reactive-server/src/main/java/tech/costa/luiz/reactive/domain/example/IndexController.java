package tech.costa.luiz.reactive.domain.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

/**
 * The type Index controller.
 */
@Controller
public class IndexController {

    /**
     * Index string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("connection", UUID.randomUUID().toString());
        return "index";
    }
}
