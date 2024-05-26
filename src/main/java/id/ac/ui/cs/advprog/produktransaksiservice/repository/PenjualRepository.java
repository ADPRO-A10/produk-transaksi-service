package id.ac.ui.cs.advprog.produktransaksiservice.repository;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PenjualRepository extends JpaRepository<Penjual, String>{
    Penjual findPenjualByUsername(String username);
    Penjual findByUserId(Long userId);
}
