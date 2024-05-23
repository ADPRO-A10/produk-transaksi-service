package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdukServiceImpl implements ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    @Override
    public Produk getProdukById(String id) {
        return produkRepository.findById(id).orElse(null);
    }

    @Override
    public Produk addProduk(Produk produk) {
        return produkRepository.save(produk);
    }

    @Override
    public Produk editProduk(String id, Produk updatedProduk) {
        Produk produk = produkRepository.findById(id).orElse(null);
        if (produk == null) {
            return null;
        }
        produk.setNama(updatedProduk.getNama());
        produk.setHarga(updatedProduk.getHarga());
        produk.setKategori(updatedProduk.getKategori());
        produk.setDeskripsi(updatedProduk.getDeskripsi());
        produk.setStokTersedia(updatedProduk.getStokTersedia());
        produk.setStokTerjual(updatedProduk.getStokTerjual());
        produk.setPenjual(updatedProduk.getPenjual());
        return produkRepository.save(produk);
    }
}
