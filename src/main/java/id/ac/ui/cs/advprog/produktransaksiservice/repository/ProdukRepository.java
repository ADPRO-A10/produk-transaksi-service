package id.ac.ui.cs.advprog.produktransaksiservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, String>{
}
