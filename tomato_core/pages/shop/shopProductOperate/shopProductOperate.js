// pages/shop/shopProductOperate/shopProductOperate.js
var app = getApp();
var WebService = require('../../../utils/webService.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    brand: {},
    goods: {},
    items: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _brandId = options.brand_id,
      _goodsId = options.goods_id,
      _that = this;
    if (_brandId && _goodsId) {
      //说明是过来的编辑链接
      wx.request({
        url: WebService.HOST + '/mapi/goods/queryOneGoods.do',
        data: {
          account_id: app.globalData.userInfo.account_id,
          brand_id: _brandId,
          goods_id: _goodsId
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
    } else {
      //传递过来是新增链接，加载代理层级
      wx.request({
        url: WebService.HOST + '/mapi/goods/queryAgentLevel.do',
        data: {
          account_id: app.globalData.userInfo.account_id,
          brand_id: _brandId,
          type: 1
        },
        success: function (res) {
          // console.log(res.data)
          _that.setData({
            brand: {
              brand_id: _brandId,
              brand_name: res.data.brand_name
            },
            goods: {
              goods_name: ''
            },
            items: res.data.list
          })
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
   * 监听商品名称输入事件
   */
  bindInputGoods: function (e) {
    var _that = this,
      _goods = _that.data.goods;
    _goods.goods_name = e.detail.value
    _that.setData({
      goods: _goods
    })
  },

  /**
   * 我的进货价和进货价出售输入监听事件
   */
  bindInputGoodsPrice: function (e) {
    var _that = this,
      _goods = _that.data.goods;
    _goods.goods_price = e.detail.value
    _that.setData({
      goods: _goods
    })
  },

  /**
   * 每一项级别的价格输入监听事件
   */
  bindInputItemPrice: function (e) {
    var _that = this,
      _items = _that.data.items,
      idx = e.currentTarget.dataset.index;
    _items[idx].goods_price = e.detail.value
    _that.setData({
      items: _items
    })
  },

  /**
   * 保存按钮事件
   */
  saveGoods: function (e) {
    var _that = this,
      _brand = _that.data.brand,
      _goods = _that.data.goods,
      _itemList = _that.data.items;
    if (!_goods.goods_name) {
      wx.showToast({
        title: '请输入商品名称！',
        icon: 'none',
        duration: 2000
      })
    } else {
      if (_brand.brand_id && _goods.goods_id) {
        wx.showLoading({
          title: '',
          mask: true
        })
        //修改商品
        wx.request({
          url: WebService.HOST + '/mapi/goods/updateGoods.do',
          data: {
            brand_id: _brand.brand_id,
            account_id: app.globalData.userInfo.account_id,
            goods_id: _goods.goods_id,
            goods_name: _goods.goods_name,
            goods_price: _goods.goods_price
          },
          success: function (res) {
            //修改商品代理价格
            for (var i in _itemList) {
              wx.request({
                url: WebService.HOST + '/mapi/goods/updateGoodsPrice.do',
                data: {
                  account_id: app.globalData.userInfo.account_id,
                  goods_id: _goods.goods_id,
                  agentlevel_id: _itemList[i].agentlevel_id,
                  goods_price: _itemList[i].goods_price
                }
              })
            }
            wx.hideLoading();
            if (res.data.code == 0) {
              wx.navigateBack({//返回
                delta: 1
              })
            }
          }
        })
      } else {
        var _agentPrices = '';
        for (var i in _itemList) {
          var _agent_id = _itemList[i].agentlevel_id
          var _agent_price = _itemList[i].goods_price ? _itemList[i].goods_price : 0
          _agentPrices += _agent_id + ',' + _agent_price + '|'
        }
        if (_agentPrices.length > 0) {
          _agentPrices = _agentPrices.substr(0, _agentPrices.length - 1)
        }
        wx.showLoading({
          title: '',
          mask: true
        })
        //新增逻辑
        wx.request({
          url: WebService.HOST + '/mapi/goods/insertGoods.do',
          data: {
            account_id: app.globalData.userInfo.account_id,
            brand_id: _brand.brand_id,
            goods_name: _goods.goods_name,
            goods_price: _goods.goods_price ? _goods.goods_price : 0,
            agent_prices: _agentPrices
          },
          success: function (res) {
            wx.hideLoading();
            if (res.data.code == 0) {
              let pages = getCurrentPages();//当前页面
              let prevPage = pages[pages.length - 2];//上一页面
              prevPage.setData({//直接给上移页面赋值
                brandId: _brand.brand_id
              });
              wx.navigateBack({//返回
                delta: 1
              })
            }
          }
        })
      }
    }
  }
})