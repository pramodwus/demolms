	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script> -->
<link rel="icon" href="resources/images/allimages/favicon.ico"
	type="image/x-icon" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0-rc.1/Chart.bundle.js"></script>


<link rel="stylesheet"
	href="<spring:url value='/resources/css/course.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/newcommon.css'/>">
<title>eLuminate</title>
<style>



.box {
  width: 200px; height: 300px;
  position: relative;
  border: 1px solid #BBB;
  background: #EEE;
}
.ribbon {
    position: absolute;
    right: 0px;
    top: -5px;
    z-index: 1;
    overflow: hidden;
    width: 100%;
    height: 75px;
    text-align: right;
}
.ribbon span {
    font-size: 9px;
    font-weight: bold;
    color: #FFF;
    text-transform: uppercase;
    text-align: center;
    line-height: 25px;
    /* transform: rotate(45deg); */
    -webkit-transform: rotate(0deg);
    width: 85px;
    display: block;
    background: #79A70A;
    background: linear-gradient(#F70505 0%, red 100%);
    box-shadow: 0 3px 10px -5px rgba(0, 0, 0, 1);
    position: absolute;
    top: 16px;
    right: 10px;
}
.ribbon span::before {
  content: "";
  position: absolute; left: 0px; top: 100%;
  z-index: -1;
  /* border-left: 3px solid #8F0808;
  border-right: 3px solid transparent;
  border-bottom: 3px solid transparent;
  border-top: 3px solid #8F0808; */
}
.ribbon span::after {
  content: "";
  position: absolute; right: 0px; top: 100%;
  z-index: -1;
  /* border-left: 3px solid transparent;
  border-right: 3px solid #8F0808;
  border-bottom: 3px solid transparent;
  border-top: 3px solid #8F0808; */
}




/* Small devices (landscape phones, 576px and up) */
@media ( max-width : 575.98px) {
	#sidebar {
		display: none;
	}
	.breadcrumb {
		padding: 1rem 1rem;
	}
}

/* Medium devices (tablets, 768px and up) */
@media ( min-width : 768px) { /* 	#sidebar{
	display:inline-block;
	} */
}

/* Large devices (desktops, 992px and up) */
@media ( min-width : 992px) { /* #sidebar{
	display:inline-block;
	}  */
}

/* Extra large devices (large desktops, 1200px and up) */
@media ( min-width : 1200px) { /* 	#sidebar{
	display:inline-block;
	} */
}

