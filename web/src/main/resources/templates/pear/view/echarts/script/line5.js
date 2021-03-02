layui.use(['echarts'], function () {
    let echarts = layui.echarts;

    var line5 = echarts.init(document.getElementById('line5'));

    const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']

    option = {
        backgroundColor: '#fff',
        title: {
            text: "告警数",
            left: "18px",
            top: "0",
            textStyle: {
                color: "#999",
                fontSize: 12,
                fontWeight: '400'
            }
        },
        color: ['#73A0FA', '#73DEB3', '#FFB761'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                },
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        legend: {
            data: ['上周', '本周'],
            orient: 'horizontal',
            icon: "rect",
            show: true,
            left: 20,
            top: 25,
        },
        xAxis: {
            type: 'category',
            data: ['爱立信端局', '中兴端局', '爱立信HSS', '中兴HSS', '华为HSS', '华为智能网', '中兴VIMS'],
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                interval:0
            }
        },
        yAxis: {
            type: 'value',
            gridIndex: 0,
            // max: max_value>=100? max_value + 100: max_value+10,
            // max: max_value > 100 ? max_value * 2 : max_value + 10,
            // interval: 10,
            // nameLocation: "center",
            axisLabel: {
                color: '#999',
                textStyle: {
                    fontSize: 12
                },
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#F3F4F4'
                }
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
        },
        grid: {top: '55%',containLabel: true},
        series: [{
            name: '上周',
            type: 'line',
            smooth: true,
            data: [1800, 1000, 2000, 1000, 500, 100, 1200]
        },
            {
                name: '本周',
                type: 'line',
                smooth: true,
                data: [1700, 999, 1100, 899, 199, 99, 1000]
            },
            //这个是饼图
            {
                type: 'pie',
                id: 'pie',
                radius: '40%',
                center: ['50%', '25%'],
                data: [{
                    value: 335,
                    name: "Apple"
                }, {
                    value: 310,
                    name: "Grapes"
                }, {
                    value: 234,
                    name: "Pineapples"
                }, {
                    value: 135,
                    name: "Oranges"
                }, {
                    value: 1548,
                    name: "Bananas"
                }]
            }
        ]
    };
    // line5.on('updateAxisPointer', function (event) {
    //     var xAxisInfo = event.axesInfo[0];
    //     if (xAxisInfo) {
    //         var dimension = xAxisInfo.value + 1;
    //         line5.setOption({
    //             series: {
    //                 id: 'pie'
    //             }
    //         });
    //     }
    // });
    line5.setOption(option);


    window.onresize = function () {
        line5.resize();
    }
})
