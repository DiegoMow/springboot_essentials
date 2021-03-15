package br.com.devdojo.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Sort;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

@JsonComponent
public class CustomSortDeserializer extends JsonDeserializer<Sort> {

    @Override
    public Sort deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectNode node = jsonParser.getCodec().readTree(jsonParser);
        Sort.Order[] orders = new Sort.Order[node.size()];
        int i = 0;
        for (JsonNode json : node) {
            if (json.has("direction") && json.has("direction")) {
                orders[i] = new Sort.Order(Sort.Direction.valueOf(json.get("direction").asText()), json.get("property").asText());
                i++;
            }
        }
        return Sort.by(orders);
    }

}
