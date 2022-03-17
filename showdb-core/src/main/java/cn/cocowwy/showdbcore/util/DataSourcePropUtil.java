package cn.cocowwy.showdbcore.util;

import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.exception.ShowDbException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author cocowwy.cn
 * @create 2022-03-03-10:03
 */
public class DataSourcePropUtil {
    public static DBEnum dataSourceType(DataSource dataSource) throws SQLException {
        DBEnum type = null;
        String name = dataSource.getConnection().getMetaData().getDatabaseProductName();
        switch (name) {
            case "MySQL":
                type = DBEnum.MySQL;
                break;
            default:
                throw new ShowDbException("The data source is not supported");
        }
        return type;
    }

    public static String getBeanName(DataSource dataSource) throws SQLException {
        for (Map.Entry<String, DataSource> entry : GlobalContext.getDataSourcesMap().entrySet()) {
            if (entry.getValue().equals(dataSource)) {
                return entry.getKey();
            }
        }
        throw new ShowDbException("The current data source does not exist");
    }
}
