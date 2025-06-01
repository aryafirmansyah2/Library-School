package kelompok7.library_school.dto;

import java.util.List;

public class PeminjamanRequest {
    private Long userId;
    private List<BukuPinjamRequest> bukuList;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BukuPinjamRequest> getBukuList() {
        return bukuList;
    }

    public void setBukuList(List<BukuPinjamRequest> bukuList) {
        this.bukuList = bukuList;
    }

}
