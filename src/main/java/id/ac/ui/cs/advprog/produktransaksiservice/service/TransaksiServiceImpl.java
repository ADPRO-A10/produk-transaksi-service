package id.ac.ui.cs.advprog.produktransaksiservice.service;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaksiServiceImpl implements TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Override
    public Transaksi createTransaksi(Transaksi transaksi) {
        return transaksiRepository.createTransaksi(transaksi);
    }
    @Override
    public Transaksi checkout(Long transaksiId) {
        return transaksiRepository.findTransaksiById(transaksiId);
    }
}