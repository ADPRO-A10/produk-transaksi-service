package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
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
@RequestMapping("/penjual")
public class PenjualController {
    private PenjualRepository penjualRepository;

    @Autowired
    PenjualController(PenjualRepository penjualRepository) {
        this.penjualRepository = penjualRepository;
    }


    @GetMapping("/{id}")
    public String penjualPage(@PathVariable Long id, Model model) {
        Penjual penjualnya = penjualRepository.findByUserId(id);
        if (penjualnya == null) {
            return "penjual tidak ditemukan";
        } else {
            List<Produk>  katalogPenjual = penjualnya.getKatalog();
            model.addAttribute("penjual", penjualnya);
            model.addAttribute("katalog", katalogPenjual);
            return "penjual";
        }
    }

    @GetMapping("/{id}/create")
    public String createProdukPage(@PathVariable String id, Model model) {
        Penjual penjualnya = penjualRepository.findById(id).orElse(null);
        if (penjualnya == null) {
            return "penjual tidak ditemukan";
        } else {
            model.addAttribute("penjual", penjualnya);
            return "createProdukPenjual";
        }
    }
}
