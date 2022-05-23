package cn.cocowwy.showdbcore.entities;

/**
 * 事务
 *
 * trx_id：唯一事务id号，只读事务和非锁事务是不会创建id的。
 * TRX_WEIGHT：事务的高度，代表修改的行数（不一定准确）和被事务锁住的行数。为了解决死锁，innodb会选择一个高度最小的事务来当做牺牲品进行回滚。已经被更改的非交易型表的事务权重比其他事务高，即使改变的行和锁住的行比其他事务低。
 * TRX_STATE：事务的执行状态，值一般分为：RUNNING, LOCK WAIT, ROLLING BACK, and COMMITTING.
 * TRX_STARTED：事务的开始时间
 * TRX_REQUESTED_LOCK_ID:如果trx_state是lockwait,显示事务当前等待锁的id，不是则为空。想要获取锁的信息，根据该lock_id，以innodb_locks表中lock_id列匹配条件进行查询，获取相关信息。
 * TRX_WAIT_STARTED：如果trx_state是lockwait,该值代表事务开始等待锁的时间；否则为空。
 * TRX_MYSQL_THREAD_ID：mysql线程id。想要获取该线程的信息，根据该thread_id，以INFORMATION_SCHEMA.PROCESSLIST表的id列为匹配条件进行查询。
 * TRX_QUERY：事务正在执行的sql语句。
 * TRX_OPERATION_STATE：事务当前的操作状态，没有则为空。
 * TRX_TABLES_IN_USE：事务在处理当前sql语句使用innodb引擎表的数量。
 * TRX_TABLES_LOCKED：当前sql语句有行锁的innodb表的数量。（因为只是行锁，不是表锁，表仍然可以被多个事务读和写）
 * TRX_LOCK_STRUCTS：事务保留锁的数量。
 * TRX_LOCK_MEMORY_BYTES：在内存中事务索结构占得空间大小。
 * TRX_ROWS_LOCKED：事务行锁最准确的数量。这个值可能包括对于事务在物理上存在，实际不可见的删除标记的行。
 * TRX_ROWS_MODIFIED：事务修改和插入的行数
 * TRX_CONCURRENCY_TICKETS：该值代表当前事务在被清掉之前可以多少工作，由 innodb_concurrency_tickets系统变量值指定。
 * TRX_ISOLATION_LEVEL：事务隔离等级。
 * TRX_UNIQUE_CHECKS：当前事务唯一性检查启用还是禁用。当批量数据导入时，这个参数是关闭的。
 * TRX_FOREIGN_KEY_CHECKS：当前事务的外键坚持是启用还是禁用。当批量数据导入时，这个参数是关闭的。
 * TRX_LAST_FOREIGN_KEY_ERROR：最新一个外键错误信息，没有则为空。
 * TRX_ADAPTIVE_HASH_LATCHED：自适应哈希索引是否被当前事务阻塞。当自适应哈希索引查找系统分区，一个单独的事务不会阻塞全部的自适应hash索引。自适应hash索引分区通过 innodb_adaptive_hash_index_parts参数控制，默认值为8。
 * TRX_ADAPTIVE_HASH_TIMEOUT：是否为了自适应hash索引立即放弃查询锁，或者通过调用mysql函数保留它。当没有自适应hash索引冲突，该值为0并且语句保持锁直到结束。在冲突过程中，该值被计数为0，每句查询完之后立即释放门闩。当自适应hash索引查询系统被分区（由 innodb_adaptive_hash_index_parts参数控制），值保持为0。
 * TRX_IS_READ_ONLY：值为1表示事务是read only。
 * TRX_AUTOCOMMIT_NON_LOCKING：值为1表示事务是一个select语句，该语句没有使用for update或者shared mode锁，并且执行开启了autocommit，因此事务只包含一个语句。当TRX_AUTOCOMMIT_NON_LOCKING和TRX_IS_READ_ONLY同时为1，innodb通过降低事务开销和改变表数据库来优化事务。
 *
 * @author Cocowwy
 * @create 2022-04-04-20:31
 */
public class TranscationalStatus {
    //    trx_id
//    trx_state
//    trx_started
//    trx_requested_lock_id
//    trx_wait_started
//    trx_weight
//    trx_mysql_thread_id
//    trx_query
//    trx_operation_state
//    trx_tables_in_use
//    trx_tables_locked
//    trx_lock_structs
//    trx_lock_memory_bytes
//    trx_rows_locked
//    trx_rows_modified
//    trx_concurrency_tickets
//    trx_isolation_level
//    trx_unique_checks
//    trx_foreign_key_checks
//    trx_last_foreign_key_error
//    trx_adaptive_hash_latched
//    trx_adaptive_hash_timeout
//    trx_is_read_only
//    trx_autocommit_non_locking
    private String trxId;
    private String trxState;
    private String trxStarted;
    private String trxRequestedLockId;
    private String trxWaitStarted;
    private String trxWeight;
    private String trxMysqlThreadId;
    private String trxQuery;
    private String trxOperationState;
    private String trxTablesInUse;
    private String trxTablesLocked;
    private String trxLockStructs;
    private String trxLockMemoryBytes;
    private String trxRowsLocked;
    private String trxRowsModified;
    private String trxConcurrencyTickets;
    private String trxIsolationLevel;
    private String trxUniqueChecks;
    private String trxForeignKeyChecks;
    private String trxLastForeignKeyError;
    private String trxAdaptiveHashLatched;
    private String trxAdaptiveHashTimeout;
    private String trxIsReadOnly;
    private String trxAutocommitNonLocking;

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getTrxState() {
        return trxState;
    }

