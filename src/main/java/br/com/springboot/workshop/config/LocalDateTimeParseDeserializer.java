package br.com.springboot.workshop.config;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public final class LocalDateTimeParseDeserializer extends StdDeserializer<LocalDateTime>  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalDateTimeParseDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(p.getValueAsString());
    }

	
}
