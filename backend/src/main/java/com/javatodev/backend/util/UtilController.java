package com.javatodev.backend.util;

import com.javatodev.backend.util.filldb.DatabaseFillService;
import com.javatodev.backend.util.migratedb.MigrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/utils")
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UtilController {
    private final DatabaseFillService databaseFillService;
    private final MigrationService migrationService;

    // fills the Database with the example data
    @GetMapping(path = "/generate")
    public @ResponseBody
    ResponseEntity<Void> fillDatabase() throws NoSuchAlgorithmException {
        //call the service layer
        databaseFillService.fillDatabase();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //migrate postgresql to mongodb
    @GetMapping(value = "/migrate")
    public void migrate(){
        //call the service layer
        //delete if exists database
        migrationService.databaseDrop();
        //migrate data from postgresql to mongodb
        migrationService.migrateData();
    }

}