    public void setTrxState(String trxState) {
        this.trxState = trxState;
    }

    public String getTrxStarted() {
        return trxStarted;
    }

    public void setTrxStarted(String trxStarted) {
        this.trxStarted = trxStarted;
    }

    public String getTrxRequestedLockId() {
        return trxRequestedLockId;
    }

    public void setTrxRequestedLockId(String trxRequestedLockId) {
        this.trxRequestedLockId = trxRequestedLockId;
    }

    public String getTrxWaitStarted() {
        return trxWaitStarted;
    }

    public void setTrxWaitStarted(String trxWaitStarted) {
        this.trxWaitStarted = trxWaitStarted;
    }

    public String getTrxWeight() {
        return trxWeight;
    }

    public void setTrxWeight(String trxWeight) {
        this.trxWeight = trxWeight;
    }

    public String getTrxMysqlThreadId() {
        return trxMysqlThreadId;
    }

    public void setTrxMysqlThreadId(String trxMysqlThreadId) {
        this.trxMysqlThreadId = trxMysqlThreadId;
    }

    public String getTrxQuery() {
        return trxQuery;
    }

    public void setTrxQuery(String trxQuery) {
        this.trxQuery = trxQuery;
    }

    public String getTrxOperationState() {
        return trxOperationState;
    }

    public void setTrxOperationState(String trxOperationState) {
        this.trxOperationState = trxOperationState;
    }

    public String getTrxTablesInUse() {
        return trxTablesInUse;
    }

    public void setTrxTablesInUse(String trxTablesInUse) {
        this.trxTablesInUse = trxTablesInUse;
    }

    public String getTrxTablesLocked() {
        return trxTablesLocked;
    }

    public void setTrxTablesLocked(String trxTablesLocked) {
        this.trxTablesLocked = trxTablesLocked;
    }

    public String getTrxLockStructs() {
        return trxLockStructs;
    }

    public void setTrxLockStructs(String trxLockStructs) {
        this.trxLockStructs = trxLockStructs;
    }

    public String getTrxLockMemoryBytes() {
        return trxLockMemoryBytes;
    }

    public void setTrxLockMemoryBytes(String trxLockMemoryBytes) {
        this.trxLockMemoryBytes = trxLockMemoryBytes;
    }

    public String getTrxRowsLocked() {
        return trxRowsLocked;
    }

    public void setTrxRowsLocked(String trxRowsLocked) {
        this.trxRowsLocked = trxRowsLocked;
    }

    public String getTrxRowsModified() {
        return trxRowsModified;
    }

    public void setTrxRowsModified(String trxRowsModified) {
        this.trxRowsModified = trxRowsModified;
    }

    public String getTrxConcurrencyTickets() {
        return trxConcurrencyTickets;
    }

    public void setTrxConcurrencyTickets(String trxConcurrencyTickets) {
        this.trxConcurrencyTickets = trxConcurrencyTickets;
    }

    public String getTrxIsolationLevel() {
        return trxIsolationLevel;
    }

    public void setTrxIsolationLevel(String trxIsolationLevel) {
        this.trxIsolationLevel = trxIsolationLevel;
    }

    public String getTrxUniqueChecks() {
        return trxUniqueChecks;
    }

    public void setTrxUniqueChecks(String trxUniqueChecks) {
        this.trxUniqueChecks = trxUniqueChecks;
    }

    public String getTrxForeignKeyChecks() {
        return trxForeignKeyChecks;
    }

    public void setTrxForeignKeyChecks(String trxForeignKeyChecks) {
        this.trxForeignKeyChecks = trxForeignKeyChecks;
    }

    public String getTrxLastForeignKeyError() {
        return trxLastForeignKeyError;
    }

    public void setTrxLastForeignKeyError(String trxLastForeignKeyError) {
        this.trxLastForeignKeyError = trxLastForeignKeyError;
    }

    public String getTrxAdaptiveHashLatched() {
        return trxAdaptiveHashLatched;
    }

    public void setTrxAdaptiveHashLatched(String trxAdaptiveHashLatched) {
        this.trxAdaptiveHashLatched = trxAdaptiveHashLatched;
    }

    public String getTrxAdaptiveHashTimeout() {
        return trxAdaptiveHashTimeout;
    }

    public void setTrxAdaptiveHashTimeout(String trxAdaptiveHashTimeout) {
        this.trxAdaptiveHashTimeout = trxAdaptiveHashTimeout;
    }

    public String getTrxIsReadOnly() {
        return trxIsReadOnly;
    }

    public void setTrxIsReadOnly(String trxIsReadOnly) {
        this.trxIsReadOnly = trxIsReadOnly;
    }

    public String getTrxAutocommitNonLocking() {
        return trxAutocommitNonLocking;
    }

    public void setTrxAutocommitNonLocking(String trxAutocommitNonLocking) {
        this.trxAutocommitNonLocking = trxAutocommitNonLocking;
    }
}
