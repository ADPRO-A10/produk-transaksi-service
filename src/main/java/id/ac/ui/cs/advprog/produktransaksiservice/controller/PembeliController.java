package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.ProdukDirector;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.PembeliRepository;
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
@RequestMapping("/pembeli")
public class PembeliController {
    private PembeliRepository pembeliRepository;

    @Autowired
    PembeliController(PembeliRepository pembeliRepository) {
        this.pembeliRepository = pembeliRepository;
    }


    @GetMapping("/{id}")
    public String penjualPage(@PathVariable Long id, Model model) {
        Pembeli pembelinya = pembeliRepository.findByUserId(id);
        if (pembelinya == null) {
            return "pembeli tidak ditemukan";
        } else {
            List<Produk> library = pembelinya.getLibrary();
            model.addAttribute("pembeli", pembelinya);
            model.addAttribute("library", library);
            return "pembeli";
        }
    }
}
