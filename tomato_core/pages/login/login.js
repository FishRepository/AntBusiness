// pages/login/login.js
var app = getApp();
var WebService = require('../../utils/webService.js');
var Base64 = require('../../utils/base64.modified.js'); 
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    userid_focus: false,
    passwd_focus: false,
    userid: null,
    passwd: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userInfo = wx.getStorageSync(WebService.USERINFO_STORAGE_KEY);
    if (userInfo && userInfo.account_userphone && userInfo.password){
      wx.request({
        url: WebService.HOST + WebService.LOGIN_PHONE_URL,
        // method: 'POST',
        data: {
          account_userphone: Base64.encode(userInfo.account_userphone),
          account_password: Base64.encode(userInfo.password),
          account_imei: WebService.ACCOUNT_IMEI
        },
        success: function (data) {
          if (data && data.data.code == 0) {
            // this.packageUserInfo(data.data, password);
            var _userInfo = data.data;
            _userInfo['password'] = userInfo.password;
            wx.setStorageSync(WebService.USERINFO_STORAGE_KEY, _userInfo);
            app.globalData.userInfo = userInfo;
            wx.redirectTo({
              url: '/pages/index/index',
            })
          } else {
            wx.showToast({
              title: "登录信息失效,请重新登录!",
              icon: 'none',
              duration: 2000
            });
          }
        },
        fail: function () {
          wx.showToast({
            title: "登录信息失效,请重新登录!",
            icon: 'none',
            duration: 2000
          });
        }
      })
    }
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
  registUser: function () {
    wx.redirectTo({
      url: '/pages/regist/regist',
    })
  },
  forget: function () {
    wx.redirectTo({
      url: '/pages/forget/forget',
    })
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
      // method: 'POST',
      data: {
        account_userphone: Base64.encode(phone),
        account_password: Base64.encode(password),
        account_imei: WebService.ACCOUNT_IMEI
      },
      success: function(data){
        wx.hideLoading();
        if(data&&data.data.code==0){
          // this.packageUserInfo(data.data, password);
          var userInfo = data.data;
          userInfo['password'] = password;
          wx.setStorageSync(WebService.USERINFO_STORAGE_KEY, userInfo);
          app.globalData.userInfo = userInfo;
          wx.showToast({
            title: '登录成功',
            icon: 'none',
            duration: 2000
          });
          wx.redirectTo({
            url: '/pages/index/index',
          })
        }else{
          wx.showToast({
            title: data.data.msg,
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
  },
  // packageUserInfo: function (data, password) {
  //   var that = this;
  //   var userInfo = {};
  //   userInfo['password'] = password;
  //   if (data.account_address){
  //     userInfo['account_address'] = data.account_address;
  //   }
  //   if (data.account_icon) {
  //     userInfo['account_icon'] = data.account_icon;
  //   }
  //   if (data.account_id) {
  //     userInfo['account_id'] = data.account_id;
  //   }
  //   if (data.account_imei) {
  //     userInfo['account_imei'] = data.account_imei;
  //   }
  //   if (data.account_integral) {
  //     userInfo['account_integral'] = data.account_integral;
  //   }
  //   if (data.account_qq) {
  //     userInfo['account_qq'] = data.account_qq;
  //   }
  //   if (data.account_wechat) {
  //     userInfo['account_wechat'] = data.account_wechat;
  //   }
  //   if (data.account_username) {
  //     userInfo['account_username'] = data.account_username;
  //   }
  //   if (data.account_userphone) {
  //     userInfo['account_userphone'] = data.account_userphone;
  //   }
  //   if (data.last_time) {
  //     userInfo['last_time'] = data.last_time;
  //   }
  //   that.setData({
  //     userInfo: userInfo
  //   });
  // }
})