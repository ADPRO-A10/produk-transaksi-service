package id.ac.ui.cs.advprog.produktransaksiservice.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.produktransaksiservice.model.*;
import id.ac.ui.cs.advprog.produktransaksiservice.service.TransaksiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

@WebMvcTest(TransaksiController.class)
public class TransaksiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransaksiService transaksiService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pembeli pembeli;
    private Penjual penjual;
    private Produk produk;
    private Transaksi transaksi;

    @BeforeEach
    void setUp() {
        pembeli = new Pembeli();
        pembeli.setUsername("pembeli");

        penjual = new Penjual();
        penjual.setUsername("penjual");

        produk = new Produk();
        produk.setProdukId("123456");
        produk.setNama("meong");
        produk.setHarga(3000);
        produk.setKategori("casual");
        produk.setDeskripsi("untuk kucing dari kucing");
        produk.setStokTersedia(1);
        produk.setStokTerjual(1);
        produk.setPenjual("penjual");

        transaksi = new Transaksi();
        transaksi.setTransaksiId(UUID.randomUUID());
        transaksi.setListProduk(Collections.singletonList(produk));
        transaksi.setTotalHarga(3000L);
        transaksi.setStatusPembayaran("selesai");
        transaksi.setTanggalTransaksi(LocalDate.now());
    }

//    @Test
//    void testCreateTransaksi() throws Exception {
//        given(transaksiService.processTransaksi(Mockito.any(Pembeli.class), Mockito.anyList(), Mockito.anyList()))
//                .willReturn(transaksi);
//
//        mockMvc.perform(post("/transaksi/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(transaksi)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("Transaksi berhasil dibuat"))
//                .andExpect(jsonPath("$.data.transaksiId").isNotEmpty());
//    }

    @Test
    void testGetTransaksiNotFound() throws Exception {
        UUID transaksiId = UUID.randomUUID();
        given(transaksiService.getTransaksi(transaksiId)).willReturn(Optional.empty());

        mockMvc.perform(get("/transaksi/get/{transaksiId}", transaksiId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTransaksiFound() throws Exception {
        UUID transaksiId = transaksi.getTransaksiId();
        given(transaksiService.getTransaksi(transaksiId)).willReturn(Optional.of(transaksi));

        mockMvc.perform(get("/transaksi/get/{transaksiId}", transaksiId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.transaksiId").value(transaksiId.toString()));
    }

    @Test
    void testGetAllTransaksiEmpty() throws Exception {
        given(transaksiService.getAllTransaksi()).willReturn(Collections.emptyList());

        mockMvc.perform(post("/transaksi/getall"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllTransaksiNotEmpty() throws Exception {
        given(transaksiService.getAllTransaksi()).willReturn(Collections.singletonList(transaksi));

        mockMvc.perform(post("/transaksi/getall"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].transaksiId").value(transaksi.getTransaksiId().toString()));
    }
}
