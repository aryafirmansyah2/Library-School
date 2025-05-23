package kelompok7.library_school.dto;

public class BukuPelajaranUpdateDTO {
    private String judul;
    private String penulis;
    private String penerbit;
    private String tahunTerbit;
    private String deskripsi;
    private String cover;
    private Integer halaman;
    private Integer jumlah;
    private Boolean available;
    private String maPel;
    private String tingkatKelas;
    private String kurikulum;
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
    public Integer getHalaman() {
        return halaman;
    }
    public void setHalaman(Integer halaman) {
        this.halaman = halaman;
    }
    public Integer getJumlah() {
        return jumlah;
    }
    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public String getMaPel() {
        return maPel;
    }
    public void setMaPel(String maPel) {
        this.maPel = maPel;
    }
    public String getTingkatKelas() {
        return tingkatKelas;
    }
    public void setTingkatKelas(String tingkatKelas) {
        this.tingkatKelas = tingkatKelas;
    }
    public String getKurikulum() {
        return kurikulum;
    }
    public void setKurikulum(String kurikulum) {
        this.kurikulum = kurikulum;
    }

}
