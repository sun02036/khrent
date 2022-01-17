<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
	
	      function drawChart() {
	          var data = google.visualization.arrayToDataTable([
	            ['Year', '예약건수'],
	            ['2021.05',  5],
	            ['2021.06',  15],
	            ['2021.07',  9],
	            ['2021.08',  8],
	            ['2021.09',  13],
	            ['2021.10',  17],
	            ['2021.11',  18]
	          ]);

	          var options = {
	            title: '월별 예약건수',
	            curveType: 'function',
	            legend: { position: 'bottom'}
	          };

	          var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

	          chart.draw(data, options);
	        }
	      </script>
	
	<div id="curve_chart" style="width:1530px; height: 500px;"></div>
		
		