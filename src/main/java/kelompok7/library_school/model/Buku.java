package kelompok7.library_school.model;

public abstract class Buku {
    private String id;
    private String judul;
    private String penulis;
    private String penerbit;
    private String tahunTerbit;
    private String deskripsi;
    private String cover;
    private int halaman;
    private int jumlah;
    protected boolean available;

    public Buku(String id, String judul, String penulis, String penerbit, String tahunTerbit, String deskripsi, String cover, boolean available) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.deskripsi = deskripsi;
        this.cover = cover;
        this.available = available;
    }

    public Buku[] getRekomendasi() {
        return new Buku[0];
    }

    public String getJudul() {
        return judul;
    }

    protected void checkOut() {
        if (available && jumlah > 0) {
            jumlah--;
            if (jumlah == 0)
                available = false;
        }
    }

    protected void checkIn() {
        jumlah++;
        available = true;
    }
}
