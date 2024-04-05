package com.spcu.dims.spcu_dims.config;

import com.spcu.dims.spcu_dims.entities.Role;
import com.spcu.dims.spcu_dims.entities.User;
import com.spcu.dims.spcu_dims.services.RoleServices;
import com.spcu.dims.spcu_dims.services.UserServices;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
public class SqlFileRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final UserServices userServices;
    private final RoleServices roleServices;


    @Override
    public void run(String... args) throws Exception {

        executeSqlFile("view.sql");
        executeSqlFile("init.sql");



        Optional<Role> role = roleServices.findByName("DefaultAdmin");
        if(role.isPresent()) {
            List<User> users = userServices.findByRole(role.get());
            if (users.isEmpty()) {

                userServices.createUser("Admin", "DefaultAdmin", "spcudims");
            }
        }





    }
    private void executeSqlFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        String sqlContent = FileCopyUtils.copyToString(reader);

        // Split SQL statements and execute them
        String[] sqlStatements = sqlContent.split(";");
        for (String sql : sqlStatements) {
            if (!sql.trim().isEmpty()) {
                jdbcTemplate.execute(sql.trim());
            }
        }

    }
}
