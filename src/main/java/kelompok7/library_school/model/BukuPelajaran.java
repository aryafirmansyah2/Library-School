package kelompok7.library_school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bukuPelajaran")
public class BukuPelajaran {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String maPel;

    @Column(nullable = false)
    private String tingkatKelas;

    @Column(nullable = false)
    private String kurikulum;

    public long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getmaPel() {
        return maPel;
    }

    public void setmaPel(String maPel) {
        this.maPel = maPel;
    }

    public String getkurikulum() {
        return kurikulum;
    }

    public void setKurikulum(String kurikulum) {
        this.kurikulum = kurikulum;
    }

    
}
