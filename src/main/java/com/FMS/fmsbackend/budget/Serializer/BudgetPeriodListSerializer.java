package com.FMS.fmsbackend.budget.Serializer;

import org.hibernate.Hibernate;

import com.FMS.fmsbackend.budget.entity.BudgetPeriod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;



import java.io.IOException;
import java.util.List;

//import io.jsonwebtoken.io.IOException;

public class BudgetPeriodListSerializer extends JsonSerializer<List<BudgetPeriod>> {
    @Override
    public void serialize(List<BudgetPeriod> subBudgetPeriods, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (BudgetPeriod subBudgetPeriod : subBudgetPeriods) {
            // Customize the serialization of each subBudgetPeriod as needed
            jsonGenerator.writeObject(subBudgetPeriod);
        }
        jsonGenerator.writeEndArray();
    }
}

