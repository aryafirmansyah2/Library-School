package kelompok7.library_school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "majalah")
public class Majalah extends Buku {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String edisi;

    @Column(nullable = false)
    private String frekuensiTerbit;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
