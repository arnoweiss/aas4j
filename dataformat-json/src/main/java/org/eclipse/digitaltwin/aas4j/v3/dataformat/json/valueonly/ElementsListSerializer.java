package org.eclipse.digitaltwin.aas4j.v3.dataformat.json.valueonly;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;

import java.util.List;

/**
 * SubmodelElementList is serialized as a JSON array with the index of the contained SubmodelElement in the list as the
 * position in the JSON array. The elements contained within the list are serialized according to their respective type.
 */
public class ElementsListSerializer extends ElementsCollectionSerializer {
    ElementsListSerializer(List<SubmodelElement> elements, String idShortPath) {
        super(elements, idShortPath);
    }

    @Override
    public JsonNode serialize() throws ValueOnlySerializationException {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
        for(int i = 0; i < element.size(); i++) {
            SubmodelElement submodelElement = element.get(i);
            arrayNode.add(serialize(submodelElement, idShortPath + "[" + i + "]"));
        }
        return arrayNode;
    }
}