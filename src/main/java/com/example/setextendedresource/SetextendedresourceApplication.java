package com.example.setextendedresource;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SetextendedresourceApplication {

	public static void main(String[] args) throws IOException, ApiException {
		SpringApplication.run(SetextendedresourceApplication.class, args);


		// loading the in-cluster config, including:
		//   1. service-account CA
		//   2. service-account bearer-token
		//   3. service-account namespace
		//   4. master endpoints(ip, port) from pre-set environment variables
		ApiClient client = ClientBuilder.cluster().build();

		// set the global default api-client to the in-cluster one from above
		Configuration.setDefaultApiClient(client);

		// the CoreV1Api loads default api-client from global configuration.
		CoreV1Api api = new CoreV1Api();

		// invokes the CoreV1Api client
		V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, false);
		for (V1Pod item : list.getItems()) {
			System.out.println(item.getMetadata().getName());
		}
	}




}
