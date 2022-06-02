

	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	let result =[];
	result.push(['부서명', '부서인원']);
	let xhtp = new XMLHttpRequest();
	xhtp.open('GET','../ChartServlet');
	xhtp.send();
	xhtp.onload = function() {
		let data = JSON.parse(xhtp.responseText);
		for (let field in data) {
			result.push([field, data[field]]);
		}
		
		var data1 = google.visualization.arrayToDataTable(result);
		var options = {
				title : '부서별 인원현황'
			};

			var chart = new google.visualization.PieChart(document
					.getElementById('piechart'));

			chart.draw(data1, options);
	}
	
	function drawChart() {

		/* var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ], [ 'Work', 11 ], [ 'Eat', 2 ],
				[ 'Commute', 2 ], [ 'Watch TV', 2 ], [ 'Sleep', 7 ] ]);

		var options = {
			title : 'My Daily Activities'
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options); */
	}
