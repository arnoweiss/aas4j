/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
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

package org.eclipse.digitaltwin.aas4j.v3.model.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.digitaltwin.aas4j.v3.model.ConceptDescription;
import org.eclipse.digitaltwin.aas4j.v3.model.LangString;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultLangString;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests the following constraint:
 * <p>
 * <i> For all ConceptDescriptions using data specification template IEC61360
 * (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0)
 * at least a preferred name in English shall be defined. </i>
 * </p>
 * 
 * @author schnicke
 *
 */

@Ignore("Constraint has been removed")
public class TestAASd_076 {

	@Test
	public void englishPreferredName() throws ValidationException {
		LangString preferredName = new DefaultLangString.Builder().text("english").language("en").build();
		ConceptDescription cd = createConceptDescription(preferredName);

		ShaclValidator.getInstance().validate(cd);
	}

	@Test
	public void noIEC61360DataSpecification() throws ValidationException {
		ConceptDescription description = ConstraintTestHelper.createConceptDescription("testIdShort", "testId",
				"PROPERTY");

		ShaclValidator.getInstance().validate(description);
	}


	@Test
	public void noEnglishPreferredName() {
		LangString preferredName = new DefaultLangString.Builder().text("deutsch").language("de").build();
		ConceptDescription cd = createConceptDescription(preferredName);

		try {
			ShaclValidator.getInstance().validate(cd);
			fail();
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().endsWith(
					"For all ConceptDescriptions using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) at least a preferred name in English shall be defined."));
		}
	}

	private ConceptDescription createConceptDescription(LangString preferredName) {
		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("idShort1", "id",
				"QUALIFIER");

//		DataSpecificationIEC61360 urlDataTypeDS = new DefaultDataSpecificationIEC61360.Builder()
//				.preferredName(preferredName)
//				.preferredName(new LangString("test", "de"))
//				.definition(new LangString("definition", "en"))
//				.dataType(DataTypeIEC61360.URL)
//				.build();
		
//		EmbeddedDataSpecification urlDataTypeEDS = new DefaultEmbeddedDataSpecification.Builder()
//				.dataSpecificationContent(urlDataTypeDS)
//				.dataSpecification(new DefaultReference.Builder()
//						.key(new DefaultKey.Builder()
//								.idType(KeyType.CUSTOM)
//								.value("foo_key")
//								.build())
//						.build())
//				.build();

//		cd.setEmbeddedDataSpecifications(Arrays.asList(urlDataTypeEDS));
		return cd;
	}
}
