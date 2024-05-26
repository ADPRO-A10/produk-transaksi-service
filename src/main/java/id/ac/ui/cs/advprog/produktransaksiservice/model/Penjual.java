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
@NoArgsConstructor
@Entity
public class Penjual extends UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @OneToMany(
            mappedBy = "transaksiPenjual",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaksi> riwayatTransaksi = new ArrayList<>();

    @Column
    private String username;

    @ManyToMany
    @JoinTable(
            name = "katalog_penjual",
            joinColumns = @JoinColumn(name = "penjual_id"),
            inverseJoinColumns = @JoinColumn(name = "produk_id")
    )
    private List<Produk> katalog = new ArrayList<>();


    public Penjual(Long userId, String firstName, String lastName, String username, String password, String email, String role, LocalDate createdAt, boolean active, String bio, String profilePhoto, double balance, String address, String phoneNumber) {
        super(userId, firstName, lastName, username, password, email, role, createdAt, active, bio, profilePhoto, balance);
        this.riwayatTransaksi = riwayatTransaksi != null ? riwayatTransaksi : new ArrayList<>();
        this.katalog = katalog != null ? katalog : new ArrayList<>();
    }
}