package kelompok7.library_school.services.buku_pelajaran;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kelompok7.library_school.model.BukuPelajaran;
import kelompok7.library_school.repository.BukuPelajaranRepository;


@Service
public class BukuPelajaranServiceImpl implements BukuPelajaranService{
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

    public BukuPelajaran update(long id, BukuPelajaran dataBuku) {
        return repository.findById(id).map(existing -> {
            if (dataBuku.getJudul() != null) existing.setJudul(dataBuku.getJudul());
            if (dataBuku.getPenulis() != null) existing.setPenulis(dataBuku.getPenulis());
            if (dataBuku.getPenerbit() != null) existing.setPenerbit(dataBuku.getPenerbit());
            if (dataBuku.getTahunTerbit() != null) existing.setTahunTerbit(dataBuku.getTahunTerbit());
            if (dataBuku.getDeskripsi() != null) existing.setDeskripsi(dataBuku.getDeskripsi());
            if (dataBuku.getCover() != null) existing.setCover(dataBuku.getCover());
            if (dataBuku.getHalaman() != 0) existing.setHalaman(dataBuku.getHalaman());
            if (dataBuku.getJumlah() != 0) existing.setJumlah(dataBuku.getJumlah());
            existing.setAvailable(dataBuku.isAvailable());
    
            if (dataBuku.getMaPel() != null) existing.setMaPel(dataBuku.getMaPel());
            if (dataBuku.getTingkatKelas() != null) existing.setTingkatKelas(dataBuku.getTingkatKelas());
            if (dataBuku.getKurikulum() != null) existing.setKurikulum(dataBuku.getKurikulum());
    
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("BukuPelajaran tidak ditemukan"));
    }
    

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<BukuPelajaran> searchByKeyword(String keyword) {
        return repository.findByJudulContainingIgnoreCaseOrMaPelContainingIgnoreCase(keyword, keyword);
    }
}
