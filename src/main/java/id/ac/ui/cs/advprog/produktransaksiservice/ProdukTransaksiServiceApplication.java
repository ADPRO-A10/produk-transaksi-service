package id.ac.ui.cs.advprog.produktransaksiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
public class ProdukTransaksiServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(ProdukTransaksiServiceApplication.class, args);
    }

}
