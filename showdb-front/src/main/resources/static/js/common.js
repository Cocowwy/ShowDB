function alertSuccess(ref, msg) {
    ref.$notify({
        title: 'SUCCESS 🍓',
        message: msg,
        position: 'top-right',
        type: 'success'
    });
}

function alertError(ref, msg) {
    ref.$notify.error({
        title: 'ERROR 🎃',
        message: msg,
        duration: 0,
        position: 'top-right',
        type: 'success'
    });
}