package org.mockserver.serialization.serializers.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.mockserver.serialization.model.*;

import java.io.IOException;

/**
 * @author jamesdbloom
 */
public class HttpResponseDTOSerializer extends StdSerializer<HttpResponseDTO> {

    public HttpResponseDTOSerializer() {
        super(HttpResponseDTO.class);
    }

    @Override
    public void serialize(HttpResponseDTO httpResponseDTO, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (httpResponseDTO.getStatusCode() != null) {
            jgen.writeObjectField("statusCode", httpResponseDTO.getStatusCode());
        }
        if (httpResponseDTO.getReasonPhrase() != null) {
            jgen.writeObjectField("reasonPhrase", httpResponseDTO.getReasonPhrase());
        }
        if (httpResponseDTO.getHeaders() != null && !httpResponseDTO.getHeaders().isEmpty()) {
            jgen.writeObjectField("headers", httpResponseDTO.getHeaders());
        }
        if (httpResponseDTO.getCookies() != null && !httpResponseDTO.getCookies().isEmpty()) {
            jgen.writeObjectField("cookies", httpResponseDTO.getCookies());
        }
        BodyWithContentTypeDTO body = httpResponseDTO.getBody();
        if (body != null) {
            if (body instanceof StringBodyDTO && !((StringBodyDTO) body).getString().isEmpty()) {
                jgen.writeObjectField("body", body);
            } else if (body instanceof JsonBodyDTO && !((JsonBodyDTO) body).getJson().isEmpty()) {
                jgen.writeObjectField("body", body);
            } else if (body instanceof XmlBodyDTO && !((XmlBodyDTO) body).getXml().isEmpty()) {
                jgen.writeObjectField("body", body);
            } else if (body instanceof BinaryBodyDTO) {
                jgen.writeObjectField("body", body);
            } else if (body instanceof LogEventBodyDTO) {
                jgen.writeObjectField("body", body);
            }
        }
        if (httpResponseDTO.getDelay() != null) {
            jgen.writeObjectField("delay", httpResponseDTO.getDelay());
        }
        if (httpResponseDTO.getConnectionOptions() != null) {
            jgen.writeObjectField("connectionOptions", httpResponseDTO.getConnectionOptions());
        }
        jgen.writeEndObject();
    }
}
