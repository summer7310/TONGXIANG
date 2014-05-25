<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>变压器属性</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list"> 
		<tr><th colspan="2">变压器基本信息</th></tr>
		<tr> 
			<td width="80px">名 称</td>
			<td width="340px"><s:textfield theme="simple" cssClass="num i290" value="%{result.name}"/></td> 
			<td width="80px">运行状态</td> 
			<td width="300px">
				<select>
					<option value ="">1000V</option>
					<option value ="2">2000V</option>
					<option value="">4000V</option>
					<option value="">20000V</option>
				</select>
			</td> 
		</tr> 
		<tr> 
			<td>经 度</td>
			<td><s:textfield theme="simple" cssClass="num i290" id="lon" value="%{result.longitude}"/></td>
			<td>纬 度</td>
			<td><s:textfield theme="simple" cssClass="num i290" id="lat" value="%{result.latitude}"/></td>
		</tr>
		<tr>
			<td>高 程(m)</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.elevation}"/></td>
			<td>运行编号</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.run_code}"/></td>
		</tr>
		<tr>
			<td>所属线路</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.name}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.asset_code}"/></td>
		</tr>
	</table>
	<div class="clear"></div>
	<table class="Tinput_list"> 
		<tr><th colspan="2">设备信息</th></tr>
		<tr> 
			<td width="80px">生产厂家</td>
			<td width="340px"><s:textfield theme="simple" cssClass="i290" value="%{result.manufacturer}"/></td> 
			<td width="80px">出厂编号</td> 
			<td width="300px">
				<s:textfield theme="simple" cssClass="i290" value="%{result.manufacture_code}"/>
			</td> 
		</tr> 
		<tr>
			<td>出厂日期</td>
			<td><input type="text" class="idata i290" id="data1" value="%{result.manufacture_date}"/></td>
			<script type="text/javascript">
			$(function(){
			    $('#data1').calendar();
			});
			</script>
			<td>温控器型号</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.thermostat_model}"/></td>
		</tr>
		<tr>
			<td>运行单位</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.operating_unit}"/></td>
			<td>投运日期</td>
			<td><input type="text" class="idata i290" id="data2" value="%{result.commissioning_date}"/></td>
			<script type="text/javascript">
			$(function(){
			    $('#data2').calendar();
			});
			</script>
		</tr>
		<tr>
			<td>站 址</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.station_address}"/></td>
			<td>资产单位</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.owner}"/></td>
		</tr>
	</table>
	<div class="clear"></div>
	<table class="Tinput_list"> 
		<tr><th colspan="2">电气信息</th></tr>
		<tr> 
			<td width="80px">接地电阻</td>
			<td width="340px"><s:textfield theme="simple" cssClass="i290" value="%{result.ground_resistance}"/></td> 
			<td width="80px">类 型</td> 
			<td width="300px">
				<s:textfield theme="simple" cssClass="i290" value="%{result.location_type}"/>
			</td> 
		</tr> 
		<tr>
			<td>相 数</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.phase_count}"/></td>
			<td>绝缘介质</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.dielectric}"/></td>
		</tr>
		<tr>
			<td>额定容量</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.rated_capacity}"/></td>
			<td>阻抗电压</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.impedance_voltage}"/></td>
		</tr>
		<tr>
			<td>空载电流</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.un_load_current}"/></td>
			<td>短路损耗</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.short_circuit_loss}"/></td>
		</tr>
		<tr>
			<td>空载损耗</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.un_load_loss}"/></td>
			<td>接线组别</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.wiring_group}"/></td>
		</tr>
		<tr>
			<td>电压比</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.voltage_ratio}"/></td>
			<td>高压侧额电</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.high_pre_current}"/></td>
		</tr>
		<tr>
			<td>电压比</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.voltage_ratio}"/></td>
			<td>高压侧额电</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.high_pre_current}"/></td>
		</tr>
		<tr>
			<td>低压侧额电</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.low_pre_current}"/></td>
			<td>油 号</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.oil_number}"/></td>
		</tr>
		<tr>
			<td>绝缘等级</td>
			<td><s:textfield theme="simple" cssClass="i290" value="%{result.insuol}"/></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
	<script type="text/javascript">
Plugins.check.form();
</script>
</div>

</body>
</html>