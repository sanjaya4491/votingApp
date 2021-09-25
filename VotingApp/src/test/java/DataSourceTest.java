import Model.DataSource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataSourceTest {
    @BeforeAll
    public static void disableLogger(){
        BasicConfigurator.configure();
        Logger.getLogger("com.zaxxer.hikari").setLevel(Level.ERROR);
    }

    @Test
    public void getConnectionShouldNotThrowException(){
        assertDoesNotThrow(DataSource::getConnection);
    }
}
