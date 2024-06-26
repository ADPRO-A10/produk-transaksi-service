package id.ac.ui.cs.advprog.produktransaksiservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.produktransaksiservice.dto.UserDTO;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.TransaksiRepository;
import id.ac.ui.cs.advprog.produktransaksiservice.service.TransaksiService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TransaksiService transaksiService;

    @GetMapping("")
    public String TransaksiPage() {
        return "Hello World Transaksi!!";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTransaksi(@RequestBody UserDTO user, Pembeli pembeli, Penjual penjual, Produk produk) throws JsonProcessingException {
        List<Penjual> listPenjual = new ArrayList<>();
        listPenjual.add(penjual);

        List<Produk> listProduk = new ArrayList<>();
        listProduk.add(produk);


        Transaksi result = transaksiService.processTransaksi(pembeli, listPenjual, listProduk);
        JSONObject response = new JSONObject();

        if (result == null) {
            response.put("message", "Transaksi gagal dibuat");
            return ResponseEntity.badRequest().body(response.toString());
        }

        String data = null;
        try {
            data = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        response.put("message", "Transaksi berhasil dibuat");
        response.put("data", data);
        return ResponseEntity.ok(response.toString());
    }

    @GetMapping("/get/{transaksiId}")
    public ResponseEntity<Transaksi> getTransaksi(@PathVariable UUID transaksiId) {
        Optional<Transaksi> transaksi = transaksiService.getTransaksi(transaksiId);
        return transaksi.map(value -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/getall")
    public ResponseEntity<List<Transaksi>> getAllTransaksi(){
        List<Transaksi> listTransaksi = transaksiService.getAllTransaksi();
        if (!listTransaksi.isEmpty()) {
            return ResponseEntity.ok(listTransaksi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}