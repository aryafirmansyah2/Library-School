package kelompok7.library_school.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Folder buku pelajaran
        Path bukuPelajaranDir = Paths.get("public/uploads/buku_pelajaran");
        String bukuPelajaranPath = bukuPelajaranDir.toFile().getAbsolutePath();

        // Folder jurnal
        Path jurnalDir = Paths.get("public/uploads/jurnal");
        String jurnalPath = jurnalDir.toFile().getAbsolutePath();

        // Folder majalah
        Path majalahDir = Paths.get("public/uploads/majalah");
        String majalahPath = majalahDir.toFile().getAbsolutePath();

        // Mapping URL ke folder fisik
        registry.addResourceHandler("/uploads/buku_pelajaran/**")
                .addResourceLocations("file:" + bukuPelajaranPath + "/");

        registry.addResourceHandler("/uploads/jurnal/**")
                .addResourceLocations("file:" + jurnalPath + "/");

        registry.addResourceHandler("/uploads/majalah/**")
                .addResourceLocations("file:" + majalahPath + "/");
    }
}
