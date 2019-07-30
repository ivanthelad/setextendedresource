package com.example.setextendedresource;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.NodeListBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateNodeTest {

        @Rule
        public KubernetesServer server = new KubernetesServer(true, true);

        @Test
        public void testList() {
            server.expect().withPath("/api/v1/nodes").andReturn(200, new NodeListBuilder().addNewItem().and().build()).once();

            KubernetesClient client = server.getClient();
            NodeList nodeList = client.nodes().list();
            List<Node> nodeList2 = client.nodes().list().getItems();

            for (Node node : nodeList2) {
                System.out.println(node.getMetadata().getName());
            }
            assertNotNull(nodeList);
        }


}
