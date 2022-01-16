package dev.thesummit.flink.database;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import dev.thesummit.flink.models.Link;
import dev.thesummit.flink.models.Preference;
import dev.thesummit.flink.models.User;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseModule extends AbstractModule {

  static final Map<String, String> env = System.getenv();
  static final String DB_URL =
      env.getOrDefault("POSTGRES_CONNECTION_PATH", "jdbc:postgresql://localhost:5432/flink");
  static final String DB_USER = env.getOrDefault("POSTGRES_USER", "flink_system");
  static final String DB_PASSWORD = env.getOrDefault("POSTGRES_PASSWORD", "flinksystem");
  static FlinkConnectionPool pool;
  static HashMap<Class<?>, String> tableMapping;
  static DatabaseService service;

  public DatabaseModule() {
    try {
      DatabaseModule.pool =
          FlinkConnectionPool.create(
              DatabaseModule.DB_URL, DatabaseModule.DB_USER, DatabaseModule.DB_PASSWORD);
    } catch (SQLException e) {
      throw new RuntimeException("Unable to connected to database", e);
    }

    DatabaseModule.tableMapping = new HashMap<Class<?>, String>();
    DatabaseModule.tableMapping.put(Link.class, "LINKS");
    DatabaseModule.tableMapping.put(User.class, "USERS");
    DatabaseModule.tableMapping.put(Preference.class, "PREFERENCES");

    DatabaseModule.service =
        new FlinkDatabaseService(DatabaseModule.pool, DatabaseModule.tableMapping);
  }

  @Provides()
  static ConnectionPool provideConnectionPool() {
    return DatabaseModule.pool;
  }

  @Provides()
  static HashMap<Class<?>, String> provideTableMapping() {
    return DatabaseModule.tableMapping;
  }

  @Provides()
  static DatabaseService provideDatabaseService() {
    return DatabaseModule.service;
  }
}
