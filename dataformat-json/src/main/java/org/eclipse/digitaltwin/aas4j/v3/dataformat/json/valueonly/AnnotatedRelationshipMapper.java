/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 * Copyright (C) 2023 SAP SE or an SAP affiliate company.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.digitaltwin.aas4j.v3.dataformat.json.valueonly;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.digitaltwin.aas4j.v3.model.AnnotatedRelationshipElement;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;

import java.util.ArrayList;
import java.util.List;

/**
 * AnnotatedRelationshipElement is serialized according to the serialization of a ReleationshipElement. Additionally, a
 * third named JSON object is introduced with "annotations" as the name of the containing JSON property. The value is
 * ${AnnotatedRelationshipElement/annotations}. The values of the array items are serialized depending on the type of
 * the annotation data element.
 */
public class AnnotatedRelationshipMapper extends AbstractMapper<AnnotatedRelationshipElement> {
    AnnotatedRelationshipMapper(AnnotatedRelationshipElement relationship, String idShortPath) {
        super(relationship, idShortPath);
    }

    @Override
    public JsonNode serialize() throws ValueOnlySerializationException {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.set("first", serializer.toJson(element.getFirst()));
        node.set("second", serializer.toJson(element.getSecond()));
        List<SubmodelElement> annotations = new ArrayList<>(element.getAnnotations());
        ElementsListMapper listSerializer = new ElementsListMapper(
            annotations, idShortPath + ".annotations");
        node.set("annotations", listSerializer.serialize());
        return node;
    }
}