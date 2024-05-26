package id.ac.ui.cs.advprog.produktransaksiservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "Review")
@Table(name = "review")
public class Review {
    @Id
    String ReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produk_id")
    Produk produk;

    String reviewerName;
    String reviewText;
    int rating;

    public Review() {
        this.ReviewId = UUID.randomUUID().toString();
    }

    public Review(Produk product, String reviewerName, String reviewText, int rating) {
        this.produk = product;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        setRating(rating);
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating tidak valid");
        }
        this.rating = rating;
    }
}
