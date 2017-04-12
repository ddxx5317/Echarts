<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>


<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/public/js/jquery.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/public/js/jquery.min.js"></script>
	 --%>
 <script type="text/javascript"
	src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>

<script type="text/javascript"
	src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	 

<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
var myChart;
var eCharts;

// 路径配置
require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});

require(
        [
            'echarts',
            'echarts/chart/line',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ], DrawEChart
        );

function DrawEChart(ec){
	eCharts=ec;
	myChart = eCharts.init(document.getElementById('main'));
	
	myChart.showLoading({
		text:"图片正在努力加载中"
	});
	
	options = {
		    title : {
		        text: '未来一周气温变化',
		        subtext: '纯属虚构'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['最高气温']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['1','2','3','4','5','6','7']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} °C'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'最高气温',
		            type:'line',
		            data:[11, 22, 33, 44, 55, 33, 44],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	
	
	myChart.setOption(options);
	myChart.hideLoading();
	getChartData();
}



</script>

<script type="text/javascript">
function getChartData(){
	var options=myChart.getOption();
	$.ajax({
		type:"post",
		async:false,
		url:"<%=basePath %>echarts/line_data",
		data:{},
		dataType:"json",
		success:function(result){
			if(""==result){
				alert("数据为空");
			}else {
				options.legend.data=result.legend;
				options.xAxis[0].data=result.category;
				options.series[0].data=result.series[0].data;
				
				myChart.hideLoading();
				myChart.setOption(options);
			}
			
		},
		error:function(){
			alert("不好意思！图表没加载出来！");
			myChart.hideLoading();
		}
	});	

}

</script>


</head>
<body>

	<div id="main" style="height: 400px;"></div>
	
	
	<div></div>

</body>
</html>