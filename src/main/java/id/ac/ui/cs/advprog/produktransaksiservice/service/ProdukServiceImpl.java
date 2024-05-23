package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdukServiceImpl implements ProdukService {

    @Override
    public Produk getProdukById(String id) {
        return null;
    }

    @Override
    public Produk addProduk(Produk produk) {
        return null;
    }

    @Override
    public Produk editProduk(String id, Produk updatedProduk) {
        return null;
    }
}
