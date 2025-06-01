package kelompok7.library_school.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "buku_id", referencedColumnName = "id")
    private Buku buku;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private LocalDate tanggalPeminjaman;

    @Column
    private LocalDate tanggalPengembalian;

    @Column(nullable = false)
    private boolean sudahDikembalikan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(LocalDate tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public LocalDate getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(LocalDate tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public boolean isSudahDikembalikan() {
        return sudahDikembalikan;
    }

    public void setSudahDikembalikan(boolean sudahDikembalikan) {
        this.sudahDikembalikan = sudahDikembalikan;
    }
}
