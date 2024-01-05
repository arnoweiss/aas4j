package org.eclipse.digitaltwin.aas4j.v3.dataformat.core;

import org.eclipse.digitaltwin.aas4j.v3.model.KeyTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.aas4j.v3.model.ReferenceTypes;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultKey;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultReference;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodel;

import java.util.List;

public class References {
    public static DefaultSubmodel PROPERTY_1 = new DefaultSubmodel.Builder()
            .id("https://www.sap.com/aass/my-aas/my-sm")
            .submodelElements(
                    new DefaultProperty.Builder()
                            .idShort("my-property")
                            .value("what we want")
                            .build()
            )
            .build();

    public static Reference REFERENCE_1 = new DefaultReference.Builder()
            .type(ReferenceTypes.MODEL_REFERENCE)
            .keys(List.of(
                    new DefaultKey.Builder()
                            .type(KeyTypes.SUBMODEL)
                            .value("https://www.sap.com/aass/my-aas/my-sm")
                            .build(),
                    new DefaultKey.Builder()
                            .type(KeyTypes.PROPERTY)
                            .value("my-property")
                            .build()
            ))
            .build();

}
