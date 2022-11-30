function alertSqlExecuteSuccess(ref) {
    ref.$notify({
        title: 'SUCCESS🍓',
        message: 'SQL执行成功',
        type: 'success'
    });
}

function alertSqlExecuteError(ref, msg) {
    ref.$notify.error({
        title: 'ERROR🍓',
        message: msg,
        type: 'success'
    });
}