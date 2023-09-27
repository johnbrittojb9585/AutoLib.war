<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.io.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<body>
<form method="get" action="./DashboardServlet">
<h2>Dashboard</h2>
<table width="90%" height="100%" align="center">
<tr><td><canvas id="myChart" style="width:100%;max-width:600px"></canvas></td><td><canvas id="myChart1" style="width:70%;max-width:350px"></canvas></td></tr>
<tr><td><canvas id="myChart2" style="width:100%;max-width:600px"></canvas></td><td><canvas id="myChart3" style="width:100%;max-width:600px"></canvas></td></tr>
<tr></tr>
<input type="hidden" name="flag">
</table>

<script>
var xValues = ["BOOK", "BOOK BANK", "THESIS", "REPORT", "NON BOOK"];
var yValues = [55, 49, 44, 24, 15];
var barColors = ["red", "green","blue","orange","brown"];

<%

ArrayList m =(ArrayList) request.getAttribute("dashboard");


System.out.print("Map:::::::::::::::::"+m);

ArrayList arry = new ArrayList();
arry.add(0, "200");
arry.add(1, "500");
arry.add(2, "600");
arry.add(3, "700");
arry.add(4, "900");




Map map = new HashMap();
map.put("book", arry);


%>

new Chart("myChart", {
  type: "bar",
  data: {
    labels: <%=map.get("book")%>,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "World Wine Production 2018"
    }
  }
});

var xValues1 = ["Italy", "France", "Spain", "USA", "Argentina"];
var yValues1 = [55, 49, 44, 24, 15];
var barColors1 = [
  "#b91d47",
  "#00aba9",
  "#2b5797",
  "#e8c3b9",
  "#1e7145"
];

new Chart("myChart1", {
  type: "pie",
  data: {
    labels: xValues1,
    datasets: [{
      backgroundColor: barColors1,
      data: yValues1
    }]
  },
  options: {
    title: {
      display: true,
      text: "World Wide Wine Production 2018"
    }
  }
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

var xValues3 = [50,60,70,80,90,100,110,120,130,140,150];
var yValues3 = [7,8,8,9,9,9,10,11,14,14,15];

new Chart("myChart3", {
  type: "line",
  data: {
    labels: xValues3,
    datasets: [{
      fill: false,
      lineTension: 0,
      backgroundColor: "rgba(0,0,255,1.0)",
      borderColor: "rgba(0,0,255,0.1)",
      data: yValues3
    }]
  },
  options: {
    legend: {display: false},
    scales: {
      yAxes: [{ticks: {min: 6, max:16}}],
    }
  }
});

</script>
</form>
</body>
</html>