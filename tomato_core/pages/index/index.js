//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    brands: [{
      name: 'A.T',
      items: [{
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }, {
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }, {
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }]
    },
    {
      name: '香奈儿',
      items: [{
        proName: '香奈儿妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '香奈儿妆品【小】',
        price: 599.00,
        num: 0
      },
      {
        proName: '香奈儿妆品【小】',
        price: 599.00,
        num: 0
      }]
    },
    {
      name: '施华洛世奇水晶',
      items: [{
        proName: '施华洛世奇水晶妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '施华洛世奇水晶妆品【小】',
        price: 399.00,
        num: 0
      },
      {
        proName: '香奈儿妆品【小】',
        price: 599.00,
        num: 0
      }]
    },
    {
      name: '雅诗兰黛',
      items: [{
        proName: '雅诗兰黛妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '雅诗兰黛妆品【小】',
        price: 499.00,
        num: 0
      }]
    },
    {
      name: '格斯蓝雅',
      items: [{
        proName: '格斯蓝雅妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '格斯蓝雅妆品【小】',
        price: 499.00,
        num: 0
      }]
    },
    {
      name: 'Mircel',
      items: [{
        proName: '星辰化妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 599.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 999.00,
        num: 0
      }, {
        proName: '星辰化妆品【小】',
        price: 699.00,
        num: 0
      }]
    },
    {
      name: '格斯蓝雅',
      items: [{
        proName: '格斯蓝雅妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '格斯蓝雅妆品【小】',
        price: 499.00,
        num: 0
      }]
    },
    {
      name: '格斯蓝雅',
      items: [{
        proName: '格斯蓝雅妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '格斯蓝雅妆品【小】',
        price: 499.00,
        num: 0
      }]
    },
    {
      name: '格斯蓝雅',
      items: [{
        proName: '格斯蓝雅妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '格斯蓝雅妆品【小】',
        price: 499.00,
        num: 0
      }]
    },
    {
      name: '格斯蓝雅',
      items: [{
        proName: '格斯蓝雅妆品【中】',
        price: 999.00,
        num: 0
      }, {
        proName: '格斯蓝雅妆品【小】',
        price: 499.00,
        num: 0
      }]
    }],
    selIdx: 0,
    totalPrice: 0.00,
    selBandNum: 0,
    totalNum:0
  },
  onLoad: function () {
    var _that = this
    var list = _that.data.brands
    var t_price = 0;
    var t_selbanNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var c_list = list[i].items
      var is_selBand = false
      for (var j = 0, j_len = c_list.length; j < j_len; ++j) {
        var num = c_list[j].num
        var price = c_list[j].price
        if (num > 0) {
          is_selBand = true
          t_price += num * price
        }
      }
      if (is_selBand) {
        t_selbanNum++
      }
    }
    _that.setData({
      totalPrice: t_price
    })
  },
  swapTabTable: function (e) {
    var selIndex = e.currentTarget.id
    this.setData({
      selIdx: selIndex
    })
  },
  subProNum: function (e) {
    var _that = this
    var selidx = _that.data.selIdx
    var idx = e.currentTarget.id
    var list = _that.data.brands
    var c_list = list[selidx].items
    var alNum = c_list[idx].num
    c_list[idx].num = alNum - 1
    var new_totalNum = _that.data.totalNum-1;
    var newT_price = _that.data.totalPrice - c_list[idx].price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].items
      var is_selBand = false
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num > 0) {
          is_selBand = true
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
      totalNum:new_totalNum
    })

  },
  addProNum: function (e) {
    var _that = this
    var selidx = _that.data.selIdx
    var idx = e.currentTarget.id
    var list = _that.data.brands
    var c_list = list[selidx].items
    var alNum = c_list[idx].num
    c_list[idx].num = alNum + 1
    var new_totalNum = _that.data.totalNum +1;
    var newT_price = _that.data.totalPrice + c_list[idx].price
    var new_selBandNum = 0;
    for (var i = 0, len = list.length; i < len; ++i) {
      var child = list[i].items
      var is_selBand = false
      for (var j = 0, j_len = child.length; j < j_len; ++j) {
        if (child[j].num > 0) {
          is_selBand = true
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
      totalNum: new_totalNum
    })
  }
})
