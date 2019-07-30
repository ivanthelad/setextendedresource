package com.example.setextendedresource;

import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kubernetes.client.ApiException;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SetextendedresourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SetextendedresourceApplication.class, args);


		KubernetesClient client = new DefaultKubernetesClient();
	//	OkHttpClient client = new DefaultKubernetesClient().getHttpClient();


		List<Node> nodelist = client.nodes().list().getItems();
		for (Node node : nodelist) {
			System.out.println( "editing " +node.toString());
			client.nodes().withName(node.getMetadata().getName())
					.edit()
						.editStatus()
							.addToCapacity("example.com/dongle2", new Quantity("13"))
						.and()
					.done();
			client.nodes().withName(node.getMetadata().getName());
			Node mynode =  client.nodes().withName(node.getMetadata().getName()).get();
			System.out.println("----" +mynode.getMetadata().getName() );

		}

		List<Node> nodelist2 = client.nodes().list().getItems();
		for (Node node : nodelist2) {
			System.out.println("giotnode " + node.getStatus().getCapacity());
		}
 		while(true){
			try {
				Thread.sleep(100000);
				System.out.println("sleep");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		}
	}





