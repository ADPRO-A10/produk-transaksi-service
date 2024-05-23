package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.ProdukDirector;
import id.ac.ui.cs.advprog.produktransaksiservice.service.ProdukServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProdukController {

    private ProdukDirector.ProdukBuilder produkBuilder;

    @Autowired
    private ProdukServiceImpl produkServiceImpl;

    @GetMapping("")
    public String showProdukPage(Model model){

        return "Hello Produk";
    }


    @GetMapping("/list")
    public ResponseEntity<List<Produk>> listProduk(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Produk> getProdukById(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Produk> editProduk(@PathVariable String id, @RequestBody Produk updatedProduk) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Produk> deleteProduk(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
