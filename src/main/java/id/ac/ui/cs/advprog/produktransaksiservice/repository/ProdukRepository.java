package id.ac.ui.cs.advprog.produktransaksiservice.repository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Review;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

public interface ProdukRepository extends JpaRepository<Produk, String>{
}
