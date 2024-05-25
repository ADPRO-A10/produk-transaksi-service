package id.ac.ui.cs.advprog.produktransaksiservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.TransaksiRepository;
import id.ac.ui.cs.advprog.produktransaksiservice.service.TransaksiService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TransaksiService transaksiService;

    @GetMapping
    public String renderTransaksiPage() {
        return "Hello World Transaksi!!";
    }

    @PostMapping
    public ResponseEntity<String> createTransaksi() throws JsonProcessingException {
        Transaksi transaksi = new Transaksi.Builder().build();
        List<Produk> listProduk = new ArrayList<>();
        transaksi.setListProduk(listProduk);

        Transaksi result = transaksiService.createTransaksi(transaksi);
        JSONObject response = new JSONObject();

        if (result == null) {
            response.put("message", "Transaksi gagal dibuat");
            return ResponseEntity.badRequest().body(response.toString());
        }

        String data = objectMapper.writeValueAsString(transaksi);
        response.put("message", "Transaksi berhasil dibuat");
        response.put("data", data);
        return ResponseEntity.ok(response.toString());
    }

}