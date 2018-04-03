// pages/shop/shopOperate/shopOperate.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    item_name: '',
    defineItems: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _brandId = options.brand_id,
      _that = this;
    if (_brandId) {
      //编辑
      wx.request({
        url: 'http://120.24.49.36/mapi/goods/queryAgentLevel.do',
        data: {
          account_id: 1,
          brand_id: _brandId,
          type: 1
        },
        success: function (res) {
          // console.log(res.data)
          if (res.data.code == 0) {
            _that.setData({
              item_name: res.data.brand_name,
              defineItems: res.data.list
            })
          }
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

  /**
   * 添加自定义代理商级别
   */
  addDefine: function (e) {
    var _that = this,
      length = _that.data.defineItems.length;
    _that.data.defineItems.splice(length, 0, { agentlevel_name: '', agentlevel_default: 0 })
    _that.setData({
      defineItems: _that.data.defineItems
    })
  },

  delDefine: function (e) {
    var _that = this,
      idx = e.currentTarget.dataset.index;//当前索引
    _that.data.defineItems.splice(idx, 1)
    _that.setData({
      defineItems: _that.data.defineItems
    })
  }
})