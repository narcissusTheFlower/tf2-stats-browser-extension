package org.narcissus.tf2statsbrowserextension;

import org.narcissus.tf2statsbrowserextension.logstf.LogsTFAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tf2StatsBrowserExtensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tf2StatsBrowserExtensionApplication.class, args);

		LogsTFAPI logs = LogsTFAPI.ofPlayer(76561198162623456L);

		System.out.println(logs.getLogs(1000));
	}

}