</style>
</head>	
	
	
	 <div style="border:1px solid #000; background:white;">
				<canvas id="courseChart" style="width:100%;height:300px;" ></canvas>
				</div>
    <input type="hidden" id="overall" value="${overallchartdata}">
                <c:forEach var="chart" items="${chartdata}" varStatus="loop">
				<input type="hidden" id="size" value="${chartdata.size()}">
				<input type="hidden" id="chartsname${loop.index}" value="${chart.sectionName}">
				<input type="hidden" id="charts${status.index}" value="${chart.marks}">
						<c:forEach var="chartdatas" items="${chart.marks}" varStatus="status">
					<%-- <input type="hidden" id="charts${status.index}" value="${chartdatas}charts${status.index}${loop.index}"> --%>
					<input type="hidden" id="charts${loop.index}${status.index}" value="${chartdatas}">
						<input type="hidden" id="chart" value="${chartdatas}charts${loop.index}${status.index}">
					</c:forEach>
				</c:forEach>
				
				
				
				<script>
		var ctx = document.getElementById("courseChart");
	debugger;
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 16;
	var labels = [];
	var i;
	var allChapter=['ressdsds'];
	var values = [];
	var attempt1 = [];
	var attempt2 = [];
	var labelss=[];
	var chaptertestattempt1 = [];
	var chaptertestattempt2 = [];
	var overall=[];
	var count;
	for (i = 0; i < $('#size').val(); i++){
		if ($('#chartsname' + i).val() != 'flag'){
			count++;
		}
		}
	
	 for (i = 0; i < $('#size').val(); i++) {
		 if($('#charts' + i + '4').val() == 1){
		for (j = 0; j <= 4; j++) {
		if (j == 0 && $('#charts' + i + '4').val() == 0){
				attempt1.push($('#charts0' + j).val());
			}
			if (j == 1 && $('#charts' + i + '4').val() == 0){
				attempt2.push($('#charts0' + j).val());
			}
			if (j == 2 && $('#charts' + i + '4').val() == 1){
				chaptertestattempt1.push($('#charts1' + j).val());
			}
			if (j == 3 && $('#charts' + i + '4').val() == 1){
				chaptertestattempt2.push($('#charts1' + j).val());
			}
			if (j == 4 && $('#charts' + i + '4').val() == 0){
				overall.push(0);
			}
		
		}
		 }
		 if($('#charts' + i + '4').val() == 0)
			 {
			 for (j = 0; j <= 4; j++) {
					if (j == 0 && $('#charts' + i + '4').val() == 0){
							attempt1.push($('#charts0' + j).val());
						}
						if (j == 1 && $('#charts' + i + '4').val() == 0){
							attempt2.push($('#charts0' + j).val());
						}
						if (j == 2 && $('#charts' + i + '4').val() == 1){
							chaptertestattempt1.push(0);
						}
						if (j == 3 && $('#charts' + i + '4').val() == 1){
							chaptertestattempt2.push(0);
						}
						if (j == 4 && $('#charts' + i + '4').val() == 0){
							overall.push(0);
						}
					
					}
			 }
		if ($('#chartsname' + i).val() != 'flag'){
			labels.push($('#chartsname' + i).val());
			labelss.push('Ch'+(i+1));
		}
		
	} 
	 attempt1.push(0);
	 attempt2.push(0);
	 chaptertestattempt1.push(0);
	 chaptertestattempt2.push(0);
	 overall.push($('#overall').val());
	 labelss.push('Overall');
	 
	
	var attempt1 = {
	  label: 'Attempt1',
	  data: attempt1,
	  backgroundColor: 'rgba(100, 99, 132, 255)',
	  borderWidth: 0,
	};

	var attempt2 = {
	  label: 'Attempt2',
	  data: attempt2,
	  backgroundColor: 'rgba(150, 132, 156, 255)',
	  borderWidth: 0,
	};
	var chaptertest1 = {
			  label: 'Chapter test attempt1',
			  data: chaptertestattempt1,
			  backgroundColor: 'rgba(99, 100, 100, 255)',
			  borderWidth: 0,
			};
	var chaptertest2 = {
			  label: 'Chapter test attempt2',
			  data: chaptertestattempt2,
			  backgroundColor: 'rgba(55, 132, 100, 255)',
			  borderWidth: 0,
			};
	var overall = {
			  label:'Overall',
			  data: overall,
			  backgroundColor: 'rgba(100, 255, 100, 255)',
			  borderWidth: 0,
			};
	
	var planetData = {
	  labels: labelss,
	  datasets: [attempt1, attempt2,chaptertest1,chaptertest2,overall]
	};

	var chartOptions = {
			tooltips: {
				callbacks: {
					title: function () {
						return "";
					},
					label: function (t, e) {
						debugger;
						var i = e.datasets[t.datasetIndex].label || "",
							n = e.datasets[t.datasetIndex].data[t.index];
						for(j=1;j<=5;j++){
						if(t.xLabel=='Ch'+j)
						return labels[j-1] + ": (" + t.yLabel+ "%)"
						}
						if(t.xLabel=='Overall')
							return 'overall' + ": (" + t.yLabel+ "%)"
							
					}
				}
			},	
	  scales: {
	    xAxes: [{
	      barPercentage: 1,
	      categoryPercentage:0.6,
	      
	   
	    }],
	    yAxes : [ {
			ticks : {
				beginAtZero : true,
				steps : 10,
				stepValue : 5,
				max : 100
			},
			scaleLabel : {
				display : true,
				labelString : "Marks(%)",
				fontColor : "black"
			}
		} ] 
	  }
	  
	};

	var barChart = new Chart(ctx, {
		
	  type: 'bar',
	  data: planetData,
	  options: chartOptions
	});
	function newdatacall(){
		debugger;
	      
		
		barChart.update();
		} 
	</script> 
	</html>
				