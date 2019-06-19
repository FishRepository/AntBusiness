// pages/report/report.js
import * as echarts from '../../ec-canvas/echarts';

function initChart(canvas, width, height) {
  const chart = echarts.init(canvas, null, {
    width: width,
    height: height
  });
  canvas.setChart(chart);

  var option = {
    color: ["#F50057"],
    grid: {
      left: "5%",
      right: "10%",
      bottom: "5%",
      containLabel: true,
      top: "20%"
    },
    tooltip: {
      show: true,
      trigger: 'axis',
      position: ['50%', '50%'],
      axisPointer:{
        type:'shadow'
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: ['07-01', '07-02', '07-03', '07-04', '07-05', '07-06', '07-07'],
      axisLabel: {
        color: '#BDBDBD',
        fontFamily: 'Microsoft YaHei',
        fontSize: 10
      },
      axisLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      splitArea: {
        show: false
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      name: '七日收益趋势(元)',
      nameTextStyle: {
        color: '#BDBDBD',
        fontFamily: 'Microsoft YaHei',
        fontWeight: 'bold',
        padding: [10, 0, 10, 20]
      },
      type: 'value',
      axisLabel: {
        color: '#BDBDBD',
        fontFamily: 'Microsoft YaHei',
        fontSize: 10
      },
      axisLine: {
        show: false
      },
      axisTick: {
        show: false
      },
      splitArea: {
        show: false
      },
      splitLine: {
        show: false
      }
    },
    series: [{
      type: 'line',
      smooth: true,
      showSymbol:false,
      lineStyle: {
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [{
            offset: 0,
            color: '#e64663'
          }, {
            offset: 1,
            color: '#fbdbdb'
          }],
          global: false
        }
      },
      areaStyle:{
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,  
          y2: 1,
          colorStops: [{
            offset: 0,
            color: '#e64663'
          }, {
            offset: 1,
              color: '#fff'
          }],
          global: false
        }
      },
      data: [2000, 2500, 2600, 3000, 3100, 3200, 3300, 2400, 3600, 3300, 3500, 3400, 3500, 3700, 3900, 4000]
    }]
  };
  chart.setOption(option);
  return chart;
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    todayData: {
      xiaoshou: '1785.00',
      shouyi: '3245.00'
    },
    star: '******',
    see: true,
    leiji: {
      xiaoshou: '57,820.00',
      shouyi: '22,463.00'
    },
    users: [{
        name: '王小明',
        url: 'http://p1.music.126.net/Jxp3emWQRhmrTu351qdIog==/109951163556000475.jpg?param=180y180',
        rank: 1
      },
      {
        name: '王大明',
        url: 'http://p2.music.126.net/wPxtWY5brcMVEhrb1_g0-A==/109951163005504470.jpg?param=180y180',
        rank: 2
      },
      {
        name: '王二明',
        url: 'http://p1.music.126.net/H-Pmcv52PPD9bNAyc2oeog==/109951163899937962.jpg?param=180y180',
        rank: 3
      }
    ],
    pros: [{
        name: '面膜',
        brand: 'A.T',
        num: 20,
        rank: 1
      },
      {
        name: '肥皂',
        brand: 'YSL',
        num: 15,
        rank: 2
      },
      {
        name: '牙膏',
        brand: 'A.T',
        num: 10,
        rank: 3
      }
    ],
    words: [{
        color: 'red',
        ch: '今天的开始，就是日后的成功。O(∩_∩)O',
        en: 'Tomorrow will be better'
      },
      {
        color: 'blue',
        ch: '今天的开始，就是日后的成功。O(∩_∩)O',
        en: 'Tomorrow will be better'
      },
      {
        color: 'red',
        ch: '今天的开始，就是日后的成功。O(∩_∩)O',
        en: 'Tomorrow will be better'
      }
    ],
    ec: {
      onInit: initChart
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  switchSee: function() {
    var _that = this
    if (_that.data.see) {
      _that.setData({
        see: false
      })
    } else {
      _that.setData({
        see: true
      })
    }
  },
})