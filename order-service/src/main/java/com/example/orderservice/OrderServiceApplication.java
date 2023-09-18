package com.example.orderservice;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static java.util.stream.Collectors.joining;


@SpringBootApplication
@AllArgsConstructor
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
public class OrderServiceApplication {


    public static void mapAppender(Map<String, String> result, Map.Entry<String, JsonNode> node, List<String> names) {
        names.add(node.getKey());
        if (node.getValue().isTextual()) {
            String name = names.stream().collect(joining("."));
            result.put(name, node.getValue().asText());
        } else {
            node.getValue().fields()
                    .forEachRemaining(nested -> mapAppender(result, nested, new ArrayList<>(names)));
        }
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(OrderServiceApplication.class, args);

        /*PlaceOrderAggregator aggregator3 = new PlaceOrderAggregator();
        aggregator3.getItemDetails().add(
                PlaceOrderAggregator.ItemDetail
                        .builder()
                        .itemName("Item-01")
                        .price(10.50)
                        .qty(2)
                        .itemDetails1(new ArrayList<>())
                        .build()
        );
        aggregator3.getItemDetails().add(
                PlaceOrderAggregator.ItemDetail
                        .builder()
                        .itemName("Item-01")
                        .price(10.50)
                        .itemDetails1(new ArrayList<>())
                        .qty(2)
                        .build()
        );
        aggregator3.getItemDetails().get(0).getItemDetails1().
                add(
                        PlaceOrderAggregator.ItemDetail1
                                .builder()
                                .itemName("Item-0134")
                                .price(10.50)
                                .qty(2)
                                .build()
                );
        List<String> keys = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setConfig(mapper.getSerializationConfig().with(SORT_PROPERTIES_ALPHABETICALLY));

        String string = mapper.writeValueAsString(aggregator3);
        getAllClasses(PlaceOrderAggregator.class);
//        JsonObject jsonObject = JsonParser.parseString(string).getAsJsonObject();

        // Get all nested keys from the JsonObject
        *//*for (int i = 0; i < 100; i++) {
            String allKeys = getSha3_256HexForJson(string);
            System.out.println("allKeys = " + allKeys);
        }*/
    }


}
