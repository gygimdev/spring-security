package springsecurity.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HelloController {

    @GetMapping
    public String helloController() {
        return "home";
    }
}
