package com.FMS.fmsbackend.budget.Serializer;


import java.io.IOException;

import org.hibernate.Hibernate;

import com.FMS.fmsbackend.budget.entity.GLAccounts;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;



public class GLAccountsSerializer extends JsonSerializer<GLAccounts> {

    @Override
    public void serialize(GLAccounts value, JsonGenerator gen, SerializerProvider serializers) throws IOException, java.io.IOException {
        if (value != null) {
            GLAccounts parentGLAccount = value.getParentGLAccount();
            if (parentGLAccount != null && Hibernate.isInitialized(parentGLAccount)) {
                gen.writeObjectField("parentGLAccount", parentGLAccount);
            } else {
                gen.writeNullField("parentGLAccount");
            }
            // Serialize other fields as needed
        } else {
            gen.writeNull();
        }
    }
}
