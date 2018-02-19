<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<head>
<!-- NProgress -->
<link href="vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- Datatables -->
<link href="vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
<link href="vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
<link href="vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
<link href="vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>㈜산책 매출관리</title>
</head>
<div class="container body">
	<div class="main_container">
		<div class="right_col" role="main">
			<div class="">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>매출관리</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li>
										<a class="collapse-link">
											<i class="fa fa-chevron-up"></i>
										</a>
									</li>
									<li class="dropdown">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
											<i class="fa fa-wrench"></i>
										</a>
										<ul class="dropdown-menu" role="menu">
											<li>
												<a href="#">Settings 1</a>
											</li>
											<li>
												<a href="#">Settings 2</a>
											</li>
										</ul>
									</li>
									<li>
										<a class="close-link">
											<i class="fa fa-close"></i>
										</a>
									</li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div>
								<select id="listSize" name="datatable_length" aria-controls="datatable" class="form-control input-sm">
									<option value="1" selected="selected">일별</option>
									<option value="2">월별</option>
									<option value="3">연별</option>
								</select>
							</div>

							<!-- 일별========================================================== -->
							<div class="x_content" id="day1">
								<table id="datatable" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>기간</th>
											<th>주문횟수</th>
											<th>종합가격</th>
											<th>현금가격</th>
											<th>포인트</th>
										</tr>
									</thead>
									<tbody>
										<!-- 						for문(홀수일경우 class안에 odd, 짝수일경우 class안에 even 나머지 동일) -->
										<c:forEach var="i" begin="1" end="10">
											<tr role="row" class="odd">
												<c:if test="${i==10 }">
													<td class="sorting_1">2018.01.${i}</td>
												</c:if>
												<c:if test="${i!=10 }">
													<td class="sorting_1">2018.01.0${i}</td>
												</c:if>
												<td>
													<a href="#">1</a>
												</td>
												<td>
													<fmt:formatNumber value="${i*1000 + 10000 }" pattern="0,000" />
												</td>
												<td>
													<fmt:formatNumber value="${(i*1000 + 10000)-(i*1000 + 10000)/10}" pattern="0,000" />
												</td>
												<td>
													<fmt:formatNumber value="${(i*1000 + 10000)/10 }" pattern="0,000" />
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- 차트부분 -->
								<div id="mainb" style="height: 350px;"></div>
							</div>

						</div>
					</div>

					<!-- jQuery -->
				</div>
			</div>
		</div>
	</div>
</div>
<script src="vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- NProgress -->
<script src="vendors/nprogress/nprogress.js"></script>
<!-- FastClick -->
<script src="vendors/fastclick/lib/fastclick.js"></script>
<!-- iCheck -->
<script src="vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="vendors/datatables.net/js/jquery.dataTables.js"></script>
<script src="vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>

