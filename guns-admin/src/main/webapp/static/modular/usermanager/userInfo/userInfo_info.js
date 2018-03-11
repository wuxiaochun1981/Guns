

/**
 * 用户权限详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
    userInfoData: {},
    validateFields: {
        userName: {
            validators: {
                notEmpty: {
                    message: '用户名称不能为空'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '手机号不能为空'
                }
            }
        },
        appid: {
            validators: {
                notEmpty: {
                    message: 'appid不能为空'
                },
                remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                    url: Feng.ctxPath + "/userInfo/checkDuplicate",//验证地址
                    message: 'appid已存在',//提示消息
                    delay : 500,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                    type: 'POST',//请求方式
                    data: function(validator) {
                        console.log("validator=" + validator);
                        return {
                            appid: $('#appid').val(),
                            id: $('#id').val()
                        };
                    }
                }
            }
        }
    }
};

/**
 * 初始化接口权限管理详情对话框
 */
var UserInfoInfoDlg = {
    userInfoInfoData : {}
};

/**
 * 清除数据
 */
UserInfoInfoDlg.clearData = function() {
    this.userInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoInfoDlg.set = function(key, val) {
    this.userInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.UserInfo.layerIndex);
}

/**
 * 收集数据
 */
UserInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userName')
    .set('phone')
    .set('appid')
    .set('token')
    .set('accessCount')
    .set('failCount')
    .set('status')
    .set('createUser')
    ;
}

/**
 * 提交添加
 */
UserInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserInfo.table.refresh();
        UserInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserInfo.table.refresh();
        UserInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);
    Feng.initValidator("userInfoFormEdit", UserInfoDlg.validateFields);
    //初始化性别选项
    $("#status").val($("#statusValue").val());
});
