package io.microsamples.geochachkies.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class ServerApplication {

	@org.springframework.beans.factory.annotation.Value("classpath:data/flight-data.json")
	private Resource resourceFile;

	@Autowired
	private ObjectMapper mapper;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	@SneakyThrows
	RouterFunction<ServerResponse> getAllChachkiesRoute() {
		final Chachkie[] chachkies = mapper.readValue(resourceFile.getFile(), new TypeReference<>() {
		});

		return route(GET("/chachkies"),
				req -> ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(Flux.fromArray(chachkies), Chachkie.class));
	}

	@Bean
	CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}

}

@Value
class Chachkie {
	Double longitude, latitude, height;
}