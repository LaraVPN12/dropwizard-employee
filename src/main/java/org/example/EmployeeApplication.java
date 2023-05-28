package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import org.example.dao.EmployeeDAO;
import org.example.domain.Employee;
import org.example.resources.EmployeeResource;

public class EmployeeApplication extends Application<EmployeeConfiguration> {

    private final HibernateBundle<EmployeeConfiguration> hibernateBundle =
            new HibernateBundle<EmployeeConfiguration>(Employee.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(EmployeeConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };

    public static void main(final String[] args) throws Exception {
        new EmployeeApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<EmployeeConfiguration> bootstrap) {
       bootstrap.addBundle(new MigrationsBundle<EmployeeConfiguration>() {

           @Override
           public DataSourceFactory getDataSourceFactory(EmployeeConfiguration employeeConfiguration) {
               return employeeConfiguration.getDatabase();
           }
       });
    }

    @Override
    public void run(final EmployeeConfiguration configuration,
                    final Environment environment) {
        final EmployeeDAO employeeDAO = new EmployeeDAO(hibernateBundle.getSessionFactory());

        final EmployeeResource employeeResource = new EmployeeResource(employeeDAO);
        environment.jersey().register(employeeResource);
    }

}
