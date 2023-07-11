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

import java.util.Arrays;

import org.eclipse.digitaltwin.aas4j.v3.model.*;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultKey;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultRange;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultReference;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests the following constraint:
 * <p>
 * <i> If the semanticId of a Range references a ConceptDescription then
 * DataSpecificationIec61360/levelType shall be identical to the set {Min, Max}.
 * </i>
 * </p>
 * 
 * @author schnicke
 *
 */
@Ignore("Constraint has been removed")
public class TestAASd_069 {

	@Test
	public void correctLevelTypes() throws ValidationException {
		String conceptDescriptionId = "conceptDescription";

		ConceptDescription correctCD = getCDWithLevelType(conceptDescriptionId);

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createRange("submodelElementIdShort", conceptDescriptionId)));

		Environment correctEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(correctCD,
						ConstraintTestHelper.getIrrelevantConceptDescription()));

		ShaclValidator.getInstance().validate(correctEnv);
	}


	@Test
	public void wrongLevelTypes() {
		String conceptDescriptionWrongId = "conceptDescriptionWrong";

		ConceptDescription wrongCD = getCDWithWrongLevelType(conceptDescriptionWrongId);

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createRange("submodelElementIdShort", conceptDescriptionWrongId)));
		
		Environment wrongEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(wrongCD, ConstraintTestHelper.getIrrelevantConceptDescription()));
		try {
			ShaclValidator.getInstance().validate(wrongEnv);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"If the semanticId of a Range references a ConceptDescription then DataSpecificationIec61360/levelType shall be identical to the set {Min, Max}."));
		}
	}

	private Range createRange(String idShort, String conceptDescriptionId) {
		return new DefaultRange.Builder()
				.idShort(idShort)
				.valueType(DataTypeDefXsd.INT)
				.min("0").max("1")
				.semanticId(new DefaultReference.Builder()
						.keys(new DefaultKey.Builder()
								.value(conceptDescriptionId)
								.type(KeyTypes.CONCEPT_DESCRIPTION)
								.build())
						.type(ReferenceTypes.GLOBAL_REFERENCE)
						.build())
				.build();
	}

	private ConceptDescription getCDWithLevelType(String conceptDescriptionId) {
		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("idShort1", conceptDescriptionId,
				"PROPERTY");

//		DataSpecificationIec61360 stringDataTypeDS = new DefaultDataSpecificationIec61360.Builder()
//				.preferredName(new LangString("ds", "EN"))
//				.definition(new LangString("some english definition", "EN"))
//				.dataType(DataTypeIec61360.REAL_MEASURE)
//				.levelTypes(Arrays.asList(LevelType.MIN, LevelType.MAX))
//				.build();

//		EmbeddedDataSpecification stringDataTypeEDS = new DefaultEmbeddedDataSpecification.Builder()
//				.dataSpecificationContent(stringDataTypeDS)
//				.dataSpecification(new DefaultReference.Builder()
//						.key(new DefaultKey.Builder()
//								.idType(KeyType.CUSTOM)
//								.value("foo_key")
//								.build())
//						.build())
//				.build();

//		cd.setEmbeddedDataSpecifications(Arrays.asList(stringDataTypeEDS));

		return cd;
	}
	

	private ConceptDescription getCDWithWrongLevelType(String conceptDescriptionId) {
		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("idShort1", conceptDescriptionId,
				"PROPERTY");

//		DataSpecificationIec61360 stringDataTypeDS = new DefaultDataSpecificationIec61360.Builder()
//				.preferredName(new LangString("ds", "EN"))
//				.definition(new LangString("some english definition", "EN"))
//				.dataType(DataTypeIec61360.STRING)
//				.levelTypes(Arrays.asList(LevelType.NOM, LevelType.MAX))
//				.build();

//		EmbeddedDataSpecification stringDataTypeEDS = new DefaultEmbeddedDataSpecification.Builder()
//				.dataSpecificationContent(stringDataTypeDS)
//				.dataSpecification(new DefaultReference.Builder()
//						.key(new DefaultKey.Builder()
//								.idType(KeyType.CUSTOM)
//								.value("foo_key")
//								.build())
//						.build())
//				.build();

//		cd.setEmbeddedDataSpecifications(Arrays.asList(stringDataTypeEDS));

		return cd;
	}

}
