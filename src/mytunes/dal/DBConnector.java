package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import mytunes.dal.Exceptions.DataException;

import java.sql.Connection;

public class DBConnector {

    private SQLServerDataSource dataSource;
    public DBConnector()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("MyTunes_team2");
        dataSource.setUser("CSe21B_28");
        dataSource.setPassword("CSe21B_28");
        dataSource.setPortNumber(1433);

    }
    public Connection getConnection() throws DataException
    {
        try {
            return dataSource.getConnection();
        } catch (SQLServerException exception) {
            throw new DataException("Cant connect to DB", exception);
        }
    }
}
