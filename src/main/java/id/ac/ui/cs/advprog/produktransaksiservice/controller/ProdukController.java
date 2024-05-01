package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProdukController {

    @Autowired
    ProdukRepository produkRepository;
    @GetMapping("")
    public String showProdukPage(Model model){
        return "Hello Produk";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Produk product) {
        Produk createdProduct = produkRepository.create(product);
        return ResponseEntity.ok(createdProduct);
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
