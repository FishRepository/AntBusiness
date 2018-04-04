// pages/introDetail/introDetail.js
var WebService = require('../../utils/webService.js')
var WxParse = require('../../wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: null,
    functions: null,
    operates: null,
    images: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _title = options.title;
    var _that = this
    _that.setData({
      title: _title
    })
    // var list = _that.data.operates
    // var ary = new Array
    // for (var i = 0, len = list.length; i < len; ++i) {
    //   ary[i] = list[i].img
    // }
    // _that.setData({
    //   images: ary
    // })

    wx.request({
      url: WebService.HOST + WebService.FETCH_INTRODUCTION_URL,
      data: {
        id: options.id
      },
      success: function (result) {
        _that.setData({
          functions: result.data.functions
        })
        // 富文本绑定
        WxParse.wxParse('operates', 'html', result.data.operates, _that, 5)
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

  },

  /**
   * 预览图片
   */
  prePhotos: function (e) {
    var index = e.target.dataset.index;
    var _that = this
    wx.previewImage({
      urls: _that.data.images,
      current: _that.data.operates[index].img
    })
  }
})