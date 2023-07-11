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
package org.eclipse.digitaltwin.aas4j.v3.dataformat.json;

import org.eclipse.digitaltwin.aas4j.v3.dataformat.DeserializationException;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Deserializer Interface for deserialization of referables
 */
public interface ReferableDeserializer {

     /**
      * Deserializes a given string into an instance of
      * the given Referable
      *
      * @param referable a string representation of the
      * Referable
      * @param outputClass most specific class of the given Referable
      * @param <T> type of the returned element
      * @return an instance of the referable
      * @throws DeserializationException
      */
     <T extends Referable> T readReferable(String referable, Class<T> outputClass) throws DeserializationException;

     /**
      * Deserializes a given input stream into an instance of the given Referable using DEFAULT_CHARSET
      *
      * @param src a input stream representing a Referable
      * @param outputClass most specific class of the given Referable
      * @param <T> type of the returned element
      * @return an instance of the referable
      * @throws DeserializationException if deserialization fails
      */
     <T extends Referable> T readReferable(InputStream src, Class<T> outputClass) throws DeserializationException;

     /**
      * Deserializes a given string into an instance of
      * a list of the given Referables
      *
      * @param referables a string representation of an
      * array of Referables
      * @param outputClass most specific class of the given Referable
      * @param <T> type of the returned element
      * @return an instance of a list of the referables
      * @throws DeserializationException
      */
     <T extends Referable> List<T> readReferables(String referables, Class<T> outputClass) throws DeserializationException;

     /**
      * Deserializes a given input stream into an instance of a list of the given Referable
      *
      * @param src a input stream representing a Referable
      * @param outputClass most specific class of the given Referable
      * @param <T> type of the returned element
      * @return an instance of the referable
      * @throws DeserializationException if deserialization fails
      */
     public <T extends Referable> List<T> readReferables(InputStream src, Class<T> outputClass) throws DeserializationException;

}
