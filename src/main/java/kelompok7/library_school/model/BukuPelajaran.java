package kelompok7.library_school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bukuPelajaran")
public class BukuPelajaran extends Buku {

    @Column(nullable = false)
    private String maPel;

    @Column(nullable = false)
    private String tingkatKelas;

    @Column(nullable = false)
    private String kurikulum;

    public String getMaPel() {
        return maPel;
    }

    public void setMaPel(String maPel) {
        this.maPel = maPel;
    }

    public String getKurikulum() {
        return kurikulum;
    }

    public void setKurikulum(String kurikulum) {
        this.kurikulum = kurikulum;
    }

    public String getTingkatKelas() {
        return tingkatKelas;
    }

    public void setTingkatKelas(String tingkatKelas) {
        this.tingkatKelas = tingkatKelas;
    }

    
}
