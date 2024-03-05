package com.spcu.dims.spcu_dims.config;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.services.ArchdioceseServices;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class SqlFileRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final ArchdioceseServices archdioceseServices;

    @Override
    public void run(String... args) throws Exception {

        Archdiocese archdiocese = archdioceseServices.findById(1);
        if (archdiocese == null) {
            Resource resource1 = new ClassPathResource("view.sql");
            InputStreamReader reader1 = new InputStreamReader(resource1.getInputStream(), StandardCharsets.UTF_8);
            String sqlContent = FileCopyUtils.copyToString(reader1);

            // Split SQL statements and execute them
            String[] sqlStatements = sqlContent.split(";");
            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    jdbcTemplate.execute(sql.trim());
                }
            }
        }


        // Load SQL file from resources
        Resource resource2 = new ClassPathResource("init.sql");
        InputStreamReader reader2 = new InputStreamReader(resource2.getInputStream(), StandardCharsets.UTF_8);
        String sqlContent = FileCopyUtils.copyToString(reader2);

        // Split SQL statements and execute them
        String[] sqlStatements = sqlContent.split(";");
        for (String sql : sqlStatements) {
            if (!sql.trim().isEmpty()) {
                jdbcTemplate.execute(sql.trim());
            }
        }
    }
}
