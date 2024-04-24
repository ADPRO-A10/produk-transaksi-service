package id.ac.ui.cs.advprog.produktransaksiservice.repository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Review;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

public class ProdukRepository {
    ArrayList<Produk> listProduk = new ArrayList<>();
    public Produk create(Produk createdProduk) {
        String namaProdukYgDiinput = createdProduk.getNama();
        for (Produk produk : listProduk) {
            if (produk.getNama().equals(namaProdukYgDiinput)) {
                throw new IllegalArgumentException("Produk dengan nama tersebut sudah ada");
            }
        }
        listProduk.add(createdProduk);
        return createdProduk;
    }

    public void deleteProduct(String id) {
        for (int i = 0; i < listProduk.size(); i++) {
            Produk product = listProduk.get(i);
            if (product.getProdukId().equals(id)) {
                listProduk.remove(product);
            }
        }
    }
    public Produk findByProductId(String id) {
        if (listProduk != null) {
            for (Produk product : listProduk) {
                if (product.getProdukId().equals(id)) {
                    return product;
                }
            }
        }
        return null;
    }

    public Iterator<Produk> findAll() {
        return listProduk.iterator();
    }

    public Produk editProduct(String id, Produk editedProduct) {
        Produk targetProduct = findByProductId(id);
        if (targetProduct != null) {
            targetProduct.setStokTersedia(editedProduct.getStokTersedia());
            targetProduct.setDeskripsi(editedProduct.getDeskripsi());
            targetProduct.setHarga(editedProduct.getHarga());
            return targetProduct;
        } else {
            throw new IllegalArgumentException("Id produk tidak ditemukan");
        }
    }
}
