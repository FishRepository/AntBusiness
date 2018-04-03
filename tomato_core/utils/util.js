const formatTime = date =
>
{
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    const hour = date.getHours()
    const minute = date.getMinutes()
    const second = date.getSeconds()

    return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n =
>
{
    n = n.toString()
    return n[1] ? n : '0' + n
}

/**
 * 时间戳转化为年 月 日 时 分 秒
 * timestamp: 传入时间戳
 * format：返回格式，支持自定义，但参数必须与formateArr里保持一致
 */
//时间戳转换时间
function formatTimeStamp(timestamp, format) {
    var formateArr = ['Y', 'M', 'D', 'h', 'm', 's'];
    var returnArr = [];
    var date = new Date(timestamp);
    returnArr.push(date.getFullYear());
    returnArr.push(formatNumber(date.getMonth() + 1));
    returnArr.push(formatNumber(date.getDate()));
    returnArr.push(formatNumber(date.getHours()));
    returnArr.push(formatNumber(date.getMinutes()));
    returnArr.push(formatNumber(date.getSeconds()));
    for (var i in returnArr) {
        format = format.replace(formateArr[i], returnArr[i]);
    }
    return format;
}

function isPoneAvailable(phoneStr) {
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(phoneStr)) {
        return false;
    } else {
        return true;
    }
}

function isValidCode(codeStr) {
    var myreg = /^\d{6,}$/;
    if (!myreg.test(codeStr)) {
        return false;
    } else {
        return true;
    }
}

function showErrorModal(title, content) {
    wx.showModal({
        title: title || '加载失败',
        content: content || '未知错误',
        showCancel: false
    });
}

function showLoadToast(title, duration) {
    wx.showToast({
        title: title || '加载中',
        icon: 'loading',
        mask: true,
        duration: duration || 10000
    });
}

function showToast(title, duration) {
    wx.showToast({
        title: title || '加载中',
        mask: true,
        icon: 'success',
        duration: duration || 1000
    });
}

module.exports = {
    formatTime: formatTime,
    formatTimeStamp: formatTimeStamp,
    isPoneAvailable: isPoneAvailable,
    isValidCode: isValidCode,
    showErrorModal: showErrorModal,
    showLoadToast: showLoadToast,
    showToast: showToast
}


