package kelompok7.library_school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class majalah {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String edisi;
    private String frekuensiTerbit;

    public Majalah() {}

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
