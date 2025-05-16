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

    public List<Jurnal> getAll() {
        return repository.findAll();
    }

    public Optional<Jurnal> getById(Long id){
        return repository.findById(id);
    }

    public Jurnal create(Jurnal jurnal){
        return repository.save(jurnal);
    }

    public Jurnal update(long id, Jurnal dataJurnal){
        return repository.findById(id).map(jurnal ->{
            jurnal.setEdisi(dataJurnal.getEdisi());
            jurnal.setFrekuensiTerbit(dataJurnal.getFrekuensiTerbit());
            jurnal.setBidang(dataJurnal.getBidang());
            jurnal.setVolume(dataJurnal.getVolume());
            jurnal.setJudul(dataJurnal.getJudul());
            jurnal.setPenulis(dataJurnal.getPenulis());
            jurnal.setPenerbit(dataJurnal.getPenerbit());
            jurnal.setTahunTerbit(dataJurnal.getTahunTerbit());
            jurnal.setDeskripsi(dataJurnal.getDeskripsi());
            jurnal.setCover(dataJurnal.getCover());
            jurnal.setHalaman(dataJurnal.getHalaman());
            jurnal.setJumlah(dataJurnal.getJumlah());
            jurnal.setAvailable(dataJurnal.isAvailable());
            return repository.save(jurnal);
        }).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
