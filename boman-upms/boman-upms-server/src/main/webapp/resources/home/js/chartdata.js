
//上架数量前六  饼图
$(function(){
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('con-one'));
		option = {
		    title : {
		        text: '统计',
		        //subtext: '上架数量前六',
		        x:'center',
		        textStyle:{
		        	fontSize:12,
		        	color:'#3e576f'
		        },
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'horizontal',
		        x:'center',
		        y:'bottom',
//		        borderColor:'#909090',
//		        borderWidth:1,
//		        borderRadius:5,
//		        padding:6,
		        data: ['010102','1122','010101','YL001','X67657','020202']
		    },
		    series : [
		        {
		            name: '移动显示',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            data:[
		                {value:2147483647, name:'010102'},
		                {value:233457447, name:'1122'},
		                {value:24032256, name:'010101'},
		                {value:103227, name:'YL001'},
		                {value:10095, name:'X67657'},
		                {value:8079, name:'020202'}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
    
    
//  近七日下架数  折线图
    var cenChart = echarts.init(document.getElementById('con-two'));
    option = {
    	title : {
		        text: '统计',
		        //subtext: '上架数量前六',
		        x:'center',
		        textStyle:{
		        	fontSize:12,
		        	color:'#3e576f'
		        },
		    },
	tooltip:{
		trigger:'axis',
		axisPointer:{
			type:'line',
			label:'cross',
			show:true
		}
	},
    xAxis: {
        type: 'category',
        axisLine:{
        	lineStyle:{
        		color:'#666666'
        	}
        },
        axisLabel:{
         	rotate:20,
        	interval:0
        },
        data: ['2019-04-30', '2019-05-06', '2019-05-07', '2019-05-08', '2019-05-10', '2019-05-13', '2019-05-14'],
    },
    yAxis: {
        type: 'value',
        min:-1,
        max:3,
        splitNumber:4,
        axisLine:{
        	lineStyle:{
        		color:'#666666'
        	}
        },
        axisLabel:{
        	formatter:'{value}k'
        }
    },
    legend: {
		        orient: 'horizontal',
		        x:'center',
		        y:'bottom',
		        data:['近七日下架数量']
		    },
	grid:{
		top:'10%',
		left:'2%',
		right:'2%',
		bottom:'12%',
		containLabel:true
	},
	legend:{
		orient:'horizontal',
		x:'center',
		y:'bottom',
		data:['近七日下架数']
	},
    series: [{
    	name:'近七日下架数',
        data: [2.3, 0, 0, 0, 0, 0, 0.1],
        type: 'line',
        symbolSize:6,
        symbol:'circle',
        itemStyle:{
        	normal:{
        		color:'#4572a7',
        		label:{
        			show:false
        		},
        		lineStyle:{
        			width:3,
        			color:'#4572a7'
        		}
        	}
        }
    }]
};
	cenChart.setOption(option);	


//下架数量前六 柱状图
var rigChart = echarts.init(document.getElementById('con-three'));

//app.title = '坐标轴刻度与标签对齐';

option = {
	title : {
		        text: '统计',
		        //subtext: '上架数量前六',
		        x:'center',
		        textStyle:{
		        	fontSize:12,
		        	color:'#3e576f'
		        },
		    },
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
    	top:'10%',
        left: '2%',
        right: '4%',
        bottom: '12%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['010102', '020202', '000126', '000012', '012030', '392432'],
            axisTick: {
                alignWithLabel: true
            },
            axisLabel:{
	        	interval:0
            },
            axisLine:{
            	lineStyle:{
            		color:'#c0d0e0'
            	}
            }
        }
    ],
    
    yAxis: {
        type: 'value',
        min:0,
        max:10000,
        interval:2500,
        splitNumber:4,
        axisLine:{
        	lineStyle:{
        		color:'#666666'
        	}
        }
    },
    legend:{
			orient: 'horizontal',
	        x:'center',
	        y:'bottom',
	        data:['下架数量前六']
    },

    series : [
        {
            name:'下架数量前六',
            type:'bar',
            barWidth: '60%',
            data:[7776, 200, 161, 35, 12, 8]
        }
    ]
};
	rigChart.setOption(option);		




    })