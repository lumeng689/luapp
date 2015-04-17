package org.luapp.cms.utils;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.CoderResult;

/**
 * Created by lumeng on 15-4-16.
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, "FINAL", "org.luapp", "cms"));
        JsonSerializer s = new JsonHtmlXssSerializer();
        module.addSerializer(s);
//        module.addKeySerializer(String.class, new JsonHtmlXssSerializer());
//
        module.addSerializer(s);

//        this.getJsonFactory().setCharacterEscapes(new HTMLCharacterEscapes());
        this.registerModule(module);
    }

    private static class HTMLCharacterEscapes extends CharacterEscapes {

        private final int[] asciiEscapes;

        public HTMLCharacterEscapes()
        {
            // start with set of characters known to require escaping (double-quote, backslash etc)
            int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
            // and force escaping of a few others:
            esc['<'] = CharacterEscapes.ESCAPE_STANDARD;
            esc['>'] = CharacterEscapes.ESCAPE_STANDARD;
            esc['&'] = CharacterEscapes.ESCAPE_STANDARD;
            esc['\''] = CharacterEscapes.ESCAPE_STANDARD;
            asciiEscapes = esc;
        }
        // this method gets called for character codes 0 - 127
        @Override public int[] getEscapeCodesForAscii() {
            return asciiEscapes;
        }
        // and this for others; we don't need anything special here
        @Override public SerializableString getEscapeSequence(int ch) {
            // no further escaping (beyond ASCII chars) needed:
            return null;
        }
    }

    private static class JsonHtmlXssSerializer extends JsonSerializer<String> {
        private static final Logger logger = LoggerFactory.getLogger(JsonHtmlXssSerializer.class);

//        @Override
//        public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
//            if (value != null) {
//                String encodedValue = encodeHtml(value);
//                jgen.writeString(encodedValue);
//            }
//        }

        private String encodeHtml(String value) {
            return Encode.forHtml(value);
        }

        @Override
        public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            logger.info("#########**********");
            if (value != null) {
                String encodedValue = encodeHtml(value);
                jgen.writeString(encodedValue);
            }
        }
    }
}
