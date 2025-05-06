package kelompok7.library_school;

import org.springframework.boot.SpringApplication;

public class TestLibrarySchoolApplication {

	public static void main(String[] args) {
		SpringApplication.from(LibrarySchoolApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
