/**
 * 用户权限管理管理初始化
 */
var UserInfo = {
    id: "UserInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名称', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '应用id', field: 'appid', visible: true, align: 'center', valign: 'middle'},
            {title: '用户tokenkey', field: 'tokenKey', visible: true, align: 'center', valign: 'middle'},
            {title: '成功访问数量', field: 'accessCount', visible: true, align: 'center', valign: 'middle'},
            {title: '失败访问次数', field: 'failCount', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {
                // var date = eval('new ' + eval(value).source)
                // return date.format("yyyy年MM月dd日");
                    if(value==0){
                        return "正常";
                    }else if(value==1){
                        return "暂停";
                    }else if(value==2){
                        return "删除";
                    }
                    return "未知";
                }
            },
            {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户权限管理
 */
UserInfo.openAddUserInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户权限管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userInfo/userInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户权限管理详情
 */
UserInfo.openUserInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户权限管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userInfo/userInfo_update/' + UserInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户权限管理
 */
UserInfo.delete = function () {
    if (this.check()) {

        var operation = function() {
            var ajax = new $ax(Feng.ctxPath + "/userInfo/delete", function (data) {
                Feng.success("删除成功!");
                UserInfo.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userInfoId", UserInfo.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否删除用户权限" + UserInfo.seItem.userName + "?",operation);
    }
};

/**
 * 查询用户权限管理列表
 */
UserInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UserInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserInfo.initColumn();
    var table = new BSTable(UserInfo.id, "/userInfo/list", defaultColunms);
    table.setPaginationType("client");
    UserInfo.table = table.init();
});
