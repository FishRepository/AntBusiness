// pages/shop/shopOperate/shopOperate.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    item_name:null,
    defineItems:[{
      name:'批发价'
    },{
        name: '代理价'
    },{
        name: '团购价'
    },{
        name: '内部价'
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var name = options.name
    if(name){
      this.setData({
        item_name: name
      })
    }else{
      this.setData({
        defineItems: []
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
  addDefine:function(e){
    
  }
})