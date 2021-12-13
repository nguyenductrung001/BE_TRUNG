package com.itsol.recruit_managerment.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class SqlReader {

    public static final String ADMIN_DASHBOARD_MODULE = "admin/admin_dashboard";
    public static final String USER_HOME_MODULE = "user/home";


    public static String getSqlQueryById(String module, String queryId) {
        try {
            File folder = new ClassPathResource("sql" + File.separator + module + File.separator + queryId + ".sql").getFile();
            return folder.isFile() ? new String(Files.readAllBytes(Paths.get(folder.getAbsolutePath()))) + "\n" : null;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