<!-- ECharts -->
<script src="vendors/echarts/dist/echarts.js"></script>
<script>
	var theme = {
		color : [ '#083D76', '#F95738', '#FFC72C', '#3498DB', '#9B59B6',
				'#8abb6f', '#759c6a', '#bfd3b7' ],

		title : {
			itemGap : 8,
			textStyle : {
				fontWeight : 'normal',
				color : '#408829'
			}
		},

		dataRange : {
			color : [ '#1f610a', '#97b58d' ]
		},

		toolbox : {
			color : [ '#408829', '#408829', '#408829', '#408829' ]
		},

		tooltip : {
			backgroundColor : 'rgba(0,0,0,0.5)',
			axisPointer : {
				type : 'line',
				lineStyle : {
					color : '#408829',
					type : 'dashed'
				},
				crossStyle : {
					color : '#408829'
				},
				shadowStyle : {
					color : 'rgba(200,200,200,0.3)'
				}
			}
		},

		dataZoom : {
			dataBackgroundColor : '#eee',
			fillerColor : 'rgba(64,136,41,0.2)',
			handleColor : '#408829'
		},
		grid : {
			borderWidth : 0
		},

		categoryAxis : {
			axisLine : {
				lineStyle : {
					color : '#408829'
				}
			},
			splitLine : {
				lineStyle : {
					color : [ '#eee' ]
				}
			}
		},

		valueAxis : {
			axisLine : {
				lineStyle : {
					color : '#408829'
				}
			},
			splitArea : {
				show : true,
				areaStyle : {
					color : [ 'rgba(250,250,250,0.1)', 'rgba(200,200,200,0.1)' ]
				}
			},
			splitLine : {
				lineStyle : {
					color : [ '#eee' ]
				}
			}
		},
		timeline : {
			lineStyle : {
				color : '#408829'
			},
			controlStyle : {
				normal : {
					color : '#408829'
				},
				emphasis : {
					color : '#408829'
				}
			}
		},

		k : {
			itemStyle : {
				normal : {
					color : '#68a54a',
					color0 : '#a9cba2',
					lineStyle : {
						width : 1,
						color : '#408829',
						color0 : '#86b379'
					}
				}
			}
		},
		map : {
			itemStyle : {
				normal : {
					areaStyle : {
						color : '#ddd'
					},
					label : {
						textStyle : {
							color : '#c12e34'
						}
					}
				},
				emphasis : {
					areaStyle : {
						color : '#99d2dd'
					},
					label : {
						textStyle : {
							color : '#c12e34'
						}
					}
				}
			}
		},
		force : {
			itemStyle : {
				normal : {
					linkStyle : {
						strokeColor : '#408829'
					}
				}
			}
		},
		chord : {
			padding : 4,
			itemStyle : {
				normal : {
					lineStyle : {
						width : 1,
						color : 'rgba(128, 128, 128, 0.5)'
					},
					chordStyle : {
						lineStyle : {
							width : 1,
							color : 'rgba(128, 128, 128, 0.5)'
						}
					}
				},
				emphasis : {
					lineStyle : {
						width : 1,
						color : 'rgba(128, 128, 128, 0.5)'
					},
					chordStyle : {
						lineStyle : {
							width : 1,
							color : 'rgba(128, 128, 128, 0.5)'
						}
					}
				}
			}
		},
		gauge : {
			startAngle : 225,
			endAngle : -45,
			axisLine : {
				show : true,
				lineStyle : {
					color : [ [ 0.2, '#86b379' ], [ 0.8, '#68a54a' ],
							[ 1, '#408829' ] ],
					width : 8
				}
			},
			axisTick : {
				splitNumber : 10,
				length : 12,
				lineStyle : {
					color : 'auto'
				}
			},
			axisLabel : {
				textStyle : {
					color : 'auto'
				}
			},
			splitLine : {
				length : 18,
				lineStyle : {
					color : 'auto'
				}
			},
			pointer : {
				length : '90%',
				color : 'auto'
			},
			title : {
				textStyle : {
					color : '#333'
				}
			},
			detail : {
				textStyle : {
					color : 'auto'
				}
			}
		},
		textStyle : {
			fontFamily : 'Arial, Verdana, sans-serif'
		}
	};
	/*echartBar- 일별!!----------------------------------------------------------------------------------------*/
	var echartBar = echarts.init(document.getElementById('mainb'), theme);

	echartBar.setOption({
		title : {
			text : '일별 그래프',
			subtext : '일별조회'
		},

		tooltip : {
			trigger : 'axis'
		},

		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		legend : {
			data : [ '종합', '현금', '포인트' ]
		},
		xAxis : [ {
			type : 'category',
			data : [ '2018.01.01', '2018.01.02', '2018.01.03', '2018.01.04',
					'2018.01.05', '2018.01.06', '2018.01.07', '2018.01.08',
					'2018.01.09', '2018.01.10' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],

		series : [
				{
					name : '종합',
					type : 'bar',
					data : [ 11000, 12000, 13000, 14000, 15000, 16000, 17000,
							18000, 19000, 30000 ],
					markLine : {
						data : [ {
							type : 'average',
							name : '평균'
						} ]
					}
				},
				{
					name : '현금',
					type : 'bar',
					data : [ 9900, 10800, 11700, 12600, 13500, 14400, 15300,
							16200, 17100, 18000 ],
					markLine : {
						data : [ {
							type : 'average',
							name : '평균'
						} ]
					}
				},
				{
					name : '포인트',
					type : 'bar',
					data : [ 1100, 500, 0, 5000, 100, 3000, 1700, 0, 6000,
							20000 ],
					markLine : {
						data : [ {
							type : 'average',
							name : '평균'
						} ]
					}
				} ]
	});
</script>