package kelompok7.library_school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.BukuPelajaran;
import kelompok7.library_school.repository.BukuPelajaranRepository;

@Service
public class BukuPelajaranService {
    @Autowired
    private BukuPelajaranRepository repository;

    public List<BukuPelajaran> getAll() {
        return repository.findAll();
    }

    public Optional<BukuPelajaran> getById(Long id){
        return repository.findById(id);
    }

    public BukuPelajaran create(BukuPelajaran bukuPelajaran){
        return repository.save(bukuPelajaran);
    }

    public BukuPelajaran update(long id, BukuPelajaran dataBuku){
        return repository.findById(id).map(bukuPelajaran ->{
            bukuPelajaran.setMaPel(dataBuku.getMaPel());
            bukuPelajaran.setKurikulum(dataBuku.getKurikulum());
            bukuPelajaran.setMaPel(dataBuku.getMaPel());
            bukuPelajaran.setTingkatKelas(dataBuku.getTingkatKelas());
            bukuPelajaran.setKurikulum(dataBuku.getKurikulum());
            bukuPelajaran.setJudul(dataBuku.getJudul());
            bukuPelajaran.setPenulis(dataBuku.getPenulis());
            bukuPelajaran.setPenerbit(dataBuku.getPenerbit());
            bukuPelajaran.setTahunTerbit(dataBuku.getTahunTerbit());
            bukuPelajaran.setDeskripsi(dataBuku.getDeskripsi());
            bukuPelajaran.setCover(dataBuku.getCover());
            bukuPelajaran.setHalaman(dataBuku.getHalaman());
            bukuPelajaran.setJumlah(dataBuku.getJumlah());
            bukuPelajaran.setAvailable(dataBuku.isAvailable());
            return repository.save(bukuPelajaran);
        }).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
