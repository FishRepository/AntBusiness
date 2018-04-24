//index.js
//获取应用实例
const app = getApp()
var WebService = require('../../utils/webService.js');
Page({
  data: {
    brands: [],//品牌集合
    orderTypeName: '',//订单类型名称
    totalPrice: 0,//总价格(减去额外费用后的数目)
    nowLevel: '',//当前显示的代理层级
    nowLevelList: [],//当前选中的品牌代理层级集合
    outPrice: 0,//额外费用
    selbrandIdx: 0,//当前选中的品牌index
    selgoodsIdx: 0,//当前选中的商品index
    selBandNum: 0,//已选择品牌数
    selGoodsNum: 0,//已选择商品总数量
  },
  onShow: function () {
    var _that = this;
    wx.getStorage({
      key: 'indexOrderData',
      success: function (res) {
        if (res.data) {
          _that.setData(res.data)
        }
      },
      fail: function (e) {
        wx.showLoading({
          title: '加载中...',
          mask: true
        })
        wx.request({
          url: WebService.HOST + '/mapi/goodsV2/queryBrandAndGoods.do',
          data: {
            account_id: app.globalData.userInfo.account_id
          },
          success: function (res) {
            //隐藏加载动画
            wx.hideLoading();
            if (res.data.code === 0 && res.data.list) {
              var _brandList = res.data.list,
                _firstagentlevellist = _brandList[0].agentlevellist,
                _nowLevel;
              if (_firstagentlevellist && _firstagentlevellist.length > 0) {
                for (var x in _firstagentlevellist){
                  if (_firstagentlevellist[x].agentlevel_default==1){
                    _nowLevel = _firstagentlevellist[x];
                    break;
                  }
                }
              } else {
                _nowLevel = {
                  agentlevel_id: -3,
                  agentlevel_name: '零售价'
                };
              }
              for (var i in _brandList) {
                //加入默认的代理层级
                if (_brandList[i].agentlevellist && _brandList[i].agentlevellist.length > 0) {
                  for (var x in _brandList[i].agentlevellist) {
                    if (_brandList[i].agentlevellist[x].agentlevel_default == 1) {
                      _brandList[i].agentlevellist[x].checked = true;
                    }
                  }
                  _brandList[i].agentlevellist.splice(_brandList[i].agentlevellist.length, 0, {
                    agentlevel_id: -1,
                    agentlevel_name: '我要进货'
                  }, {
                      agentlevel_id: -2,
                      agentlevel_name: '进货价出售'
                    });
                }else{
                  _brandList[i].agentlevellist = [{
                    agentlevel_id: -3,
                    agentlevel_name: '零售价',
                    checked:true
                  }, {
                    agentlevel_id: -1,
                    agentlevel_name: '我要进货'
                  }, {
                    agentlevel_id: -2,
                    agentlevel_name: '进货价出售'
                  }];
                }
                for (var j in _brandList[i].goodsAndGoodsPricelist) {
                  //先给商品加入默认代理层级
                  _brandList[i].goodsAndGoodsPricelist[j].goodspricelist.splice(_brandList[i].goodsAndGoodsPricelist[j].goodspricelist.length, 0, {
                    agentlevel_id: -1,
                    agentlevel_name: '我要进货',
                    goods_price: _brandList[i].goodsAndGoodsPricelist[j].goods_price
                  }, {
                      agentlevel_id: -2,
                      agentlevel_name: '进货价出售',
                      goods_price: _brandList[i].goodsAndGoodsPricelist[j].goods_price
                    });
                }
              }
              for (var i in _brandList[0].goodsAndGoodsPricelist) {
                var _goodsPriceAgentList = _brandList[0].goodsAndGoodsPricelist[i].goodspricelist;
                for (var j in _goodsPriceAgentList) {
                  if (_goodsPriceAgentList[j].agentlevel_id === _nowLevel.agentlevel_id) {
                    _brandList[0].goodsAndGoodsPricelist[i].goods_price = _goodsPriceAgentList[j].goods_price
                    _brandList[0].goodsAndGoodsPricelist[i].agentlevel_id = _goodsPriceAgentList[j].agentlevel_id
                  }
                }
              }
              //当前代理层级赋值
              _that.setData({
                brands: _brandList,
                orderTypeName: '2',//默认置为出货订单
                nowLevel: _nowLevel,
                nowLevelList: _brandList[0].agentlevellist,
                isGlobalBrandGoodsNone: false,
                // brands: [],//品牌集合
                // orderTypeName: '',//订单类型名称
                totalPrice: 0,//总价格(减去额外费用后的数目)
                // nowLevel: '',//当前显示的代理层级
                // nowLevelList: [],//当前选中的品牌代理层级集合
                outPrice: 0,//额外费用
                selbrandIdx: 0,//当前选中的品牌index
                selgoodsIdx: 0,//当前选中的商品index
                selBandNum: 0,//已选择品牌数
                selGoodsNum: 0,//已选择商品总数量
              })
            } else {
              wx.hideLoading();
              //设置无商品页面
              _that.setData({
                orderTypeName: '',
                isGlobalBrandGoodsNone: true
              })
            }
          },
          fail: function () {
            wx.hideLoading();
            wx.showToast({
              title: '数据加载失败！',
              icon: 'none',
              duration: 2000
            })
          }
        })
      }
    })
  },
  /**
   * 切换品牌列表
   */
  swapTabTable: function (e) {
    var selIndex = e.currentTarget.id,
      _that = this,
      _brandList = _that.data.brands,
      _firstagentlevellist = _brandList[selIndex].agentlevellist;
      var _nowLevel;
      for(var i in _firstagentlevellist){
        if(_firstagentlevellist[i].checked){
          _nowLevel=_firstagentlevellist[i];
        }
      }
    for (var i in _brandList[selIndex].goodsAndGoodsPricelist) {
      var _goodsPriceAgentList = _brandList[selIndex].goodsAndGoodsPricelist[i].goodspricelist;
      for (var j in _goodsPriceAgentList) {
        if (_goodsPriceAgentList[j].agentlevel_id === _nowLevel.agentlevel_id) {
          _brandList[selIndex].goodsAndGoodsPricelist[i].goods_price = _goodsPriceAgentList[j].goods_price
          _brandList[selIndex].goodsAndGoodsPricelist[i].agentlevel_id = _goodsPriceAgentList[j].agentlevel_id
        }
      }
    }
    _that.setData({
      selbrandIdx: selIndex,
      nowLevel: _nowLevel,
      nowLevelList: _brandList[selIndex].agentlevellist,
      brands: _brandList
    });
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 减数量
   */
  subProNum: function (e) {
    var _that = this,
      selidx = e.currentTarget.dataset.pindex,
      idx = e.currentTarget.dataset.cindex,
      list = _that.data.brands,
      c_list = list[selidx].goodsAndGoodsPricelist,
      alNum = c_list[idx].num;
    c_list[idx].num = alNum*1 - 1;
    var new_totalNum = _that.data.selGoodsNum*1 - 1;
    var newT_price = _that.data.totalPrice - c_list[idx].goods_price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].goodsAndGoodsPricelist
      var is_selBand = false
      list[i].hasSelectItem = false
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num > 0) {
          is_selBand = true
          list[i].hasSelectItem = true
        }
      }
      if (is_selBand) {
        new_selBandNum++
      }
    }
    _that.setData({
      brands: list,
      totalPrice: newT_price,
      selBandNum: new_selBandNum,
      selGoodsNum: new_totalNum
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 加数量
   */
  addProNum: function (e) {
    var _that = this,
      selidx = e.currentTarget.dataset.pindex,
      idx = e.currentTarget.dataset.cindex,
      list = _that.data.brands,
      c_list = list[selidx].goodsAndGoodsPricelist,
      alNum = c_list[idx].num;
    if (alNum) {
      c_list[idx].num = alNum*1 + 1;
    } else {
      c_list[idx].num = 1 * 1;
    }
    var new_totalNum = _that.data.selGoodsNum*1 + 1;
    var newT_price = _that.data.totalPrice + c_list[idx].goods_price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].goodsAndGoodsPricelist
      var is_selBand = false
      list[i].hasSelectItem = false
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num && child[j].num > 0) {
          is_selBand = true
          list[i].hasSelectItem = true
        }
      }
      if (is_selBand) {
        new_selBandNum++
      }
    }
    _that.setData({
      brands: list,
      totalPrice: newT_price,
      selBandNum: new_selBandNum,
      selGoodsNum: new_totalNum
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 清空按钮操作
   */
  clearEmpty: function (e) {
    // var _that = this,
    //   list = _that.data.brands;
    // for (var i = 0, len = list.length; i < len; ++i) {
    //   var child = list[i].goodsAndGoodsPricelist
    //   for (var j = 0, j_len = child.length; j < j_len; ++j) {
    //     child[j].num = 0
    //   }
    // }
    // _that.setData({
    //   brands: list,
    //   totalPrice: 0,
    //   selBandNum: 0,
    //   selGoodsNum: 0,
    //   maskVisual: false
    // })
    //先清除缓存
    wx.removeStorage({
      key: 'indexOrderData',
      success: function (res) {
        // wx.hideLoading();
      }
    })
    // _that.cascadeDismiss();
    this.onShow();
  },
  /**
   * 弹出数量弹窗
   */
  showNumModalDialog: function (e) {
    var _that = this,
      idx = e.currentTarget.dataset.index;
    _that.setData({
      showNumModal: true,
      selgoodsIdx: idx
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
     * 隐藏数量弹窗
     */
  hideNumModalDialog: function () {
    this.setData({
      showNumModal: false
    });
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: this.data
    })
  },
  /**
   * 弹出框蒙层截断touchmove事件
   */
  preventTouchMove: function () {
  },
  /**
   * 数量弹窗输入数量事件
   */
  brandItemNumChange: function (e) {
    var _that = this,
      prtIdx = _that.data.selbrandIdx,
      idx = e.currentTarget.dataset.index,
      list = _that.data.brands;
    list[prtIdx].goodsAndGoodsPricelist[idx].num = e.detail.value;
    _that.setData({
      brands: list
    });
    var t_price = 0;
    var t_selbanNum = 0;
    var t_totalNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var c_list = list[i].goodsAndGoodsPricelist
      var is_selBand = false
      list[i].hasSelectItem = false
      for (var j = 0, j_len = c_list.length; j < j_len; ++j) {
        var num = c_list[j].num
        var price = c_list[j].goods_price
        if (num > 0) {
          is_selBand = true
          list[i].hasSelectItem = true
          t_price += num * price
          t_totalNum += num * 1
        }
      }
      if (is_selBand) {
        t_selbanNum++
      }
    }
    _that.setData({
      brands: list,
      totalPrice: t_price,
      selBandNum: t_selbanNum,
      selGoodsNum: t_totalNum
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 弹出层级弹窗
   */
  showLevelModalDialog: function (e) {
    this.setData({
      showLevelModal: true
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: this.data
    })
  },
  /**
     * 隐藏层级弹窗
     */
  hideLevelModalDialog: function () {
    this.setData({
      showLevelModal: false
    });
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: this.data
    })
  },
  /**
   * 选择代理层级
   */
  switchSelectLevel: function (e) {
    var _that = this,
      _brandList = _that.data.brands,
      selBrandIdx = _that.data.selbrandIdx,
      idx = e.currentTarget.dataset.index;
      //当前操作订单只允许操作一种代理层级
    //如果选择的是我要进货
    if (_brandList[selBrandIdx].agentlevellist[idx].agentlevel_id==-1){
      //判断其他品牌是否选了出货代理层级
      for (var k in _brandList) {
        //只用判断其他的品牌是否选择了我要进货，如果是当前品牌选择了我要进货，则可以进行切换代理层级操作
        if (k != selBrandIdx) {
          var _brand_goodList = _brandList[k].goodsAndGoodsPricelist;
          for (var m in _brand_goodList) {
            if (_brand_goodList[m].agentlevel_id != -1 && _brand_goodList[m].num && _brand_goodList[m].num > 0) {
              wx.showToast({
                title: '当前订单为【出货订单】，此品牌不能选择【进货订单】代理层级。如需切换订单类型，请先执行清空操作！',
                icon: 'none',
                duration: 3000
              })
              return;
            }
          }
          //当前品牌选择了我要进货时，将其他品牌默认置为我要进货
          for (var j in _brandList[k].agentlevellist){
            if (_brandList[k].agentlevellist[j].agentlevel_id==-1){
              _brandList[k].agentlevellist[j].checked = true;
            }else{
              _brandList[k].agentlevellist[j].checked = false;
            }
          }
        }
      }
    }else{
    //如果选择的是出货代理层级  
      for (var k in _brandList) {
        //只用判断其他的品牌是否选择了我要进货，如果是当前品牌选择了我要进货，则可以进行切换代理层级操作
        if (k != selBrandIdx) {
          var _brand_goodList = _brandList[k].goodsAndGoodsPricelist;
          for (var m in _brand_goodList) {
            if (_brand_goodList[m].agentlevel_id == -1 && _brand_goodList[m].num && _brand_goodList[m].num > 0) {
              wx.showToast({
                title: '当前订单为【进货订单】，此品牌不能选择【出货订单】代理层级。如需切换订单类型，请先执行清空操作！',
                icon: 'none',
                duration: 3000
              })
              return;
            }
          }
        }
      }
    }
    for (var i = 0, len = _brandList[selBrandIdx].agentlevellist.length; i < len; ++i) {
      if (idx == i) {
        _brandList[selBrandIdx].agentlevellist[i].checked = true;
      } else {
        _brandList[selBrandIdx].agentlevellist[i].checked = false;
      }
    }
    _that.setData({
      // nowLevelList: list,
      nowLevel: _brandList[selBrandIdx].agentlevellist[idx]
    });
    var _nowLevel = _brandList[selBrandIdx].agentlevellist[idx];
    for (var i in _brandList[selBrandIdx].goodsAndGoodsPricelist) {
      for (var j in _brandList[selBrandIdx].goodsAndGoodsPricelist[i].goodspricelist) {
        if (_brandList[selBrandIdx].goodsAndGoodsPricelist[i].goodspricelist[j].agentlevel_id === _nowLevel.agentlevel_id) {
          _brandList[selBrandIdx].goodsAndGoodsPricelist[i].goods_price = _brandList[selBrandIdx].goodsAndGoodsPricelist[i].goodspricelist[j].goods_price
          _brandList[selBrandIdx].goodsAndGoodsPricelist[i].agentlevel_id = _brandList[selBrandIdx].goodsAndGoodsPricelist[i].goodspricelist[j].agentlevel_id
        }
      }
    }
    _that.setData({
      brands: _brandList
    });
    var _orderTypeName = ''
    if (_nowLevel.agentlevel_id === -1) {
      _orderTypeName = '1';
    } else {
      _orderTypeName = '2';
    }
    _that.setData({
      orderTypeName: _orderTypeName
    })
    //重新计算订单数量和价格
    var _outPrice = _that.data.outPrice,
      new_selBandNum = 0,
      new_totalNum = 0,
      new_totalPrice = 0;
    for (var i = 0, len = _brandList.length; i < len; ++i) {
      var child = _brandList[i].goodsAndGoodsPricelist
      var is_selBand = false
      _brandList[i].hasSelectItem = false;
      if (child && child.length>0){
        for (var j = 0, j_len = child.length; j < j_len; ++j) {
          if (child[j].num && child[j].num > 0 && child[j].goods_price > 0) {
            is_selBand = true
            _brandList[i].hasSelectItem = true
            new_totalNum = new_totalNum*1 + child[j].num*1
            new_totalPrice = new_totalPrice*1 + child[j].num * child[j].goods_price
          }
        }
      }
      if (is_selBand) {
        new_selBandNum++
      }
    }
    new_totalPrice = new_totalPrice*1 + _outPrice*1
    _that.setData({
      totalPrice: new_totalPrice,
      selBandNum: new_selBandNum,
      selGoodsNum: new_totalNum
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 弹出额外费用弹窗
   */
  showPriceModalDialog: function (e) {
    var _that = this,
      nowOutPrice = _that.data.outPrice;
    if (nowOutPrice >= 0) {
      this.setData({
        outPriceSymbol: '+'
      })
    } else {
      this.setData({
        outPriceSymbol: '-'
      })
    }
    this.setData({
      showPriceModal: true,
      modalOutPrice: Math.abs(nowOutPrice)
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
     * 隐藏额外费用弹窗
     */
  hidePriceModalDialog: function () {
    this.setData({
      showPriceModal: false
    });
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: this.data
    })
  },
  /**
   * 额外费用输入监听事件
   */
  brandOutPriceChange: function (e) {
    var _that = this,
      nowSymbol = _that.data.outPriceSymbol;
    var thisOutPrice = 0;
    if (nowSymbol == '+') {
      thisOutPrice = e.detail.value * 1
    } else {
      thisOutPrice = 0 - e.detail.value * 1
    }
    _that.setData({
      outPrice: thisOutPrice
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 切换额外费用正负
   */
  switchOurPriceSymbol: function (e) {
    var _that = this,
      nowSymbol = _that.data.outPriceSymbol,
      nowOutPrice = _that.data.outPrice;
    if (nowSymbol == '+') {
      nowSymbol = '-';
      nowOutPrice = 0 - Math.abs(nowOutPrice) * 1
    } else {
      nowSymbol = '+'
      nowOutPrice = Math.abs(nowOutPrice) * 1
    }
    _that.setData({
      outPriceSymbol: nowSymbol,
      outPrice: nowOutPrice
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 额外费用弹框确认
   */
  outPriceConfirm: function (e) {
    var _that = this,
      _brandList = _that.data.brands,
      _outPrice = _that.data.outPrice;
    var nowTotalPrice = 0;
    for (var i = 0, len = _brandList.length; i < len; ++i) {
      var child = _brandList[i].goodsAndGoodsPricelist
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num > 0) {
          nowTotalPrice = nowTotalPrice*1 + child[j].num * child[j].goods_price
        }
      }
    }
    _that.setData({
      showPriceModal: false,
      totalPrice: nowTotalPrice*1 + _outPrice*1
    })
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 切换购物车开与关
   */
  cascadeToggle: function () {
    var that = this
    if (that.data.maskVisual) {
      that.cascadeDismiss();
    } else {
      that.cascadePopup();
    }
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: that.data
    })
  },
  /**
   * 购物车打开动画
   */
  cascadePopup: function () {
    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: "linear",
      delay: 0
    });
    this.animation = animation;
    animation.translateY(400).step();
    this.setData({
      animationData: this.animation.export(),
      maskVisual: true
    });
    setTimeout(function () {
      animation.translateY(0).step()
      this.setData({
        animationData: animation.export()
      })
    }.bind(this), 100)
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: this.data
    })
  },
  /**
   * 购物车关闭动画
   */
  cascadeDismiss: function () {
    this.setData({
      maskVisual: false
    });
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: this.data
    })
  },
  /**
     * 立即下单弹窗
     */
  showOrderSureDialog: function (e) {
    var _that = this;
    if (_that.data.selGoodsNum > 0) {
      _that.setData({
        showOrderSure: true,
        maskVisual: false
      })
      //先添加当前订单信息入库
      var _brandList = _that.data.brands,
        _outPrice = _that.data.outPrice,
        _orderTypeName = _that.data.orderTypeName;
      if (_orderTypeName === '1') {
        //进货订单
        var _orderGoodsList = '';//封装接口商品数据
        for (var i in _brandList) {
          var _goods = _brandList[i].goodsAndGoodsPricelist
          for (var j in _goods) {
            if (_goods[j].num * _goods[j].goods_price > 0) {
              //进货订单商品代理级别默认传0
              _orderGoodsList += _brandList[i].brand_id + ',0,' + _goods[j].goods_id + ',' + _goods[j].num + '|'
            }
          }
        }
        _orderGoodsList = _orderGoodsList.substr(0, _orderGoodsList.length - 1)
        wx.request({
          url: WebService.HOST + '/mapi/order/insertMoreInOrder.do',
          data: {
            account_id: app.globalData.userInfo.account_id,
            order_premium: _outPrice,
            goodslist: _orderGoodsList
          },
          success: function (res) {
            if (res.data.code === 0 && res.data.order) {
              _that.setData({
                thisOrder: res.data.order
              })
              //将当前所有的数据存入缓存中
              wx.setStorage({
                key: 'indexOrderData',
                data: _that.data
              })
            }
          }
        })
      } else if (_orderTypeName === '2') {
        //出货订单
        var _orderGoodsList = '';//封装接口商品数据
        for (var i in _brandList) {
          var _goods = _brandList[i].goodsAndGoodsPricelist
          for (var j in _goods) {
            if (_goods[j].num * _goods[j].goods_price > 0) {
              //如果是成本价出售，则将代理层级id置为0
              var _agentlevel_id = _goods[j].agentlevel_id > 0 ? _goods[j].agentlevel_id : 0;
              _orderGoodsList += _brandList[i].brand_id + ',' + _agentlevel_id + ',' + _goods[j].goods_id + ',' + _goods[j].num + '|'
            }
          }
        }
        _orderGoodsList = _orderGoodsList.substr(0, _orderGoodsList.length - 1)
        wx.request({
          url: WebService.HOST + '/mapi/order/insertMoreOutOrder.do',
          data: {
            account_id: app.globalData.userInfo.account_id,
            order_premium: _outPrice,
            goodslist: _orderGoodsList
          },
          success: function (res) {
            if (res.data.code === 0 && res.data.order) {
              _that.setData({
                thisOrder: res.data.order
              })
              //将当前所有的数据存入缓存中
              wx.setStorage({
                key: 'indexOrderData',
                data: _that.data
              })
            }
          }
        })
      }
      //设置系统剪贴板内容
      wx.setClipboardData({
        data: '【番茄管家】温馨提示：主人 您的本次订单共计 ' + _that.data.selGoodsNum + ' 个商品 【请支付金额 ¥ ' + _that.data.totalPrice + ' 元 】祝您天天开心 心想事成(^_^)',
        success: function (res) {
          console.log("当前编辑订单处理完成")
        }
      })
    } else {
      wx.showToast({
        title: '当前订单无商品',
        icon: 'none',
        duration: 2000
      })
    }
  },
  /**
   * 修改订单
   */
  updateThisOrder: function (e) {
    //删除当前订单，页面数据不动
    var _that = this,
      _thisOrder = _that.data.thisOrder;
    wx.request({
      url: WebService.HOST + '/mapi/order/deleteOrder.do',
      data: {
        account_id: app.globalData.userInfo.account_id,
        order_id: _thisOrder.order_id
      },
      success: function (res) {
        if (res.data.code == 0) {
          console.log("删除当前编辑订单成功！")
        }
      }
    })
    _that.setData({
      showOrderSure: false,
      thisOrder: {}
    });
    //将当前所有的数据存入缓存中
    wx.setStorage({
      key: 'indexOrderData',
      data: _that.data
    })
  },
  /**
   * 确定并进行下一单
   */
  sureNextOrder: function (e) {
    wx.showLoading({
      title: '订单处理中...',
      mask: true
    })
    //先清除缓存
    wx.removeStorage({
      key: 'indexOrderData',
      success: function (res) {
        wx.hideLoading();
      }
    })
    this.setData({
      showOrderSure: false
    });
    this.clearEmpty();
  },
  /**
     * 隐藏立即下单弹窗
     */
  hideOrderSureDialog: function () {
    this.setData({
      showOrderSure: false
    });
  },
  /**
   * 无商品页，点击添加品牌按钮事件
   */
  gotoAddBrand: function () {
    wx.navigateTo({
      url: '/pages/shop/shopOperate/shopOperate?brand_id='
    })
  },
  /**
   * 学习如何使用，跳转到使用指南小程序
   */
  gotoLearnWechatApp: function () {
    wx.navigateToMiniProgram({
      appId: 'wx8849c093e8ee7954',
      success(res) {
        // 打开成功
      }
    })
  }
})
