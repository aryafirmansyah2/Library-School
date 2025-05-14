package kelompok7.library_school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jurnal")
public class Jurnal {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false)
    private String penulis;

    @Column(nullable = false)
    private int tahunPublikasi;

    @Column(nullable = false)
    private int halaman;

    @Column(nullable = false)
    private String bidang;

    @Column(nullable = false)
    private String volume;

    @Column(nullable = false)
    private String penerbit;

    @Column(nullable = false)
    private String tahunTerbit;

    @Column(nullable = false)
    private String deskripsi;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private int jumlah;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getTahunPublikasi() {
        return tahunPublikasi;
    }

    public void setTahunPublikasi(int tahunPublikasi) {
        this.tahunPublikasi = tahunPublikasi;
    }

    public int getHalaman() {
        return halaman;
    }

    public void setHalaman(int halaman) {
        this.halaman = halaman;
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

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    // Methods for borrowing and returning
    public void pinjam() {
        if (available && jumlah > 0) {
            jumlah--;
        }
    }

    public void kembalikan() {
        jumlah++;
    }
}
