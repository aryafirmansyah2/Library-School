package kelompok7.library_school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BukuStatusResponse {
    private Long id;
    private String judul;
    private boolean tersedia;
    private int jumlahTersedia;
    private long jumlahDipinjam;
}