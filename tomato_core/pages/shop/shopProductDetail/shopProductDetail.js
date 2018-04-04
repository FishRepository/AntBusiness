// pages/shop/shopProductDetail/shopProductDetail.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    brand: '',
    goods: '',
    items: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var brandId = options.brand_id,
      goodsId = options.goods_id,
      _that = this;
    wx.request({
      url: 'http://120.24.49.36/mapi/goods/queryOneGoods.do',
      data: {
        account_id: app.globalData.account_id,
        brand_id: brandId,
        goods_id: goodsId
      },
      success: function (res) {
        // console.log(res.data)
        if (res.data.code == 0) {
          _that.setData({
            brand: res.data.brand,
            goods: res.data.goods,
            items: res.data.list
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