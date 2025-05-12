package kelompok7.library_school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity


public class BukuPelajaran extends Buku{
    private String maPel;
    private String tingkatKelas;
    private String kurikulum;

    public BukuPelajaran(String id, String judul, String penulis, int halaman, String maPel, String tingkatKelas, String kurikulum, String penerbit, String tahunTerbit, String deskripsi, String cover, boolean available, int jumlah) {
        super(id, judul, penulis, penerbit, tahunTerbit, deskripsi, cover, available);
        this.maPel = maPel;
        this.tingkatKelas = tingkatKelas;
        this.kurikulum = kurikulum;
    }


    public void borrow() {
        checkOut();
    }

    
    public void returnBook() {
        checkIn();
    }
}
