<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>

<script type="text/javascript"
	src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	 
</head>
<body>

	<div style="padding:10px;">  
    <div id="psLine" style="height:300px;width:600px;position:absolute;left:0px;top:100px;"></div>  
    
    <div>asdfasdf還是了卡收費</div>
    
    
    
     <div style="padding:10px;">  
            <div id="psLine1" style="height:300px;width:600px;position:absolute;left:0px;top:450px;"></div>  
           </div>  
          
    
    
</div>  
</body>  
<script src="<%=request.getContextPath() %>/js/echarts-all.js" ></script>  
<script type="text/javascript">  
    //图表  
    var psLineChar = echarts.init(document.getElementById('psLine'));  
  
    //查询  
    function loadDrugs() {  
        psLineChar.clear();  
        psLineChar.showLoading({text: '正在努力的读取数据中...'});  
        $.getJSON('/Echarts/echart/select', function (data) {  
        	alert("asa:"+"---"+data);
            if (data!=null) {  
            	alert(data); 
                psLineChar.setOption(data, true);  
                psLineChar.hideLoading(); 
            } else {  
                alert('暂时没有数据');  
            }  
        });  
    }  
    //载入图表  
    loadDrugs();  
</script>  

<script type="text/javascript">
//图表  
var psLineChar1 = echarts.init(document.getElementById('psLine1'));  

//查询  
function loadDrugs1() {  
    psLineChar1.clear();  
    psLineChar1.showLoading({text: '正在努力的读取数据中...'});  
    $.getJSON('/Echarts/echart/getLineChart', function (data) {  
        console.log(psLineChar1);  
        console.log(data);  
           psLineChar1.setOption(data, true);  
           psLineChar1.hideLoading();  
       });  
}  
//载入图表  
loadDrugs1();  



</script>



</body>
</html>