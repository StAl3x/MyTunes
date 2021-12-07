package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DBConnector {

    private SQLServerDataSource dataSource;
    public DBConnector()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("MyTunesTeam2");
        dataSource.setUser("CSe21B_32");
        dataSource.setPassword("CSe21B_32");
        dataSource.setPortNumber(1433);

    }
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
}
