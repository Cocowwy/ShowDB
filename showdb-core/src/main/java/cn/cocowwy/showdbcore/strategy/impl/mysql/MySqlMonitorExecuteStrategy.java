package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.SlaveStatus;
import cn.cocowwy.showdbcore.strategy.MonitorExecuteStrategy;
import cn.cocowwy.showdbcore.util.DataSourcePropUtil;
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
        List<SlaveStatus> ipCounts = ShowDbFactory.getJdbcTemplate(ds).query(sql, (rs, i) -> {
            SlaveStatus ss = new SlaveStatus();
            ss.setSlaveIOState(rs.getString("Slave_IO_State"));
            ss.setMasterHost(rs.getString("Master_Host"));
            ss.setMasterUser(rs.getString("Master_User"));
            ss.setMasterPort(rs.getString("Master_Port"));
            ss.setMasterRetryCount(rs.getString("Master_Retry_Count"));
            ss.setSlaveIORunning(rs.getString("Slave_IO_Running"));
            ss.setSlaveSQLRunning(rs.getString("Slave_SQL_Running"));
            ss.setSqlDelay(rs.getString("SQL_Delay"));
            ss.setRelayLogFile(rs.getString("Relay_Log_File"));
            ss.setMasterLogFile(rs.getString("Master_Log_File"));
            return ss;
        });

        // todo mock test data
        if (ds.equals("oms")) {
            SlaveStatus mock = new SlaveStatus();
            mock.setSlaveIOState("Waiting for master to send event");
            mock.setMasterHost("localhost");
            mock.setMasterUser("photorepl");
            mock.setMasterPort("3301");
            mock.setMasterRetryCount("86400");
            mock.setSlaveIORunning("Yes");
            mock.setSlaveSQLRunning("Yes");
            mock.setSqlDelay("0");
            mock.setRelayLogFile("mysqld-relay-bin.005201");
            mock.setMasterLogFile("mysql-bin.001822");
            return mock;
        }
        // ------------mock---------------

        return CollectionUtils.lastElement(ipCounts);
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
                DataSourcePropUtil.getMysqlSchemaFromDataSourceBeanName(ds)), (rs, i) -> {
            DsInfo dsInfo = new DsInfo();
            dsInfo.setDataSize(rs.getString("data_size_MB"));
            dsInfo.setIndexSize(rs.getString("index_size_MB"));
            dsInfo.setRecords(rs.getLong("records"));
            return dsInfo;
        });
        return CollectionUtils.firstElement(tableInfos);
    }
}
