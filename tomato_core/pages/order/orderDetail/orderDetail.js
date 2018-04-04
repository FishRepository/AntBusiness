// pages/order/orderDetail/orderDetail.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order:null,
    orderDetail:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _orderId = options.order_id,
    _that=this
    wx.request({
      url: 'http://120.24.49.36/mapi/order/queryOrder.do',
      data:{
        account_id: app.globalData.account_id,
        order_id: _orderId
      },
      success:function(res){
        // console.log(res.data)
        if(res.data.code==0){
          _that.setData({
            order: res.data.order,
            orderDetail:res.data.list
          })
        }
      }
    })
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

  }
})