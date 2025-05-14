package kelompok7.library_school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.Jurnal;
import kelompok7.library_school.repository.JurnalRepository;

@Service
public class JurnalService {

    @Autowired
    private JurnalRepository repository;

    // Get all Jurnals
    public List<Jurnal> getAll() {
        return repository.findAll();
    }

    // Get Jurnal by ID
    public Optional<Jurnal> getById(Long id) {
        return repository.findById(id);
    }

    // Create a new Jurnal
    public Jurnal create(Jurnal jurnal) {
        return repository.save(jurnal);
    }

    // Update an existing Jurnal
    public Jurnal update(Long id, Jurnal jurnalData) {
        return repository.findById(id).map(jurnal -> {
            jurnal.setJudul(jurnalData.getJudul());
            jurnal.setPenulis(jurnalData.getPenulis());
            jurnal.setTahunPublikasi(jurnalData.getTahunPublikasi());
            jurnal.setHalaman(jurnalData.getHalaman());
            jurnal.setBidang(jurnalData.getBidang());
            jurnal.setVolume(jurnalData.getVolume());
            jurnal.setPenerbit(jurnalData.getPenerbit());
            jurnal.setTahunTerbit(jurnalData.getTahunTerbit());
            jurnal.setDeskripsi(jurnalData.getDeskripsi());
            jurnal.setCover(jurnalData.getCover());
            jurnal.setAvailable(jurnalData.isAvailable());
            jurnal.setJumlah(jurnalData.getJumlah());
            return repository.save(jurnal);
        }).orElseThrow();
    }

    // Delete Jurnal by ID
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
