package com.gcy.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcy.ssm.service.PatrolPlanServiceImpl;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import com.google.gson.Gson;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/echart")
public class EchaertController {

	@Autowired
	PatrolPlanServiceImpl patrolPlanService;

	@RequestMapping("/select")
	@ResponseBody
	public String select(HttpServletResponse response) throws UnsupportedEncodingException {
		
		response.setContentType("textml;charset=utf-8");
		List<Map<String, Object>> list = patrolPlanService.select();
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return -1;
			}
		});

		//Option
		Option option = new Option();

		option.title("设备故障").tooltip(Trigger.axis).legend("故障次数");
		option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));
		CategoryAxis category = new CategoryAxis();
		Bar bar = new Bar("故障次数");
		Pie pie = new Pie("故障次数");
		for (Map<String, Object> objectMap : list) {
			category.data(objectMap.get("trouble_direct"));

			System.out.println(objectMap.get("trouble_direct") + ":" + objectMap.get("trouble_num"));

			bar.data(objectMap.get("trouble_num"));
			pie.data(new PieData(objectMap.get("trouble_direct").toString(), objectMap.get("trouble_num")));
		}
		option.yAxis(category);
		pie.center(900, 380).radius(100);
		option.series(bar, pie);
		option.grid().x(180);
		
		
		PrintWriter out;
		Gson gson = new Gson();
		String json=gson.toJson(option);//转换为json，使用springmvc直接返回map即可
		try {
			out = response.getWriter();
			// 利用Json插件将Array转换成Json格式
			
			out.write(json);
			// 释放资源
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
		/*Gson gson = new Gson();
		
		 String json=new String(gson.toJson(option).getBytes("UTF-8"), "utf-8");*/
		
		/* JSONObject object = JSONObject.fromObject(option); */

		/*return object.toString();*/
	}

	@RequestMapping("/select1")
	@ResponseBody
	public Option select1() {

		List<Map<String, Object>> list = patrolPlanService.select();
		
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return -1;
			}
		});

		
		Option option = new Option();
		option.legend("法司法斯蒂芬");

		option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar),
				Tool.restore, Tool.saveAsImage);

		option.calculable(true);
		option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}掳C");

		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value} .C");
		option.xAxis(valueAxis);

		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.axisLine().onZero(false);
		categoryAxis.axisLabel().formatter("{value} km");
		categoryAxis.boundaryGap(false);
		categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
		option.yAxis(categoryAxis);

		Line line = new Line();
		line.smooth(true).name("楂樺害(km)涓庢皵娓�(掳C)鍙樺寲鍏崇郴").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5)
				.itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
		option.series(line);

		return option;
	}

	@RequestMapping("/index")
	public String index() {
		return "echarts";
	}

	@RequestMapping("/index1")
	public String index1() {
		return "NewFile";
	}

	@RequestMapping("/er")
	public Option test1() {
		Option option = new Option();
		option.legend("楂樺害(km)涓庢皵娓�(掳C)鍙樺寲鍏崇郴");

		option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar),
				Tool.restore, Tool.saveAsImage);

		option.calculable(true);
		option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}掳C");

		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value} 掳C");
		option.xAxis(valueAxis);

		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.axisLine().onZero(false);
		categoryAxis.axisLabel().formatter("{value} km");
		categoryAxis.boundaryGap(false);
		categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
		option.yAxis(categoryAxis);

		Line line = new Line();
		line.smooth(true).name("楂樺害(km)涓庢皵娓�(掳C)鍙樺寲鍏崇郴").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5)
				.itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
		option.series(line);

		return option;

	}

	@RequestMapping(value = "/getLineChart", method = RequestMethod.GET)
	@ResponseBody
	public String getLineChart(HttpServletRequest request, HttpSession session) {

		Option option = new Option();
		option.toolbox().x("right").show(true).feature(Tool.dataView,
				new MagicType(Magic.line, Magic.bar, Magic.pie).show(true), Tool.restore, Tool.saveAsImage,
				Tool.dataZoom);
		option.title("历年变化情况");
		option.tooltip().trigger(Trigger.axis);
		option.calculable(true);
		List<String> xDataList = new ArrayList<String>();
		List<Integer> dDataList = new ArrayList<Integer>();
		xDataList.add("2011");
		xDataList.add("2012");
		xDataList.add("2013");
		dDataList.add(32);
		dDataList.add(2);
		dDataList.add(12);
		int xsize = xDataList.size();
		String[] xArray = (String[]) xDataList.toArray(new String[xsize]);
		int dsize = dDataList.size();
		Integer[] dArray = (Integer[]) dDataList.toArray(new Integer[dsize]);
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.boundaryGap(true);
		AxisLabel axisLabel = new AxisLabel();
		axisLabel.setRotate(120);
		categoryAxis.data(xArray).setAxisLabel(axisLabel);
		option.xAxis(categoryAxis);
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value} 人");
		option.yAxis(valueAxis);
		Line line1 = new Line();
		line1.data(dArray);
		line1.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));
		ItemStyle colorStyle = new ItemStyle();
		colorStyle.normal().color("#E87C25");
		Bar bar = new Bar("数据");
		bar.setBarWidth(50);
		bar.setItemStyle(colorStyle);
		bar.data(2, 11, 15, 3);
		bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));
		bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));
		bar.markPoint();
		option.series(line1, bar);
		JSONArray json = JSONArray.fromObject(option);
		return json.toString();
	}
	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/test2")
	@ResponseBody
	public String test2(HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=gbk");
		PrintWriter out = resp.getWriter();
		List category = new ArrayList();//显示横坐标数据
		category.add("周一");
		category.add("周二");
		category.add("周三");
		category.add("周四");
		category.add("周五");
		category.add("周六");
		category.add("周日");
		List series = new ArrayList();//纵坐标设置，line表示以折线显示，bar是柱状
		Map map = new HashMap(0);
		map.put("legend", "图例");
		map.put("category", category);
		map.put("series", series);
		Gson gson = new Gson();
		String json=gson.toJson(map);//转换为json，使用springmvc直接返回map即可
		out.write(json);
		return json;
		}
}
