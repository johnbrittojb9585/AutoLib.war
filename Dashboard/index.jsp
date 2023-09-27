<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.io.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<body style="background-color:white;">
<form method="get" action="./DashboardServlet">
<h2>Dashboard</h2>
<table width="70%" height="100%" align="center">
<tr><td><canvas id="myChart" style="width:50%;max-width:100%"></canvas></td></tr>
<tr>
</table>

<table width="70%" height="60%" align="center">
<tr><td><canvas id="myChart1" style="width:50%;max-width:100%"></canvas></td></tr>
</table>



<table width="50%" height="100%" align="center">
<tr>
<td><canvas id="deptwiseStat"></canvas></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><canvas id="subjectWiseStat"></canvas></td>
</tr>
</table>

<table width="70%" height="100%" align="center">
<tr>
<td><canvas id="gateEntryOneMonth" style="width:100%;max-width:100%"></canvas></td>
</tr>
</table>


<table width="70%" height="100%" align="center">
<tr>
<td><canvas id="opacChartLastOneMonth" style="width:100%;max-width:100%"></canvas></td>
</tr>
</table>


<input type="hidden" name="flag">


 <!-- <canvas id="myChart" style="width:100%;max-width:1050px"></canvas>
<td><canvas id="myChart1" style="width:70%;max-width:350px"></canvas>
<td><canvas id="myChart2" style="width:100%;max-width:600px"></canvas>
<canvas id="myChart3" style="width:100%;max-width:600px"></canvas>
 
<input type="hidden" name="flag">-->


<script>
<%
Map map = (Map) request.getAttribute("dashboard");
ArrayList listx = new ArrayList();
ArrayList listy = new ArrayList();
ArrayList memList1 = new ArrayList();
ArrayList memList2 = new ArrayList();
ArrayList oneMonGateX = new ArrayList();
ArrayList oneMonGateY = new ArrayList();
ArrayList oneMonOpacX = new ArrayList();
ArrayList oneMonOpacY = new ArrayList();

ArrayList depWiseStatX = new ArrayList();
ArrayList depWiseStatY = new ArrayList();
ArrayList subjWiseStatX = new ArrayList();
ArrayList subjWiseStatY = new ArrayList();





memList1 =(ArrayList) map.get("memberGraphx");
memList2 =(ArrayList) map.get("memberGraphy");
listx =(ArrayList) map.get("documentGraphx");
listy =(ArrayList) map.get("documentGraphy");
oneMonGateX =(ArrayList) map.get("lastOneMonthGraphGateX");
oneMonGateY =(ArrayList) map.get("lastOneMonthGraphGateY");
oneMonOpacX =(ArrayList) map.get("lastOneMonthGraphOpacX");
oneMonOpacY =(ArrayList) map.get("lastOneMonthGraphOpacY");

// Statistics
depWiseStatX =(ArrayList) map.get("deptWiseStatisticsGraphX");
depWiseStatY =(ArrayList) map.get("deptWiseStatisticsGraphY");
subjWiseStatX =(ArrayList) map.get("subjWiseStatisticsGraphX");
subjWiseStatY =(ArrayList) map.get("subjWiseStatisticsGraphY");





Gson gsonObj = new Gson();
String dataPoints = gsonObj.toJson(listx);
String dataPoint2 = gsonObj.toJson(listy);
String dataPoint3 = gsonObj.toJson(memList1);
String dataPoint4 = gsonObj.toJson(memList2);
String oneMonGateJsonX = gsonObj.toJson(oneMonGateX);
String oneMonGateJsonY = gsonObj.toJson(oneMonGateY);
String oneMonOpacJsonX = gsonObj.toJson(oneMonOpacX);
String oneMonOpacJsonY = gsonObj.toJson(oneMonOpacY);

String depWiseStatJsonX = gsonObj.toJson(depWiseStatX);
String depWiseStatJsonY = gsonObj.toJson(depWiseStatY);
String subjWiseStatJsonX = gsonObj.toJson(subjWiseStatX);
String subjWiseStatJsonY = gsonObj.toJson(subjWiseStatY);



System.out.println("oneMonOpacJsonX::::::::"+oneMonOpacJsonX);
System.out.println("oneMonOpacJsonY::::::::"+oneMonOpacJsonY);

%>

var valmem1 = javaArrayToJsArray(<%=dataPoint3%>);
var valmem2 = javaArrayToJsArray(<%=dataPoint4%>);

var barColors44 = [
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29",
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29",
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29"
];

