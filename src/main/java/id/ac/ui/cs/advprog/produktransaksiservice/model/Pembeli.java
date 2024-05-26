package id.ac.ui.cs.advprog.produktransaksiservice.model;

import id.ac.ui.cs.advprog.produktransaksiservice.dto.UserDTO;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pembeli extends UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column
    private String username;

    @OneToMany(
            mappedBy = "transaksiPembeli",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaksi> riwayatTransaksi = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "library_pembeli",
            joinColumns = @JoinColumn(name = "pembeli_id"),
            inverseJoinColumns = @JoinColumn(name = "produk_id")
    )
    private List<Produk> library = new ArrayList<>();

    public Pembeli(Long userId, String firstName, String lastName, String username, String password, String email, String role, LocalDate createdAt, boolean active, String bio, String profilePhoto, double balance, String address, String phoneNumber) {
        super(userId, firstName, lastName, username, password, email, role, createdAt, active, bio, profilePhoto, balance);
        this.riwayatTransaksi = riwayatTransaksi != null ? riwayatTransaksi : new ArrayList<>();
        this.library = library != null ? library : new ArrayList<>();
    }
}