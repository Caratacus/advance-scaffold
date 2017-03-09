function jsonTimeStamp(milliseconds) {
    if (milliseconds != "" && milliseconds != null
        && milliseconds != "null") {
        var datetime = new Date();
        datetime.setTime(milliseconds);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0"
        + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
            : datetime.getDate();
        var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
            : datetime.getHours();
        var minute = datetime.getMinutes() < 10 ? "0"
        + datetime.getMinutes() : datetime.getMinutes();
        var second = datetime.getSeconds() < 10 ? "0"
        + datetime.getSeconds() : datetime.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute
            + ":" + second;
    } else {
        return "";
    }

}

/**
 *
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 *
 * @returns list
 */
$.stringToList = function(value) {
    if (value != undefined && value != '') {
        var values = [];
        var t = value.split(',');
        for ( var i = 0; i < t.length; i++) {
            values.push('' + t[i]);/* 避免他将ID当成数字 */
        }
        return values;
    } else {
        return [];
    }
};

/**
 *
 * 增加formatString功能
 *
 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 *
 * @returns 格式化后的字符串
 */
$.formatString = function(str) {
    for ( var i = 0; i < arguments.length - 1; i++) {
        str = str.replace("{" + i + "}", arguments[i + 1]);
    }
    return str;
};