package io.github.aikusonitradesystem.authserver.session.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * MessageKeyTest checks if all message keys referenced in the code exist in the message properties files.
 */
@Slf4j
public class MessageKeyTest {

    private static final String BASE_MESSAGES_PATH = "src/main/resources/messages/messages";
    private static final String CODE_PATH = "src/main/java";

    private static final Pattern MESSAGE_KEY_PATTERN = Pattern.compile("\\W*m\\(\"([^\"]+)\"");

    @Test
    public void testMessageKeysExistence() throws IOException {
        Set<String> referencedKeys = findReferencedMessageKeys();
        Set<String> existingKeys = loadMessageKeysFromProperties();

        log.info("Referenced keys: {}", referencedKeys);
        log.info("Number of referenced keys: {}", referencedKeys.size());
        log.info("Existing keys: {}", existingKeys);
        log.info("Number of existing keys: {}", existingKeys.size());

        referencedKeys.removeAll(existingKeys);

        assertTrue(referencedKeys.isEmpty(), "Missing message keys: " + referencedKeys);
    }

    private static Set<String> loadMessageKeysFromProperties() throws IOException {
        Set<String> keys = new HashSet<>();

        Pattern pattern = Pattern.compile(".+messages.+.properties");

        try (Stream<String> paths = Files.walk(Paths.get("src/main/resources/messages"))
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .filter(fileName -> pattern.matcher(fileName).matches())) {

            paths.forEach(fileName -> {
                Properties properties = new Properties();
                try {
                    properties.load(Files.newInputStream(Paths.get("src/main/resources/messages/" + fileName)));
                    keys.addAll(properties.stringPropertyNames());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return keys;
    }

    private Set<String> findReferencedMessageKeys() throws IOException {
        Set<String> keys = new HashSet<>();

        Files.walk(new File(CODE_PATH).toPath())
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(path -> {
                    try {
                        List<String> lines = Files.readAllLines(path);

                        for (String line : lines) {
                            Matcher matcher = MESSAGE_KEY_PATTERN.matcher(line);
                            while (matcher.find()) {
                                keys.add(matcher.group(1));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return keys;
    }
}

