package kelompok7.library_school.dto;

public class PeminjamanRequest {
    private Long userId;
    private Long bukuId;
    private int jumlahPeminjaman;

    public Long getUserId() {
        return userId;
    }

    public int getJumlahPeminjaman() {
        return jumlahPeminjaman;
    }

    public void setJumlahPeminjaman(int jumlahPeminjaman) {
        this.jumlahPeminjaman = jumlahPeminjaman;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBukuId() {
        return bukuId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }
}
