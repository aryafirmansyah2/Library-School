package kelompok7.library_school.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.Buku;
import kelompok7.library_school.repository.BukuRepository;

@Service
public class BukuService {
    @Autowired
    private BukuRepository repository;

    public List<Buku> getAll() {
        return repository.findAll();
    }

    public Optional<Buku> getById(Long id) {
        return repository.findById(id);
    }

    public Buku create(Buku buku) {
        return repository.save(buku);
    }

    public Buku update(Long id, Buku bukuData) {
        return repository.findById(id).map(buku -> {
            buku.setJudul(bukuData.getJudul());
            buku.setPenulis(bukuData.getPenulis());
            buku.setPenerbit(bukuData.getPenerbit());
            buku.setTahunTerbit(bukuData.getTahunTerbit());
            buku.setDeskripsi(bukuData.getDeskripsi());
            buku.setCover(bukuData.getCover());
            buku.setHalaman(bukuData.getHalaman());
            buku.setJumlah(bukuData.getJumlah());
            buku.setAvailable(bukuData.isAvailable());
            return repository.save(buku);
        }).orElseThrow();
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