new Chart("myChart", {
  type: "bar",
  data: {
    labels: valmem1,
    datasets: [{
      backgroundColor: barColors44,
      data: valmem2
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "Members Departmentwise",
      fontSize:18
    }
  }
});



<%-- var myArray2 = new Array();
myArray2= new Array(<%=dataPoints%>);
var carter = myArray2.toString();
carter = carter.replace("[","");
carter = carter.replace("]","");
carter = carter.split(",");
 --%>
 
var val1 = javaArrayToJsArray(<%=dataPoints%>);
var val2 = javaArrayToJsArray(<%=dataPoint2%>);

var barColors1 = ["#060047","#FFBF00","#285430","#B01E68","#3F0071","#06283D","#FD841F","#D2001A","#4C0033","#E38B29","#277BC0"];

new Chart("myChart1", {
  type: "bar",
  data: {
    labels: val1,
    datasets: [{
      backgroundColor: barColors1,
      data: val2
    }]
  },
  options: {
    title: {
      display: true,
      text: "Document Collection",
      fontSize:18
    }
  },
  
});

var xValues2 = [100,200,300,400,500,600,700,800,900,1000];

new Chart("myChart2", {
  type: "line",
  data: {
    labels: xValues2,
    datasets: [{ 
      data: [860,1140,1060,1060,1070,1110,1330,2210,7830,2478],
      borderColor: "red",
      fill: false
    }, { 
      data: [1600,1700,1700,1900,2000,2700,4000,5000,6000,7000],
      borderColor: "green",
      fill: false
    }, { 
      data: [300,700,2000,5000,6000,4000,2000,1000,200,100],
      borderColor: "blue",
      fill: false
    }]
  },
  options: {
    legend: {display: false}
  }
});

var gateX = javaArrayToJsArray(<%=oneMonGateJsonX%>);
var gateY = javaArrayToJsArray(<%=oneMonGateJsonY%>);

new Chart("gateEntryOneMonth", {
  type: "line",
  data: {
    labels: gateX,
    datasets: [{
      fill: false,
      lineTension: 0,
      backgroundColor: "rgba(0,0,255,1.0)",
      borderColor: "rgba(0,0,255,0.1)",
      data: gateY
    }]
  },
  options: {
    legend: {display: false},
    /* scales: {
      yAxes: [{ticks: {min: 6, max:16}}],
    } */
    title: {
        display: true,
        text: "Last One Month Gate Entry",
        fontSize:18
      }
  }
});

var opacX = javaArrayToJsArray(<%=oneMonOpacJsonX%>);
var opacY = javaArrayToJsArray(<%=oneMonOpacJsonY%>);

new Chart("opacChartLastOneMonth", {
  type: "line",
  data: {
    labels: opacX,
    datasets: [{
      fill: false,
      lineTension: 0,
      backgroundColor: "rgba(255,0,0,1.0)",
      borderColor: "rgba(255,0,0,0.3)",
      data: opacY
    }]
  },
  options: {
    legend: {display: false},
    /* scales: {
      yAxes: [{ticks: {min: 6, max:16}}],
    } */
    title: {
        display: true,
        text: "Last One Month Opac Login",
        fontSize:18
      }
  }
});


var deptStatx = javaArrayToJsArray(<%=depWiseStatJsonX%>);
var deptStaty = javaArrayToJsArray(<%=depWiseStatJsonY%>);

var barColors44 = [
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29",
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29",
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29"
];

new Chart("deptwiseStat", {
  type: "doughnut",
  data: {
    labels: deptStatx,
    datasets: [{
      backgroundColor: barColors44,
      data: deptStaty
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "Books Departmentwise",
      fontSize:18
    }
  }
});



var subStatx = javaArrayToJsArray(<%=subjWiseStatJsonX%>);
var subStaty = javaArrayToJsArray(<%=subjWiseStatJsonY%>);

var barColorsSubStat = [
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29",
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29",
  "#b91d47","#00aba9","#2b5797","#e8c3b9","#1e7145","#060047","#B3005E","#537FE7","#E9F8F9","#EA8FEA","#FFAACF","#F6F7C1","#FFAACF",
  "#609EA2","#332C39","#C1AEFC","#609EA2","#443C68","#FFED00","#16FF00","#0F6292","#000000","#362FD9","#913175","#AA5656","#362FD9",
  "#D61355","#F94A29","#F99417","#5D3891","#400E32","#00425A","#1F8A70","#BFDB38","#03001C","#F55050","#CF4DCE","#13005A","#00337C",
  "#A31ACB","#39B5E0","#6C00FF","#FFB100","#7B2869","#0A2647","#251749","#2D033B","#0014FF","#D2001A","#224B0C","#42032C","#FF9F29"
];

new Chart("subjectWiseStat", {
  type: "doughnut",
  data: {
    labels: subStatx,
    datasets: [{
      backgroundColor: barColorsSubStat,
      data: subStaty
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "Books Subjectwise",
      fontSize:18
    }
  }
});

function javaArrayToJsArray(value)
{
	var myArray2 = new Array();
	myArray2= new Array(value);
	var carter = myArray2.toString();
	carter = carter.replace("[","");
	carter = carter.replace("]","");
	carter = carter.split(",");
	
	return carter;
	
	}

</script>
</form>
</body>
</html>