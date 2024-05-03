package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.service.TransaksiServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransaksiController {

    @Autowired
    private TransaksiServiceImpl transaksiService;

    @PostMapping
    public Transaksi createTransaksi(@RequestBody Transaksi transaksi) {
        return transaksiService.createTransaksi(transaksi);
    }

    @GetMapping("/{transaksiId}")
    public Transaksi getTransaksi(@PathVariable Long transaksiId) {
        return transaksiService.checkout(transaksiId);
    }
}