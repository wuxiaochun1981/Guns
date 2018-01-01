/**
 * infoManager管理初始化
 */
var PersonnelInfo = {
    id: "PersonnelInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PersonnelInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'userName', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle',sortable: true ,
                formatter: function (value, row, index) {
                var sexName = '男';
                if(value==2){
                    sexName = '女';
                }
                return sexName;
            }},
            {title: '年龄', field: 'age', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '出生日期', field: 'birthday', visible: true, align: 'center', valign: 'middle',sortable: true,
                formatter: function (value, row, index) {
                    var date = new Date(value);
                    console.log("日期=" + date.Format("yyyy年MM月dd日"));
                    return date.Format("yyyy年MM月dd日");
                }},
            {title: '民族', field: 'ethnicity', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '国籍', field: 'nationality', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '受教育程度', field: 'education', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '政治面貌', field: 'political', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '户籍', field: 'householdType', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '婚姻状况', field: 'marriage', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '身份证号', field: 'idCard', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: 'E-mail ', field: 'email', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '就职公司', field: 'company', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '职业', field: 'occupation', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '市', field: 'city', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '区、县', field: 'county', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '地址信息', field: 'address', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '是否户主', field: 'householder', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '与户主关系', field: 'householderRel', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '户籍省', field: 'srcProvince', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '户籍城市', field: 'srcCity', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '户籍', field: 'srcCounty', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '户籍地址', field: 'srcAddress', visible: true, align: 'center', valign: 'middle',sortable: true}
    ];
};

/**
 * 检查是否选中
 */
PersonnelInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PersonnelInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加infoManager
 */
PersonnelInfo.openAddPersonnelInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加用戶信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/personnelInfo/personnelInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看infoManager详情
 */
PersonnelInfo.openPersonnelInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用戶详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/personnelInfo/personnelInfo_update/' + PersonnelInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除infoManager
 */
PersonnelInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/personnelInfo/delete", function (data) {
            Feng.success("删除成功!");
            PersonnelInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("personnelInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询infoManager列表
 */
PersonnelInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PersonnelInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PersonnelInfo.initColumn();
    var table = new BSTable(PersonnelInfo.id, "/personnelInfo/list", defaultColunms);
    table.setPaginationType("client");
    PersonnelInfo.table = table.init();
});
