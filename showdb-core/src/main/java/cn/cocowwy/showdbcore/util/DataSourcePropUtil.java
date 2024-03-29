package cn.cocowwy.showdbcore.util;

import cn.cocowwy.showdbcore.config.ShowDbCache;
import cn.cocowwy.showdbcore.config.ShowDBContext;
import cn.cocowwy.showdbcore.constants.DBEnum;
import cn.cocowwy.showdbcore.exception.ShowDbException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author cocowwy.cn
 */
public class DataSourcePropUtil {

    /**
     * 根据当前数据源的bean名称，获取到数据源枚举
     * @param dataSourceBeanName 数据源bean名称
     * @return DBEnum
     */
    public static DBEnum dataSourceTypeByBeanName(String dataSourceBeanName) {
        return (DBEnum) ShowDbCache.cache().computeIfAbsent(ShowDbCache.buildCacheKey(dataSourceBeanName, "dataSourceTypeByBeanName", dataSourceBeanName),
                (k) -> {
                    DBEnum type = null;
                    Connection connection = null;
                    try {
                        connection = ShowDBContext.getDataSourcesMap().get(dataSourceBeanName).getConnection();
                        String name = connection.getMetaData().getDatabaseProductName();
                        switch (name) {
                            case "MySQL":
                                type = DBEnum.MySQL;
                                break;
                            default:
                                throw new ShowDbException("The data source is not supported");
                        }
                    } catch (NullPointerException | SQLException np) {
                        throw new ShowDbException(String.format("The connection to the database [beanName=%s] is abnormal," +
                                " please verify the configuration is correct", dataSourceBeanName));
                    } finally {
                        try {
                            // fix：修复不释放连接的bug
                            connection.close();
                        } catch (SQLException ignored) {
                        }
                    }
                    return type;
                });
    }

    /**
     * 根据数据源，获取当前数据源的Bean名称
     * @param dataSource
     * @return
     */
    public static String getBeanName(DataSource dataSource) {
        for (Map.Entry<String, DataSource> entry : ShowDBContext.getDataSourcesMap().entrySet()) {
            if (entry.getValue().equals(dataSource)) {
                return entry.getKey();
            }
        }
        throw new ShowDbException("The current data source does not exist");
    }

    /**
     * 获取MySQL环境的数据源的schema(通过字符串截取URL)，走缓存
     * @return schema
     */
    public static String getMysqlSchemaFromDataSourceBeanName(String beanName) {
        return (String) ShowDbCache.cache().computeIfAbsent(
                ShowDbCache.buildCacheKey(beanName, "dataSourceSchema", beanName),
                (key) -> {
                    String mysqlSchema = null;
                    Connection connection = null;
                    try {
                        connection = ShowDBContext.getDataSourcesMap()
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
