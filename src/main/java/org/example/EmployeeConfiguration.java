package org.example;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class EmployeeConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
    public DataSourceFactory getDatabase() {
        return database;
    }
}
