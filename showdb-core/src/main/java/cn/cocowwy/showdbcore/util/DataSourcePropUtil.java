package cn.cocowwy.showdbcore.util;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.config.ShowDbFactory;
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

    /**
     * 获取MySQL环境的数据源的库名，通过字符串截取，走缓存
     * @return
     */
    public static String getMysqlSchemaFromDataSource() {
        return (String) ShowDbCache.cache().computeIfAbsent(
                ShowDbCache.buildCacheKey(DBEnum.MySQL.getName(), "dataSourceSchema", GlobalContext.getCurrentDataSourceBeanName()),
                (key) -> {
                    String mysqlSchema = null;
                    try {
                        mysqlSchema = ShowDbFactory.getDataSource().getConnection().getMetaData().getURL();
                    } catch (SQLException throwables) {
                        return "";
                    }
                    int begin = 0;
                    int end = 0;
                    int prefixSlashCount = 2;
                    for (int i = 0; i < mysqlSchema.length(); i++) {
                        if (mysqlSchema.charAt(i) == '/' && prefixSlashCount > 0) {
                            prefixSlashCount--;
                        } else if (mysqlSchema.charAt(i) == '/' && prefixSlashCount == 0) {
                            begin = i;
                            prefixSlashCount--;
                        } else if (mysqlSchema.charAt(i) == '?') {
                            end = i;
                            break;
                        } else if (i == mysqlSchema.length() - 1) {
                            end = i + 1;
                        }
                    }
                    return mysqlSchema.substring(begin + 1, end);
                }
        );

    }
}
