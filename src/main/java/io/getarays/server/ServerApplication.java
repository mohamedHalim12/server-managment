package io.getarays.server;

import io.getarays.server.enumeration.Status;
import io.getarays.server.model.Server;
import io.getarays.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static io.getarays.server.enumeration.Status.*;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(
					new Server(null,
							"192.168.1.160",
							"Ubuntu linux",
							"16 GB",
							"Personal Pc",
							"http://localhost:8080/server/image/server1.png",
							SERVER_UP)
			);
			serverRepo.save(
					new Server(null,
							"192.168.1.58",
							"Fedora linux",
							"16 GB",
							"Dell Tower",
							"http://localhost:8080/server/image/server2.png",
							SERVER_DOWN)
			);
			serverRepo.save(
					new Server(null,
							"192.168.1.21",
							"MS 2008",
							"32 GB",
							"Web Server",
							"http://localhost:8080/server/image/server3.png",
							SERVER_UP)
			);
			serverRepo.save(
					new Server(null,
							"192.168.1.14",
							"Red Hat Entrepriselinux",
							"64 GB",
							"Mail Server",
							"http://localhost:8080/server/image/server4.png",
							SERVER_DOWN)
			);
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:3000"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
