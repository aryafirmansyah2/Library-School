package kelompok7.library_school.services.peminjaman;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kelompok7.library_school.model.Buku;
import kelompok7.library_school.model.BukuPelajaran;
import kelompok7.library_school.model.Jurnal;
import kelompok7.library_school.model.Majalah;
import kelompok7.library_school.model.Peminjaman;
import kelompok7.library_school.model.User;

import kelompok7.library_school.repository.BukuPelajaranRepository;
import kelompok7.library_school.repository.JurnalRepository;
import kelompok7.library_school.repository.MajalahRepository;
import kelompok7.library_school.repository.PeminjamanRepository;

@Service
public class PeminjamanServiceImpl implements PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private BukuPelajaranRepository bukuPelajaranRepository;

    @Autowired
    private JurnalRepository jurnalRepository;

    @Autowired
    private MajalahRepository majalahRepository;

    @Override
    public List<Peminjaman> getAll() {
        return peminjamanRepository.findAll();
    }

    @Override
    public Optional<Peminjaman> getById(Long id) {
        return peminjamanRepository.findById(id);
    }

    @Override
    @Transactional
    public Peminjaman create(Peminjaman peminjaman) {
        Buku buku = findBukuById(peminjaman.getBuku().getId())
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        if (!buku.isAvailable() || buku.getJumlah() <= 0) {
            throw new RuntimeException("Buku tidak tersedia");
        }

        buku.setJumlah(buku.getJumlah() - 1);
        if (buku.getJumlah() == 0) {
            buku.setAvailable(false);
        }

        // Simpan perubahan buku berdasarkan tipe
        if (buku instanceof BukuPelajaran) {
            bukuPelajaranRepository.save((BukuPelajaran) buku);
        } else if (buku instanceof Jurnal) {
            jurnalRepository.save((Jurnal) buku);
        } else if (buku instanceof Majalah) {
            majalahRepository.save((Majalah) buku);
        }

        peminjaman.setTanggalPeminjaman(LocalDate.now());
        peminjaman.setTanggalPengembalian(LocalDate.now().plusDays(7));
        peminjaman.setSudahDikembalikan(false);

        return peminjamanRepository.save(peminjaman);
    }

    @Override
    public void delete(Long id) {
        peminjamanRepository.deleteById(id);
    }

    @Override
    public List<Peminjaman> getByUserId(Long userId) {
        User user = new User();
        user.setId(userId);
        return peminjamanRepository.findByUser(user);
    }

    @Override
    public List<Peminjaman> getByBukuId(Long bukuId) {
        Buku buku = findBukuById(bukuId)
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));
        return peminjamanRepository.findByBuku(buku);
    }

    @Override
    public long countAktifByBukuId(Long bukuId) {
        Buku buku = findBukuById(bukuId)
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));
        return peminjamanRepository.countByBukuAndSudahDikembalikanFalse(buku);
    }

    @Override
    public List<Peminjaman> getAktifPeminjaman() {
        return peminjamanRepository.findBySudahDikembalikanFalse();
    }

    @Override
    @Transactional
    public Peminjaman kembalikanBuku(Long peminjamanId) {
        Peminjaman peminjaman = peminjamanRepository.findById(peminjamanId)
                .orElseThrow(() -> new RuntimeException("Peminjaman tidak ditemukan"));

        if (peminjaman.isSudahDikembalikan()) {
            throw new RuntimeException("Buku sudah dikembalikan");
        }

        // Update status pengembalian
        peminjaman.setSudahDikembalikan(true);
        peminjaman.setTanggalPengembalian(LocalDate.now());

        // Tambah jumlah buku
        Buku buku = findBukuById(peminjaman.getBuku().getId())
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        buku.setJumlah(buku.getJumlah() + 1);
        buku.setAvailable(true);

        if (buku instanceof BukuPelajaran) {
            bukuPelajaranRepository.save((BukuPelajaran) buku);
        } else if (buku instanceof Jurnal) {
            jurnalRepository.save((Jurnal) buku);
        } else if (buku instanceof Majalah) {
            majalahRepository.save((Majalah) buku);
        }

        return peminjamanRepository.save(peminjaman);
    }

    @Override
    public Optional<Buku> findBukuById(Long id) {
        Optional<? extends Buku> buku = bukuPelajaranRepository.findById(id);
        if (buku.isEmpty())
            buku = jurnalRepository.findById(id);
        if (buku.isEmpty())
            buku = majalahRepository.findById(id);
        return buku.map(b -> (Buku) b);
    }

}
