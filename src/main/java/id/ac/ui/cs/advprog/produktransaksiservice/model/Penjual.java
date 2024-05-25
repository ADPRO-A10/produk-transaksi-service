package id.ac.ui.cs.advprog.produktransaksiservice.model;

import id.ac.ui.cs.advprog.produktransaksiservice.dto.UserDTO;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Penjual extends UserDTO {
    private List<Transaksi> riwayatTransaksi;
    private List<Produk> katalog;

    public Penjual(Long userId, String firstName, String lastName, String username, String password, String email, String role, LocalDate createdAt, boolean active, String bio, String profilePhoto, double balance, String address, String phoneNumber) {
        super(userId, firstName, lastName, username, password, email, role, createdAt, active, bio, profilePhoto, balance);
        this.riwayatTransaksi = riwayatTransaksi;
        this.katalog = katalog;
    }
}