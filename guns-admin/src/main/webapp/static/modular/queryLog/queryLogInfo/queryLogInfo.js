/**
 * 请求日志管理初始化
 */
var QueryLogInfo = {
    id: "QueryLogInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QueryLogInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户uid', field: 'uid', visible: true, align: 'center', valign: 'middle'},
            {title: '用户appid', field: 'appid', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {
                    // var date = eval('new ' + eval(value).source)
                    // return date.format("yyyy年MM月dd日");
                    if(value==0){
                        return "正常";
                    }else if(value==1){
                        return "失败";
                    }
                    return "未知";
                }
             },
            {title: 'ip地址', field: 'ip', visible: true, align: 'center', valign: 'middle'},
            {title: '请求参数', field: 'params', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'tradeNo', visible: true, align: 'center', valign: 'middle'},
            {title: '返回结果', field: 'resultStr', visible: true, align: 'center', valign: 'middle'},
            {title: '访问时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
QueryLogInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QueryLogInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加请求日志
 */
QueryLogInfo.openAddQueryLogInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加请求日志',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/queryLogInfo/queryLogInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看请求日志详情
 */
QueryLogInfo.openQueryLogInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '请求日志详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/queryLogInfo/queryLogInfo_update/' + QueryLogInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除请求日志
 */
QueryLogInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/queryLogInfo/delete", function (data) {
            Feng.success("删除成功!");
            QueryLogInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("queryLogInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询请求日志列表
 */
QueryLogInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    QueryLogInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QueryLogInfo.initColumn();
    var table = new BSTable(QueryLogInfo.id, "/queryLogInfo/list", defaultColunms);
    table.setPaginationType("client");
    QueryLogInfo.table = table.init();
});
