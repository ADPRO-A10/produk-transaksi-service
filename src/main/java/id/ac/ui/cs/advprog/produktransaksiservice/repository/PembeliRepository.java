package id.ac.ui.cs.advprog.produktransaksiservice.repository;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PembeliRepository extends JpaRepository<Pembeli, String>{
    Pembeli findPembeliByUsername(String username);
    Pembeli findByUserId(Long userId);
}
