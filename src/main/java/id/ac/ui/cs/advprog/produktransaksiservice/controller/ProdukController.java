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
    private ProdukServiceImpl produkServiceImpl;

    @Autowired
    ProdukController(ProdukServiceImpl produkServiceImpl) {
        this.produkServiceImpl = produkServiceImpl;
        this.produkBuilder = new ProdukDirector.ProdukBuilder();
    }

    @GetMapping("")
    public String showProdukPage(Model model){

        return "Hello Produk";
    }


    @GetMapping("/list")
    public ResponseEntity<List<Produk>> listProduk(){
        return new ResponseEntity<>(produkServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Produk> getProdukById(@PathVariable String id) {
        Produk produk = produkServiceImpl.findProdukById(id);
        if (produk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produk, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk) {
        try {
            Produk createdProduk = produkServiceImpl.createProduk(produk);
            return new ResponseEntity<>(createdProduk, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(produk, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Produk> editProduk(@PathVariable String id, @RequestBody Produk updatedProduk) {
        if (produkServiceImpl.findProdukById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Produk produk = produkServiceImpl.editProduk(id, updatedProduk);
            return new ResponseEntity<>(produk, HttpStatus.OK);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Produk> deleteProduk(@PathVariable String id) {
        Produk produk = produkServiceImpl.findProdukById(id);
        if (produk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produkServiceImpl.deleteProduk(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
