package id.ac.ui.cs.advprog.produktransaksiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {"id.ac.ui.cs.advprog.produktransaksiservice.repository.TransaksiRepository"})
public class ProdukTransaksiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdukTransaksiServiceApplication.class, args);
    }

}