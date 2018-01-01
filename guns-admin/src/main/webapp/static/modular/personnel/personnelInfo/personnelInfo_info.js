/**
 * 初始化infoManager详情对话框
 */
var PersonnelInfoInfoDlg = {
    personnelInfoInfoData : {},
    validateFields: {
        userName: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        phone:{
            validators: {
                regexp: {
                    regexp: /^[0-9]{11}$/,
                    message: '手机号不正确'
                }
            }
        },
        idCard:{
            validators: {
                regexp: {
                    regexp: /^[a-z0-9]{15,18}$/,
                    message: '身份证号不正确'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
PersonnelInfoInfoDlg.clearData = function() {
    this.personnelInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PersonnelInfoInfoDlg.set = function(key, val) {
    this.personnelInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PersonnelInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PersonnelInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.PersonnelInfo.layerIndex);
}

/**
 * 收集数据
 */
PersonnelInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userName')
    .set('sex')
    .set('age')
    .set('birthday')
    .set('ethnicity')
    .set('nationality')
    .set('education')
    .set('political')
    .set('householdType')
    .set('marriage')
    .set('idCard')
    .set('phone')
    .set('email')
    .set('company')
    .set('province')
    .set('occupation')
    .set('city')
    .set('county')
    .set('address')
    .set('householder')
    .set('householderRel')
    .set('srcProvince')
    .set('srcCity')
    .set('srcCounty')
    ;
}

/**
 * 提交添加
 */
PersonnelInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/personnelInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.PersonnelInfo.table.refresh();
        PersonnelInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.personnelInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PersonnelInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/personnelInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.PersonnelInfo.table.refresh();
        PersonnelInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.personnelInfoInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("personnelInfoForm", PersonnelInfoInfoDlg.validateFields);
    //初始化性别选项
    $("#sex").val($("#sexValue").val());
});
