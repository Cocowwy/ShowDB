package cn.cocowwy.showdbcore.strategy.impl.mysql;

import cn.cocowwy.showdbcore.config.ShowDbFactory;
import cn.cocowwy.showdbcore.entities.DsInfo;
import cn.cocowwy.showdbcore.entities.IpCount;
import cn.cocowwy.showdbcore.entities.SlaveStatus;
import cn.cocowwy.showdbcore.entities.TranscationalStatus;
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
public class MySqlMonitorExecuteStrategy extends MySqlExecuteStrategy implements MonitorExecuteStrategy {
    /**
     * 查询每个IP所占用的连接数
     * @param ds 当前数据源
     * @return 数据源IP连接信息
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
     * @param ds 当前数据源
     * @return 主从状态
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
        return CollectionUtils.lastElement(ipCounts);
    }

    /**
     * 数据
     * @param ds 当前数据源
     * @return 当前数据源信息
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
        return CollectionUtils.lastElement(tableInfos);
    }

    /**
     * 查询当前运行事务
     * @param ds 当前数据源
     * @return 当前正在运行的事务详情
     */
    @Override
    public List<TranscationalStatus> transcationalStatus(String ds) {
        String sql = "select * from information_schema.innodb_trx";
        return ShowDbFactory.getJdbcTemplate(ds).query(String.format(sql,
                DataSourcePropUtil.getMysqlSchemaFromDataSourceBeanName(ds)), (rs, i) -> {
            TranscationalStatus status = new TranscationalStatus();
            status.setTrxId(rs.getString("trx_id"));
            status.setTrxState(rs.getString("trx_state"));
            status.setTrxState(rs.getString("trx_started"));
            status.setTrxRequestedLockId(rs.getString("trx_requested_lock_id"));
            status.setTrxWaitStarted(rs.getString("trx_wait_started"));
            status.setTrxWeight(rs.getString("trx_weight"));
            status.setTrxMysqlThreadId(rs.getString("trx_mysql_thread_id"));
            status.setTrxQuery(rs.getString("trx_query"));
            status.setTrxOperationState(rs.getString("trx_operation_state"));
            status.setTrxTablesInUse(rs.getString("trx_tables_in_use"));
            status.setTrxTablesLocked(rs.getString("trx_tables_locked"));
            status.setTrxLockStructs(rs.getString("trx_lock_structs"));
            status.setTrxLockMemoryBytes(rs.getString("trx_lock_memory_bytes"));
            status.setTrxRowsLocked(rs.getString("trx_rows_locked"));
            status.setTrxRowsModified(rs.getString("trx_rows_modified"));
            status.setTrxConcurrencyTickets(rs.getString("trx_concurrency_tickets"));
            status.setTrxIsolationLevel(rs.getString("trx_isolation_level"));
            status.setTrxUniqueChecks(rs.getString("trx_unique_checks"));
            status.setTrxForeignKeyChecks(rs.getString("trx_foreign_key_checks"));
            status.setTrxLastForeignKeyError(rs.getString("trx_last_foreign_key_error"));
            status.setTrxAdaptiveHashLatched(rs.getString("trx_adaptive_hash_latched"));
            status.setTrxAdaptiveHashTimeout(rs.getString("trx_adaptive_hash_timeout"));
            status.setTrxIsReadOnly(rs.getString("trx_is_read_only"));
            status.setTrxAutocommitNonLocking(rs.getString("trx_autocommit_non_locking"));
            return status;
        });
    }
}