package id.ac.ui.cs.advprog.produktransaksiservice.controller;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.ProdukDirector;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.PenjualRepository;
import id.ac.ui.cs.advprog.produktransaksiservice.service.ProdukServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProdukController {

    private ProdukDirector.ProdukBuilder produkBuilder;
    private ProdukServiceImpl produkServiceImpl;
    private PenjualRepository penjualRepository;


    @Autowired
    ProdukController(ProdukServiceImpl produkServiceImpl, PenjualRepository penjualRepository) {
        this.produkServiceImpl = produkServiceImpl;
        this.produkBuilder = new ProdukDirector.ProdukBuilder();
        this.penjualRepository = penjualRepository;
    }

    @GetMapping("")
    public String showProdukPage(Model model){

        return "Hello Produk";
    }


    @GetMapping("/list")
    public ResponseEntity<List<Produk>> listProduk(){
        return new ResponseEntity<>(produkServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/listAllProduk")
    public String listAllProduk(Model model) {
        List<Produk> produkList = produkServiceImpl.findAll();
        model.addAttribute("produkList", produkList);
        return "ListProduk";
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Produk> getProdukById(@PathVariable String id) {
        Produk produk = produkServiceImpl.findProdukById(id);
        if (produk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produk, HttpStatus.OK);
    }

    @GetMapping("/createProduk/{id}")
    public String createProdukPage(Model model, @PathVariable Long id) {
        Produk produk = new Produk();
        model.addAttribute("produk", produk);
        Penjual penjualnya = penjualRepository.findByUserId(id);
        model.addAttribute("id", id);
        if (penjualnya == null) {
            return "redirect:/";
        }
        model.addAttribute("penjual", penjualnya);
        return "CreateProdukPenjual";
    }

    @PostMapping("/createProduk/{id}")
    public String createProduk(@ModelAttribute Produk produk, @PathVariable Long id, Model model) {
        Penjual penjualnya = penjualRepository.findByUserId(id);
        if (penjualnya == null) {
            return "redirect:/";
        } else {
            try {
            produk.setPenjual(penjualnya.getUsername());
            produkServiceImpl.createProduk(produk);
            penjualnya.getKatalog().add(produk);
            penjualRepository.save(penjualnya);
            return "redirect:/penjual/" + id;
            } catch (Exception e) {
                model.addAttribute("errorMessage", e.getMessage());
                return "redirect:/createProduk/" + id;
            }
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Produk> createProdukPost(@RequestBody Produk produk) {
        try {
            produkServiceImpl.createProduk(produk);
            return new ResponseEntity<>(produk, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    @GetMapping("/edit/{productId}/{penjualId}")
    public String editProductPage(@PathVariable String productId, Model model, @PathVariable Long penjualId) {
        Produk produk = produkServiceImpl.findProdukById(productId);
        Penjual penjual = penjualRepository.findByUserId(penjualId);
        model.addAttribute("produk", produk);
        model.addAttribute("penjual", penjual);
        return "EditProduk";
    }

    @PostMapping("/edit/{productId}/{penjualId}")
    public String editProductPost(@PathVariable String productId, Model model, @ModelAttribute Produk product, @PathVariable Long penjualId) {
        try {
            produkServiceImpl.editProduk(productId, product);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "EditProduk";
        }
        return "redirect:/penjual/" + penjualId;
    }
}