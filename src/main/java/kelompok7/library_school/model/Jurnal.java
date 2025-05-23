package kelompok7.library_school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "jurnal")
public class Jurnal extends Buku {


    @Column(nullable = false)
    private String edisi;

    @Column(nullable = false)
    private String frekuensiTerbit;

    @Column(nullable = false)
    private String bidang;

    @Column(nullable = false)
    private String volume;

    public String getEdisi() {
        return edisi;
    }

    public void setEdisi(String edisi) {
        this.edisi = edisi;
    }

    public String getFrekuensiTerbit() {
        return frekuensiTerbit;
    }

    public void setFrekuensiTerbit(String frekuensiTerbit) {
        this.frekuensiTerbit = frekuensiTerbit;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    
}
