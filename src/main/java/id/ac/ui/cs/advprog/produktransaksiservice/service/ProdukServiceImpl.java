package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdukServiceImpl implements ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    @Override
    public Produk findProdukById(String id) {
        return produkRepository.findById(id).orElse(null);
    }

    @Override
    public Produk findProdukByNama(String nama) {
        return produkRepository.findByNama(nama).orElse(null);
    }

    @Override
    public Produk createProduk(Produk produk) {
        if(produkRepository.findByNama(produk.getNama()).isPresent()){
            throw new RuntimeException("Produk dengan nama tersebut sudah ada");
        } else {
            return produkRepository.save(produk);
        }
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

    @Override
    public List<Produk> findAll() {
        return produkRepository.findAll();
    }

    @Override
    public void deleteProduk(String id) {
        Produk produk = produkRepository.findById(id).orElse(null);
        if (produk == null) {
            throw new RuntimeException("Produk tidak ditemukan");
        }
        produkRepository.delete(produk);
    }
}
