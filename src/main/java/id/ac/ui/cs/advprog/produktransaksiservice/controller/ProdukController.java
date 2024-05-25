package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produk")
public class ProdukController {
    @GetMapping("")
    public String helloPage(Model model){
        return "Hello Produk";
    }
}