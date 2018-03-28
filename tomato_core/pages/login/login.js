// pages/login/login.js
var WebService = require('../../utils/webService.js');
var Base64 = require('../../utils/base64.modified.js'); 
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    userid_focus: false,
    passwd_focus: false,
    userid: null,
    passwd: null
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
  loginUser: function (e) {
    var that = this;
    var phone = that.data.userid;
    var password = that.data.passwd;
    if(!phone || !password){
      wx.showToast({
        title: '请输入账号密码',
        icon: 'none',
        duration: 2000
      });
      return;
    }
    wx.showLoading({
      title: '登录中',
    })
     
    wx.request({
      url: WebService.HOST+WebService.LOGIN_PHONE_URL,
      method: 'POST',
      data: {
        account_userphone: Base64.encode(phone),
        account_password: Base64.encode(password),
        account_imei: WebService.ACCOUNT_IMEI
      },
      success: function(data){
        wx.hideLoading();
        if(data&&data.code==0){
          wx.showToast({
            title: '登录成功',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: function(){
        wx.hideLoading();
      }
    })
  },
  inputFocus: function (e) {
    if (e.target.id == 'userid') {
      this.setData({
        'userid_focus': true
      });
    } else if (e.target.id == 'passwd') {
      this.setData({
        'passwd_focus': true
      });
    }
  },
  inputBlur: function (e) {
    if (e.target.id == 'userid') {
      this.setData({
        'userid_focus': false
      });
    } else if (e.target.id == 'passwd') {
      this.setData({
        'passwd_focus': false
      });
    }
  },
  useridInput: function (e) {
    this.setData({
      userid: e.detail.value
    });
  },
  passwdInput: function (e) {
    this.setData({
      passwd: e.detail.value
    });
  }
})