package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.SlaveStatus;
import cn.cocowwy.showdbcore.entities.TableInfo;
import cn.cocowwy.showdbcore.strategy.MonitorExecuteStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-03-03-20:40
 */
@Component
public class MySqlMonitorExecuteStrategy implements MonitorExecuteStrategy, MySqlExecuteStrategy {
    /**
     * 查询每个IP所占用的连接数
     * @return
     */
    @Override
    public List<IpCount> ipConnectCount(String ds) {
        String sql = "select SUBSTRING_INDEX(host, ':', 1) as ip, count(*) as count\n" +
                "from information_schema.processlist as a\n" +
                "group by ip\n" +
                "order by count desc;\n";
        List<IpCount> ipCounts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> {
            IpCount ic = new IpCount();
            ic.setIp(rs.getString("ip"));
            ic.setCount(rs.getLong("count"));
            return ic;
        });
        return ipCounts;
    }

    /**
     * 主从信息查询
     *  - MySQL
     * @return
     */
    @Override
    public SlaveStatus slaveStatus(String ds) {
        String sql = "show slave status";
        return null;
    }

    /**
     * 监控表大小
     * @return
     */
    @Override
    public List<TableInfo> tableMonitor(String ds, String tableName) {
        String sql = "select\n" +
                "table_schema as 'ds',\n" +
                "table_name as 'tableName',\n" +
                "table_rows as 'records',\n" +
                "truncate(data_length/1024/1024, 2) as 'data_size_MB',\n" +
                "truncate(index_length/1024/1024, 2) as 'index_size_MB'\n" +
                "from information_schema.tables\n" +
                "where table_schema='%s'\n" +
                "order by data_length desc, index_length desc;";
        List<TableInfo> tableInfos = ShowDbFactory.getJdbcTemplate(ds).query(String.format(sql, ds),
                (rs, i) -> {
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setTableName(rs.getString("tableName"));
                    tableInfo.setDataSize(rs.getLong("data_size_MB"));
                    tableInfo.setIndexSize(rs.getLong("index_size_MB"));
                    tableInfo.setRecords(rs.getLong("records"));
                    return tableInfo;
                });
        return tableInfos;
    }

    /**
     * 数据
     * @return
     */
    @Override
    public DsInfo dsInfo(String ds) {
        String sql = "select\n" +
                "table_schema as 'ds',\n" +
                "sum(table_rows) as 'records',\n" +
                "sum(truncate(data_length/1024/1024, 2)) as 'data_size_MB',\n" +
                "sum(truncate(index_length/1024/1024, 2)) as 'index_size_MB'\n" +
                "from information_schema.tables\n" +
                "where table_schema='%s'";
        List<DsInfo> tableInfos = ShowDbFactory.getJdbcTemplate(ds).query(String.format(sql,
                ds), (rs, i) -> {
            DsInfo dsInfo = new DsInfo();
            dsInfo.setDataSize(rs.getString("data_size_MB"));
            dsInfo.setIndexSize(rs.getString("index_size_MB"));
            dsInfo.setRecords(rs.getLong("records"));
            return dsInfo;
        });
        return CollectionUtils.firstElement(tableInfos);
    }
}
