package kelompok7.library_school.services.jurnal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.Jurnal;
import kelompok7.library_school.repository.JurnalRepository;

@Service
public class JurnalServiceImpl implements JurnalService {
    @Autowired
    private JurnalRepository repository;

    @Override
    public List<Jurnal> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Jurnal> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Jurnal create(Jurnal jurnal) {
        return repository.save(jurnal);
    }

    @Override
    public Jurnal update(long id, Jurnal dataJurnal) {
        return repository.findById(id).map(existing -> {
            if (dataJurnal.getJudul() != null)
                existing.setJudul(dataJurnal.getJudul());
            if (dataJurnal.getPenulis() != null)
                existing.setPenulis(dataJurnal.getPenulis());
            if (dataJurnal.getPenerbit() != null)
                existing.setPenerbit(dataJurnal.getPenerbit());
            if (dataJurnal.getTahunTerbit() != null)
                existing.setTahunTerbit(dataJurnal.getTahunTerbit());
            if (dataJurnal.getDeskripsi() != null)
                existing.setDeskripsi(dataJurnal.getDeskripsi());
            if (dataJurnal.getCover() != null)
                existing.setCover(dataJurnal.getCover());
            if (dataJurnal.getHalaman() != 0)
                existing.setHalaman(dataJurnal.getHalaman());
            if (dataJurnal.getJumlah() != 0)
                existing.setJumlah(dataJurnal.getJumlah());
            existing.setAvailable(dataJurnal.isAvailable());

            if (dataJurnal.getEdisi() != null)
                existing.setEdisi(dataJurnal.getEdisi());
            if (dataJurnal.getFrekuensiTerbit() != null)
                existing.setFrekuensiTerbit(dataJurnal.getFrekuensiTerbit());
            if (dataJurnal.getBidang() != null)
                existing.setBidang(dataJurnal.getBidang());
            if (dataJurnal.getVolume() != null)
                existing.setVolume(dataJurnal.getVolume());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Jurnal tidak ditemukan"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Jurnal> searchByJudul(String keyword) {
        return repository.findByJudulContainingIgnoreCase(keyword);
    }
}
