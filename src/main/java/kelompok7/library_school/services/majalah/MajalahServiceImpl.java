package kelompok7.library_school.services.majalah;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.Majalah;
import kelompok7.library_school.repository.MajalahRepository;

@Service
public class MajalahServiceImpl implements MajalahService {
    @Autowired
    private MajalahRepository repository;

    @Override
    public List<Majalah> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Majalah> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Majalah create(Majalah majalah) {
        return repository.save(majalah);
    }

    @Override
        public Majalah update(long id, Majalah dataMajalah) {
        return repository.findById(id).map(existing -> {
            if (dataMajalah.getJudul() != null)
                existing.setJudul(dataMajalah.getJudul());
            if (dataMajalah.getPenulis() != null)
                existing.setPenulis(dataMajalah.getPenulis());
            if (dataMajalah.getPenerbit() != null)
                existing.setPenerbit(dataMajalah.getPenerbit());
            if (dataMajalah.getTahunTerbit() != null)
                existing.setTahunTerbit(dataMajalah.getTahunTerbit());
            if (dataMajalah.getDeskripsi() != null)
                existing.setDeskripsi(dataMajalah.getDeskripsi());
            if (dataMajalah.getCover() != null)
                existing.setCover(dataMajalah.getCover());
            if (dataMajalah.getHalaman() != 0)
                existing.setHalaman(dataMajalah.getHalaman());
            if (dataMajalah.getJumlah() != 0)
                existing.setJumlah(dataMajalah.getJumlah());
            existing.setAvailable(dataMajalah.isAvailable());
            
            if (dataMajalah.getEdisi() != null)
                existing.setEdisi(dataMajalah.getEdisi());
            if (dataMajalah.getFrekuensiTerbit() != null)
                existing.setFrekuensiTerbit(dataMajalah.getFrekuensiTerbit());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Jurnal tidak ditemukan"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Majalah> searchByJudul(String keyword) {
        return repository.findByJudulContainingIgnoreCase(keyword);
    }
}
