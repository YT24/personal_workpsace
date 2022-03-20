package com.example.xxljobregister;

import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;
import java.nio.file.Paths;

@SpringBootTest
class XxlJobRegisterApplicationTests {


    @Test
    public void generateAsciiDocs() throws Exception {
        // 输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .build();
        Swagger2MarkupConverter.from(new URL("http://127.0.0.1:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("src/docs/asciidoc/generated"));
    }

}
