/**
 * 初始化请求日志详情对话框
 */
var QueryLogInfoInfoDlg = {
    queryLogInfoInfoData : {}
};

/**
 * 清除数据
 */
QueryLogInfoInfoDlg.clearData = function() {
    this.queryLogInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QueryLogInfoInfoDlg.set = function(key, val) {
    this.queryLogInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QueryLogInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QueryLogInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.QueryLogInfo.layerIndex);
}

/**
 * 收集数据
 */
QueryLogInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('uid')
    .set('appid')
    .set('status')
    .set('ip')
    .set('params')
    .set('tradeNo')
    ;
}

/**
 * 提交添加
 */
QueryLogInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/queryLogInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.QueryLogInfo.table.refresh();
        QueryLogInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.queryLogInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QueryLogInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/queryLogInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.QueryLogInfo.table.refresh();
        QueryLogInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.queryLogInfoInfoData);
    ajax.start();
}

$(function() {

});
