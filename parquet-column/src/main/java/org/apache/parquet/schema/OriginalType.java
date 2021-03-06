/* 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.parquet.schema;

public enum OriginalType {
  MAP,
  LIST,
  UTF8(PrimitiveStringifier.UTF8_STRINGIFIER),
  MAP_KEY_VALUE,
  ENUM(PrimitiveStringifier.UTF8_STRINGIFIER),
  DECIMAL {
    @Override
    PrimitiveStringifier stringifier(PrimitiveType type) {
      return PrimitiveStringifier.createDecimalStringifier(type.getDecimalMetadata().getScale());
    }
  },
  DATE(PrimitiveStringifier.DATE_STRINGIFIER),
  TIME_MILLIS(PrimitiveStringifier.TIME_STRINGIFIER),
  TIME_MICROS(PrimitiveStringifier.TIME_STRINGIFIER),
  TIMESTAMP_MILLIS(PrimitiveStringifier.TIMESTAMP_MILLIS_STRINGIFIER),
  TIMESTAMP_MICROS(PrimitiveStringifier.TIMESTAMP_MICROS_STRINGIFIER),
  UINT_8(PrimitiveStringifier.UNSIGNED_STRINGIFIER),
  UINT_16(PrimitiveStringifier.UNSIGNED_STRINGIFIER),
  UINT_32(PrimitiveStringifier.UNSIGNED_STRINGIFIER),
  UINT_64(PrimitiveStringifier.UNSIGNED_STRINGIFIER),
  INT_8(PrimitiveStringifier.DEFAULT_STRINGIFIER),
  INT_16(PrimitiveStringifier.DEFAULT_STRINGIFIER),
  INT_32(PrimitiveStringifier.DEFAULT_STRINGIFIER),
  INT_64(PrimitiveStringifier.DEFAULT_STRINGIFIER),
  JSON(PrimitiveStringifier.UTF8_STRINGIFIER),
  BSON(PrimitiveStringifier.DEFAULT_STRINGIFIER),
  INTERVAL(PrimitiveStringifier.INTERVAL_STRINGIFIER);

  private final PrimitiveStringifier stringifier;

  PrimitiveStringifier stringifier(PrimitiveType type) {
    if (stringifier == null) {
      throw new UnsupportedOperationException("Stringifier is not supported for the original type: " + this);
    }
    return stringifier;
  }

  OriginalType() {
    this(null);
  }

  OriginalType(PrimitiveStringifier stringifier) {
    this.stringifier = stringifier;
  }
}
