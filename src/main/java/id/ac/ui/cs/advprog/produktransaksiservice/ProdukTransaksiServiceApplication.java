package id.ac.ui.cs.advprog.produktransaksiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ProdukTransaksiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdukTransaksiServiceApplication.class, args);
    }

}
