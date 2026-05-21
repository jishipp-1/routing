package com.country.routing.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import jakarta.annotation.PostConstruct;

/**
 * Loads country data from GitHub JSON and builds adjacency graph
 */

@Component
public class CountryDataLoader {
	private static final String DATA_URL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

	private final Map<String, List<String>> graph = new HashMap<>();

	@PostConstruct
	public void init() {

		try {

			ObjectMapper mapper = new ObjectMapper();

			URL url = new URL(DATA_URL);

			// modern non-deprecated approach
			try (InputStream inputStream = url.openStream()) {

				JsonNode root = mapper.readTree(inputStream);

				for (JsonNode country : root) {

					String cca3 = country.get("cca3").asText();

					graph.putIfAbsent(cca3, new ArrayList<>());

					JsonNode borders = country.get("borders");

					if (borders != null && borders.isArray()) {

						for (JsonNode border : borders) {

							String neighbor = border.asText();

							graph.putIfAbsent(neighbor, new ArrayList<>());

							if (!graph.get(cca3).contains(neighbor)) {

								graph.get(cca3).add(neighbor);
								graph.get(neighbor).add(cca3);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("Failed to load country data", e);
		}
	}

	public Map<String, List<String>> getGraph() {
		return graph;
	}
}