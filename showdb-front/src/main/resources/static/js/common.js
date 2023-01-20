function alertSuccess(ref, msg) {
    ref.$notify({
        title: 'SUCCESS 🍓',
        message: msg,
        position: 'top-left',
        duration: 2000,
        type: 'success'
    });
}

function alertError(ref, msg) {
    ref.$notify.error({
        title: 'ERROR 🎃',
        message: msg,
        duration: 3000,
        position: 'top-left',
        type: 'success'
    });
}