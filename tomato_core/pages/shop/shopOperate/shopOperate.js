// pages/shop/shopOperate/shopOperate.js
var WebService = require('../../../utils/webService.js');
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    brand_id: '',
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
      _that.setData({
        brand_id: _brandId
      })
      //编辑
      wx.request({
        url: WebService.HOST + '/mapi/goods/queryAgentLevel.do',
        data: {
          account_id: app.globalData.userInfo.account_id,
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
   * 监听品牌名称输入事件
   */
  bindInputName: function (e) {
    this.setData({
      item_name: e.detail.value
    })
  },

  /**
   * 监听代理级别输入事件
   */
  bindInputLevel: function (e) {
    var _that = this,
      idx = e.currentTarget.dataset.index;
    _that.data.defineItems[idx].agentlevel_name = e.detail.value
  },

  /**
   * 添加自定义级别
   */
  addDefine: function (e) {
    var _that = this,
      length = _that.data.defineItems.length;
    _that.data.defineItems.splice(length, 0, { agentlevel_name: '', agentlevel_default: 0 })
    _that.setData({
      defineItems: _that.data.defineItems
    })
  },

  /**
   * 删除自定义级别
   */
  delDefine: function (e) {
    var _that = this,
      _brandId = _that.data.brand_id,
      idx = e.currentTarget.dataset.index;//当前索引
    //先删数据库数据
    var item = _that.data.defineItems[idx]
    if (item.agentlevel_id) {
      wx.request({
        url: WebService.HOST + '/mapi/goods/deleteAgentLevel.do',
        data: {
          brand_id: _brandId,
          account_id: app.globalData.userInfo.account_id,
          agentlevel_id: item.agentlevel_id
        }
      })
    }
    _that.data.defineItems.splice(idx, 1)
    _that.setData({
      defineItems: _that.data.defineItems
    })
  },

  /**
   * 保存新增的品牌信息
   */
  saveBrand: function (e) {
    var _that = this,
      _brandId = _that.data.brand_id,
      _brandName = _that.data.item_name,
      _levelList = _that.data.defineItems;
    if (!_brandName) {
      wx.showToast({
        title: '请输入品牌名称！',
        icon: 'none',
        duration: 2000
      })
    } else {
      var isCheck = false;//验证是否级别全部都输入了
      var _leveNames = '';
      for (var i in _levelList) {
        if (!_levelList[i].agentlevel_name) {
          isCheck = true
        }
        _leveNames += _levelList[i].agentlevel_name + '|'
      }
      if (isCheck) {
        wx.showToast({
          title: '请输入级别名称！',
          icon: 'none',
          duration: 2000
        })
      } else {
        if (_leveNames.length > 0) {
          _leveNames = _leveNames.substr(0, _leveNames.length - 1)
        }
        wx.showLoading({
          title: '',
          mask: true
        })
        if (_brandId && _brandId.length > 0) {
          //说明是进行了修改操作
          wx.request({
            url: WebService.HOST + '/mapi/goods/updateBrand.do',
            data: {
              brand_id: _brandId,
              account_id: app.globalData.userInfo.account_id,
              brand_name: _brandName
            },
            success: function (res) {
              //进行代理级别的修改
              for (var i in _levelList) {
                if (_levelList[i].agentlevel_id) {
                  //修改
                  wx.request({
                    url: WebService.HOST + '/mapi/goods/updateAgentLevel.do',
                    data: {
                      brand_id: _brandId,
                      account_id: app.globalData.userInfo.account_id,
                      agentlevel_id: _levelList[i].agentlevel_id,
                      agentlevel_name: _levelList[i].agentlevel_name
                    }
                  })
                } else {
                  //新增
                  wx.request({
                    url: WebService.HOST + '/mapi/goods/insertAgentLevel.do',
                    data: {
                      brand_id: _brandId,
                      account_id: app.globalData.userInfo.account_id,
                      agentlevel_name: _levelList[i].agentlevel_name
                    }
                  })
                }
              }
              wx.hideLoading();
              if (res.data.code == 0) {
                wx.navigateBack({//返回
                  delta: 1
                })
              }
            },
            fail: function () {
              wx.hideLoading();
            }
          })
        } else {
          //新增操作
          wx.request({
            url: WebService.HOST + '/mapi/goods/insertBrand.do',
            data: {
              account_id: app.globalData.userInfo.account_id,
              brand_name: _brandName,
              agentlevel_names: _leveNames
            },
            success: function (res) {
              wx.hideLoading();
              if (res.data.code == 0) {
                wx.navigateBack({
                  url: '/pages/shop/shop',
                })
              }
            },
            fail: function () {
              wx.hideLoading();
            }
          })
        }
      }
    }
  }
})