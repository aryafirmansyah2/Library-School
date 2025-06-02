package kelompok7.library_school.dto;

import java.util.List;

import lombok.Data;

@Data

public class PeminjamanRequest {
    private Long userId;
    private List<BukuPinjamRequest> bukuList;
}
