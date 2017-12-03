// Copyright 2017 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.graphql.rejoiner;

import static com.google.common.truth.Truth.assertThat;

import graphql.Scalars;
import graphql.schema.GraphQLEnumType;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit tests for {@link com.google.api.graphql.rejoiner.SchemaToProto}. */
@RunWith(JUnit4.class)
public final class SchemaToProtoTest {

  @Test
  public void emptySchemaShouldGenerateCorrectProto() {
    assertThat(
            SchemaToProto.toProto(
                GraphQLSchema.newSchema()
                    .query(GraphQLObjectType.newObject().name("queryType"))
                    .build()))
        .isEqualTo(
            "// LINT: LEGACY_NAMES\n"
                + "// Autogenerated. Do not edit.\n"
                + "syntax = \"proto3\";\n"
                + "\n"
                + "package google.api.graphql.rejoiner.proto;\n"
                + "\n"
                + "option java_package = \"com.google.api.graphql.rejoiner.proto\";\n"
                + "option (jspb.js_namespace) = \"com.google.api.graphql.rejoiner.proto\";\n"
                + "import \"java/com/google/apps/jspb/jspb.proto\";\n"
                + "\n"
                + "message queryType {\n"
                + "option (jspb.message_id) = \"corp.graphql.queryType\";\n"
                + "}");
  }

  @Test
  public void scalarValuesShouldGenerateCorrectProto() {
    assertThat(
            SchemaToProto.toProto(
                GraphQLSchema.newSchema()
                    .query(
                        GraphQLObjectType.newObject()
                            .name("queryType")
                            .field(
                                GraphQLFieldDefinition.newFieldDefinition()
                                    .name("string")
                                    .staticValue(new Object())
                                    .type(Scalars.GraphQLString))
                            .field(
                                GraphQLFieldDefinition.newFieldDefinition()
                                    .name("boolean")
                                    .staticValue(new Object())
                                    .type(Scalars.GraphQLBoolean))
                            .field(
                                GraphQLFieldDefinition.newFieldDefinition()
                                    .name("float")
                                    .staticValue(new Object())
                                    .type(Scalars.GraphQLFloat))
                            .field(
                                GraphQLFieldDefinition.newFieldDefinition()
                                    .name("int")
                                    .staticValue(new Object())
                                    .type(Scalars.GraphQLInt))
                            .field(
                                GraphQLFieldDefinition.newFieldDefinition()
                                    .name("long")
                                    .staticValue(new Object())
                                    .type(Scalars.GraphQLLong)))
                    .build()))
        .isEqualTo(
            "// LINT: LEGACY_NAMES\n"
                + "// Autogenerated. Do not edit.\n"
                + "syntax = \"proto3\";\n"
                + "\n"
                + "package google.api.graphql.rejoiner.proto;\n"
                + "\n"
                + "option java_package = \"com.google.api.graphql.rejoiner.proto\";\n"
                + "option (jspb.js_namespace) = \"com.google.api.graphql.rejoiner.proto\";\n"
                + "import \"java/com/google/apps/jspb/jspb.proto\";\n"
                + "\n"
                + "message queryType {\n"
                + "option (jspb.message_id) = \"corp.graphql.queryType\";\n"
                + "string string = 1;\n"
                + "bool boolean = 2;\n"
                + "float float = 3;\n"
                + "int32 int = 4;\n"
                + "int64 long = 5;\n"
                + "}");
  }

  @Test
  public void schemaWithEnumsShouldGenerateCorrectProto() {
    assertThat(
            SchemaToProto.toProto(
                GraphQLSchema.newSchema()
                    .query(
                        GraphQLObjectType.newObject()
                            .name("queryType")
                            .field(
                                GraphQLFieldDefinition.newFieldDefinition()
                                    .name("enum")
                                    .type(
                                        GraphQLEnumType.newEnum()
                                            .name("enumType")
                                            .value("a")
                                            .value("b")
                                            .value("c")
                                            .build())))
                    .build()))
        .isEqualTo(
            "// LINT: LEGACY_NAMES\n"
                + "// Autogenerated. Do not edit.\n"
                + "syntax = \"proto3\";\n"
                + "\n"
                + "package google.api.graphql.rejoiner.proto;\n"
                + "\n"
                + "option java_package = \"com.google.api.graphql.rejoiner.proto\";\n"
                + "option (jspb.js_namespace) = \"com.google.api.graphql.rejoiner.proto\";\n"
                + "import \"java/com/google/apps/jspb/jspb.proto\";\n"
                + "\n"
                + "message queryType {\n"
                + "option (jspb.message_id) = \"corp.graphql.queryType\";\n"
                + "enumType.Enum enum = 1;\n"
                + "}\n"
                + "\n"
                + "message enumType {\n"
                + " option (jspb.message_id) = \"corp.graphql.enumType\";\n"
                + " enum Enum {\n"
                + "a = 0;\n"
                + "b = 1;\n"
                + "c = 2;\n"
                + "}\n"
                + "}");
  }
}
