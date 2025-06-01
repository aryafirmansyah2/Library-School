package kelompok7.library_school.services.jurnal;

import java.util.List;
import java.util.Optional;
import kelompok7.library_school.model.Jurnal;

public interface JurnalService {
    List<Jurnal> getAll();

    Optional<Jurnal> getById(Long id);

    Jurnal create(Jurnal jurnal);

    Jurnal update(long id, Jurnal dataJurnal);

    void delete(Long id);

    List<Jurnal> searchByJudul(String keyword);
}
