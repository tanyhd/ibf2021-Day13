package nus.edu.workshop13;
import java.io.File;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Workshop13Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Workshop13Application.class);
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		

		if(appArgs.containsOption("dataDir")) {
			if(!appArgs.getNonOptionArgs().isEmpty()) {
				System.out.println(appArgs.getNonOptionArgs());
				String dir = appArgs.getNonOptionArgs().get(0);
				File fileDir = new File(dir);
				if(!fileDir.exists()) {
					fileDir.mkdirs();
				}
			} else {
				System.out.println("Error: No data directory is provided!");
				System.exit(1);
			}
		} else {
			System.out.println("Error: Key in --dataDir follow by data directory");
			System.exit(1);
		}

		app.run(args);
	}

}
