package cn.cocowwy.showdbcore.util;

import cn.cocowwy.showdbcore.cache.ShowDbCache;
import cn.cocowwy.showdbcore.config.GlobalContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.exception.ShowDbException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author cocowwy.cn
 * @create 2022-03-03-10:03
 */
public class DataSourcePropUtil {
    /**
     * 根据当前数据源的bean名称，获取到数据源枚举
     * @param dataSourceBeanName
     * @return
     */
    public static DBEnum dataSourceTypeByBeanName(String dataSourceBeanName) {
        return (DBEnum) ShowDbCache.cache().computeIfAbsent(ShowDbCache.buildCacheKey(dataSourceBeanName, "dataSourceTypeByBeanName", dataSourceBeanName),
                (k) -> {
                    DBEnum type = null;
                    Connection connection = null;
                    try {
                        connection = GlobalContext.getDataSourcesMap().get(dataSourceBeanName).getConnection();
                        String name = connection.getMetaData().getDatabaseProductName();
                        switch (name) {
                            case "MySQL":
                                type = DBEnum.MySQL;
                                break;
                            default:
                                throw new ShowDbException("The data source is not supported");
                        }
                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    } finally {
                        try {
                            // fix：修复不释放连接的bug
                            connection.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    return type;
                });
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
     * 获取MySQL环境的数据源的schema，通过字符串截取，走缓存
     * @return
     */
    public static String getMysqlSchemaFromDataSourceBeanName(String beanName) {
        return (String) ShowDbCache.cache().computeIfAbsent(
                ShowDbCache.buildCacheKey(beanName, "dataSourceSchema", beanName),
                (key) -> {
                    String mysqlSchema = null;
                    Connection connection = null;
                    try {
                        connection = GlobalContext.getDataSourcesMap()
                                .get(beanName)
                                .getConnection();
                        mysqlSchema = connection.getMetaData().getURL();
                    } catch (SQLException throwables) {
                        return "";
                    } finally {
                        try {
                            connection.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
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
