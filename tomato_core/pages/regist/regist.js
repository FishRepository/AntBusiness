// pages/forget/forget.js
var WebService = require('../../utils/webService.js');
var Base64 = require('../../utils/base64.modified.js'); 
var utils = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShow:false,
    sec:60,
    userid: null,
    validCode: null,
    passwd: null,
    confirmPasswd: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  //手机号输入监听
  useridInput: function (e) {
    this.setData({
      userid: e.detail.value
    });
  },
  //验证码输入监听
  validCodeInput: function (e) {
    this.setData({
      validCode: e.detail.value
    });
  },
  //密码输入监听
  passwdInput: function (e) {
    this.setData({
      passwd: e.detail.value
    });
  },
  //密码确认输入监听
  confirmPasswdInput: function (e) {
    this.setData({
      confirmPasswd: e.detail.value
    });
  },
  //发送验证码
  sendCode: function(){
    var that = this;
    var userid = that.data.userid;
    if (!userid) {
      utils.showErrorModal('错误', '请输入手机号!');
      return;
    }
    if (!utils.isPoneAvailable(userid)) {
      utils.showErrorModal('错误', '请输入正确的手机号!');
      return;
    }
    this.getCodeInterval(that,60);
    wx.request({
      url: WebService.HOST + WebService.SEND_CODE_URL,
      data: {
        account_userphone: Base64.encode(userid),
        type: 1
      },
      success: function (data) {
        //
        if (data && data.data.code == 0) {
          utils.showLoadToast('验证码已发送', 1000);
        }else{
          utils.showErrorModal('错误', data.data.msg);
        }
      },
      fail: function(data) {
        console.log(data.data.msg);
      }
    })
  },
  //确认按钮
  confirm: function () {
    var that = this;
    var userid = that.data.userid;
    var validCode = that.data.validCode;
    var passwd = that.data.passwd;
    var confirmPasswd = that.data.confirmPasswd;
    if(!userid || !validCode || !passwd || !confirmPasswd){
      utils.showErrorModal('错误','有未填项!');
      return;
    }
    if (!utils.isPoneAvailable(userid)){
      utils.showErrorModal('错误', '请输入正确的手机号!');
      return;
    }
    if (!utils.isValidCode(validCode)){
      utils.showErrorModal('错误', '验证码格式不正确!');
      return;
    }
    if (passwd != confirmPasswd){
      utils.showErrorModal('错误', '两次输入密码不一致!');
      return;
    }
    wx.request({
      url: WebService.HOST + WebService.MOBILE_REGIST_URL,
      data: {
        account_userphone: Base64.encode(userid),
        account_code: validCode,
        account_imei: WebService.ACCOUNT_IMEI,
        account_password: Base64.encode(passwd)
      },
      success: function (data){
        if (data && data.data.code == 0){
          wx.showModal({
            title: '提示',
            content: '注册成功,请登录',
            showCancel: false,
            success: function (res) {
              if (res.confirm) {
                console.log('用户点击确定')
                wx.navigateTo({
                  url: '/pages/login/login',
                })
              }
            }
          })
        }else{
          utils.showErrorModal('错误', data.data.msg);
        }
      }
    })
  },
  //发送验证码后60S倒计时
  getCodeInterval: function (_this, num){
    _this.setData({
      isShow: true                    //按钮1隐藏，按钮2显示
    })
    var remain= num;             //用另外一个变量来操作秒数是为了保存最初定义的倒计时秒数，就不用在计时完之后再手动设置秒数
    var time = setInterval(function () {
      if (remain == 1) {
        clearInterval(time);
        _this.setData({
          sec: num,
          isShow: false
        })
        return false      //必须有
      }
      remain--;
      _this.setData({
        sec: remain
      })
    }, 1000)
  }
})