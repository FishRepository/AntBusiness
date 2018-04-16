// pages/mine/mine.js
var app = getApp();
var WebService = require('../../utils/webService.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:null,
    wechatIsBind:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userInfo = app.globalData.userInfo;
    var that = this;
    var wechatIsBind = false;
    if (userInfo.account_wechat){
      wechatIsBind = true;
    }
    
    that.setData({
      userInfo: userInfo,
      wechatIsBind: wechatIsBind
    });
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

  showUrl: function () {
    var that = this;
    var sendUrl = WebService.HOST + WebService.SEND_CODE_URL;
    that.setData({
      url: sendUrl
    })
  },

  /**
   * 用户登出
   */
  loginOut: function () {
    wx.clearStorage();
    app.globalData.userInfo = null;
    wx.reLaunch({
      url: '/pages/login/login',
    })
  },

  /**
   * 绑定微信号
   */
  bindWechat: function () {
    var that = this;
    var wechatIsBind = that.data.wechatIsBind;
    if (wechatIsBind){
      return;
    }
    wx.login({
      success: function (res) {
        console.log(res.code);
        if (res.code) {
          //发起网络请求
          wx.request({
            url: WebService.HOST + "/mapi//wx/getOpenid.do",
            data: {
              accountId: app.globalData.userInfo.account_id,
              code: res.code
            },
            success: function (res) {
              if (res.data.code && res.data.code==0){
                var wechatIsBind = true;
                that.setData({
                  wechatIsBind: wechatIsBind
                });
                wx.showToast({
                  title: '绑定成功',
                  icon: 'success',
                  duration: 1500
                });
                console.log(res.data.msg);
              }else{
                console.log(res.data.msg);
                wx.showToast({
                  title: res.data.msg,
                  icon: 'none',
                  duration: 2500
                });
              }
            }
          })
        }
        // wx.getUserInfo({
        //   success: function (res) {
        //     var simpleUser = res.userInfo;
        //     var encryptedData = res.encryptedData;
        //     console.log(simpleUser.nickName);
        //     console.log(simpleUser);
        //   }
        // });
      }
    });
  }
})