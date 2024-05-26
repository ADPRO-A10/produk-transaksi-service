package id.ac.ui.cs.advprog.produktransaksiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, UUID>{

}
