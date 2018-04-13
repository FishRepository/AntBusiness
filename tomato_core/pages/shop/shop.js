// pages/shop/shop.js
var WebService = require('../../utils/webService.js');
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    items: [],
    startX: 0,
    startY: 0
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
    var _that = this;
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    wx.request({
      url: WebService.HOST + '/mapi/goods/queryBrand.do',
      data: {
        account_id: app.globalData.userInfo.account_id,
        type: 1
      },
      success: function (res) {
        wx.hideLoading();
        if (res.data.code == 0) {
          // console.log(res.data.list)
          _that.setData({
            items: res.data.list
          })
        }else{
          wx.showToast({
            title: '无品牌！',
            icon: 'none',
            duration: 2000
          })
        }
      }
    })
    var list = _that.data.items
    for (var j = 0, len = list.length; j < len; ++j) {
      list[j].isTouchMove = false
    }
    _that.setData({
      items: list
    })
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

  //手指触摸动作开始 记录起点X坐标
  touchstart: function (e) {
    //开始触摸时 重置所有删除
    this.data.items.forEach(function (v, i) {
      if (v.isTouchMove)//只操作为true的
        v.isTouchMove = false;
    })
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      items: this.data.items
    })
  },
  //滑动事件处理
  touchmove: function (e) {
    var that = this,
      index = e.currentTarget.dataset.index,//当前索引
      startX = that.data.startX,//开始X坐标
      startY = that.data.startY,//开始Y坐标
      touchMoveX = e.changedTouches[0].clientX,//滑动变化坐标
      touchMoveY = e.changedTouches[0].clientY,//滑动变化坐标
      //获取滑动角度
      angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    that.data.items.forEach(function (v, i) {
      v.isTouchMove = false
      //滑动超过30度角 return
      if (Math.abs(angle) > 30) return;
      if (i == index) {
        if (touchMoveX > startX) //右滑
          v.isTouchMove = false
        else //左滑
          v.isTouchMove = true
      }
    })
    //更新数据
    that.setData({
      items: that.data.items
    })
  },
  /**
   * 计算滑动角度
   * @param {Object} start 起点坐标
   * @param {Object} end 终点坐标
   */
  angle: function (start, end) {
    var _X = end.X - start.X,
      _Y = end.Y - start.Y
    //返回角度 /Math.atan()返回数字的反正切值
    return 360 * Math.atan(_Y / _X) / (2 * Math.PI);
  },
  //删除事件
  delShop: function (e) {
    var that = this,
      idx = e.currentTarget.dataset.index;
    wx.showModal({
      title: '提示',
      content: '是否确认删除该品牌？',
      success: function (res) {
        if (res.confirm) {
          wx.showLoading({
            title: '',
            mask: true
          })
          wx.request({
            url: WebService.HOST + '/mapi/goods/deleteBrand.do?=1&=1',
            data: {
              brand_id: that.data.items[idx].brand_id,
              account_id: app.globalData.userInfo.account_id
            },
            success: function (res) {
              wx.hideLoading();
            }
          })
          that.data.items.splice(e.currentTarget.dataset.index, 1)
          that.setData({
            items: that.data.items
          })
        } else if (res.cancel) {

        }
      }
    })
  },
  //编辑事件
  editShop: function (e) {
    var idx = e.currentTarget.dataset.index
    var _that = this;
    wx.navigateTo({
      url: '/pages/shop/shopOperate/shopOperate?brand_id=' + _that.data.items[idx].brand_id
    })
  },
  //添加品牌事件
  addShop: function (e) {
    wx.navigateTo({
      url: '/pages/shop/shopOperate/shopOperate?brand_id='
    })
  },
  //进入品牌详情
  gotoGetail: function (e) {
    var idx = e.currentTarget.dataset.index,
      _that = this;
    wx.navigateTo({
      url: '/pages/shop/shopDetail/shopDetail?brand_id=' + _that.data.items[idx].brand_id
    })
  }
})