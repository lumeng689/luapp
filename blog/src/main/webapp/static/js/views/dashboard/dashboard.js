/**
 * Created by lum on 2015/7/15.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    'artDialog',
    'templates',
    'echarts',
    'echarts/chart/bar',
    'echarts/chart/line',
    'echarts/chart/map',
    'jquery_validate'
], function ($, _, Backbone, Marionette, juicer, artDialog, Templates, ec) {
    'use strict';

    var dashboard = Marionette.ItemView.extend({
        //el: "#footer",
        template: Templates.dashboard.index,
        events: {
            'click .btn.click': 'clickBtn',
            'click .btn.ret': 'retBtn'
        },
        ui: {
            line1: '#line1'
        },
        initialize: function () {
            //var myChart = echarts.init(this.ui.line1);
            //var option = {"EXPORT_PATH": "/tmp/echarts/","VIEW": false,"calculable": true,"toolbox": {"feature": {"mark": {"show": true,"title": {"markUndo": "删除辅助线","markClear": "清空辅助线","mark": "辅助线开关"},"lineStyle": {"color": "#1e90ff","type": "dashed","width": 2}},"dataView": {"show": true,"title": "数据视图","readOnly": false,"lang": ["数据视图","关闭","刷新"]},"magicType": {"show": true,"title": {"bar": "柱形图切换","stack": "堆积","tiled": "平铺","line": "折线图切换"},"type": ["line","bar"]},"restore": {"show": true,"title": "还原"},"saveAsImage": {"show": true,"title": "保存为图片","type": "png","lang": ["点击保存"]}},"show": true},"tooltip": {"trigger": "axis","formatter": "Temperature : <br/>{b}km : {c}°C"},"legend": {"data": ["高度(km)与气温(°C)变化关系"]},"xAxis": [{"type": "value","axisLabel": {"formatter": "{value} °C"}}],"yAxis": [{"boundaryGap": false,"type": "category","axisLine": {"onZero": false},"axisLabel": {"formatter": "{value} km"},"data": [0,10,20,30,40,50,60,70,80]}],"series": [{"smooth": true,"name": "高度(km)与气温(°C)变化关系","type": "line","itemStyle": {"normal": {"lineStyle": {"shadowColor": "rgba(0,0,0,0.4)"}}},"data": [15,-50,-56.5,-46.5,-22.1,-2.5,-27.7,-55.7,-76.5]}]};
            //myChart.setOption(option);
            //--- 折柱 ---

            this.on('attach', this.renderChart);
        },
        renderChart: function () {
            $("#form1").validate({
                submitHandler: function () {
                    alert("submitted!");
                }
            });
            var myChartLine = ec.init(this.$el.find('#line')[0]);
            myChartLine.setOption({
                title: {
                    text: '未来一周气温变化',
                    subtext: '纯属虚构'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['最高气温', '最低气温']
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} °C'
                        }
                    }
                ],
                series: [
                    {
                        name: '最高气温',
                        type: 'line',
                        data: [11, 11, 15, 13, 12, 13, 10],
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        name: '最低气温',
                        type: 'line',
                        data: [1, -2, 2, 5, 3, 2, 0],
                        markPoint: {
                            data: [
                                {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            });
            //--- 折柱 ---
            //var myChart = ec.init(document.getElementById('main2'));
            var myChartBar = ec.init(this.$el.find('#bar')[0]);
            myChartBar.setOption({
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['蒸发量', '降水量']
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        splitArea: {show: true}
                    }
                ],
                series: [
                    {
                        name: '蒸发量',
                        type: 'bar',
                        data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                    },
                    {
                        name: '降水量',
                        type: 'bar',
                        data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                    }
                ]
            });

            // --- 地图 ---
            //var myChart2 = ec.init(document.getElementById('mainMap'));
            var myChartMap = ec.init(this.$el.find('#map')[0]);
            myChartMap.setOption({
                tooltip: {
                    trigger: 'item',
                    formatter: '{b}'
                },
                series: [
                    {
                        name: '中国',
                        type: 'map',
                        mapType: 'china',
                        selectedMode: 'multiple',
                        itemStyle: {
                            normal: {label: {show: true}},
                            emphasis: {label: {show: true}}
                        },
                        data: [
                            {name: '广东', selected: true}
                        ]
                    }
                ]
            });

        },
        clickBtn: function () {
            var d = dialog({
                title: '欢迎',
                content: '欢迎使用 artDialog 对话框组件！'
            });
            d.showModal();
        },
        retBtn: function () {
            debugger;
            var d = dialog({
                title: '消息',
                content: $('#formDiv')[0],
                ok: function () {
                    var value = $('#property-returnValue-demo').val();
                    this.close(value);
                    this.remove();
                }
            });
            d.addEventListener('close', function () {
                alert(this.returnValue);
            });
            d.show();
        }
    });

    return dashboard;
});