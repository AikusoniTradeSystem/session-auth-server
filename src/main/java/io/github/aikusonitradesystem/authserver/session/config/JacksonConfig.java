package io.github.aikusonitradesystem.authserver.session.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import io.github.aikusonitradesystem.authserver.session.annotation.HideSensitiveData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class JacksonConfig {

    @Bean
    public com.fasterxml.jackson.databind.ObjectMapper objectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new CustomBeanSerializerModifier());
        mapper.registerModule(module);
        return mapper;
    }

    public static class CustomBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            return beanProperties.stream()
                    .map(this::filterSensitive)
                    .collect(Collectors.toList());
        }

        private BeanPropertyWriter filterSensitive(BeanPropertyWriter writer) {
            if (writer.getAnnotation(HideSensitiveData.class) != null) {
                return new MaskedPropertyWriter(writer);
            }
            return writer;
        }
    }

    public static class MaskedPropertyWriter extends BeanPropertyWriter {
        public MaskedPropertyWriter(BeanPropertyWriter writer) {
            super(writer);
        }

        @Override
        public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            if (_nullSerializer != null) {
                gen.writeFieldName(_name);
                _nullSerializer.serialize(null, gen, prov);
            } else if (bean != null) {
                gen.writeFieldName(_name);
                gen.writeString("*****");
            }
        }
    }
}
