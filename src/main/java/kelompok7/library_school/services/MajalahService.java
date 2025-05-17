package kelompok7.library_school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.Majalah;
import kelompok7.library_school.repository.MajalahRepository;

@Service
public class MajalahService {
    @Autowired
    private MajalahRepository repository;

    public List<Majalah> getAll(){
        return repository.findAll();
    }

    public Optional<Majalah> getById(Long Id) {
        return repository.findById(Id);
    }

    public Majalah create(Majalah majalah) {
        return repository.save(majalah);
    }

    public Majalah update(Long id, Majalah dataMajalah) {
        return repository.findById(id).map(majalah ->{
            majalah.setJudul(dataMajalah.getJudul());
            majalah.setPenulis(dataMajalah.getPenulis());
            majalah.setPenerbit(dataMajalah.getPenerbit());
            majalah.setTahunTerbit(dataMajalah.getTahunTerbit());
            majalah.setDeskripsi(dataMajalah.getDeskripsi());
            majalah.setCover(dataMajalah.getCover());
            majalah.setHalaman(dataMajalah.getHalaman());
            majalah.setJumlah(dataMajalah.getJumlah());
            majalah.setAvailable(dataMajalah.isAvailable());
            majalah.setEdisi(dataMajalah.getEdisi());
            majalah.setFrekuensiTerbit(dataMajalah.getFrekuensiTerbit());
            return repository.save(majalah);
        }).orElseThrow();
    }

    public void delete(Long id) {
            repository.deleteById(id);
    }
}

