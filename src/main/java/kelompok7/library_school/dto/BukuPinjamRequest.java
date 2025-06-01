package kelompok7.library_school.dto;

public class BukuPinjamRequest {
    private Long bukuId;
    private int jumlah;

// Getters and setters
    public Long getBukuId() {
        return bukuId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
    this.jumlah = jumlah;
    }
}
