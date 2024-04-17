package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String helloPage(Model model){
        return "Hello";
    }

}