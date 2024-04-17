package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProdukController {
    @GetMapping("")
    public String showProdukPage(Model model){
        return "Hello Produk";
    }

    @GetMapping("/create")
    public String showCreateProdukPage(Model model){
        return "Hello Create Produk";
    }

    @GetMapping("/list")
    public String showReadProdukPage(Model model){
        return "Hello Read Produk";
    }

    @GetMapping("/edit")
    public String showEditProdukPage(Model model){
        return "Hello Edit Produk";
    }

    @GetMapping("/delete")
    public String showDeleteProdukPage(Model model){
        return "Hello Delete Produk";
    }
}
