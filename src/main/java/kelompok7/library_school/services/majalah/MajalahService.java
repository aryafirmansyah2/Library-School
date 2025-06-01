package kelompok7.library_school.services.majalah;
import java.util.List;
import java.util.Optional;
import kelompok7.library_school.model.Majalah;

public interface MajalahService {
    List<Majalah> getAll();

    Optional<Majalah> getById(Long id);

    Majalah create(Majalah majalah);

    Majalah update(long id, Majalah dataMajalah);

    void delete(Long id);

    List<Majalah> searchByJudul(String keyword);
}
