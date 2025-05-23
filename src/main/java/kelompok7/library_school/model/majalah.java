package kelompok7.library_school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "majalah")
public class Majalah extends Buku {

    @Column(nullable = false)
    private String edisi;

    @Column(nullable = false)
    private String frekuensiTerbit;

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
    
    
}
