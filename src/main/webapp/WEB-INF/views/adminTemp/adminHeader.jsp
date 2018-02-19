<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title">
						<img src="images/adminTemplate/logo_temp.png" alt="..." width="50px" style="display: inline-block;">
						<span>㈜산책</span>
						</a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic">
							<img src="images/adminTemplate/admin.jpg" alt="..." class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>반갑습니다.</span>
							<h2>OOO관리자님</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<!-- <h3>관리</h3> -->
							<ul class="nav side-menu">
								<li><a> <i class="fa fa-book"></i> 도서관리 <span class="fa fa-chevron-down"></span>
								</a>
									<ul class="nav child_menu">
										<li><a href="adminBookSearch.do">도서검색</a></li>
										<li><a href="adminBookInsert.do">도서등록</a></li>
									</ul></li>
								<li><a> <i class="fa fa-users"></i> 회원관리 <span class="fa fa-chevron-down"></span>
								</a>
									<ul class="nav child_menu">
										<li><a href="adminMemberManage.do">회원리스트</a></li>
									</ul></li>
								<li><a href="adminMap.do"> <i class="fa fa-map-marker"></i> 영업점관리
								</a></li>
								<li><a> <i class="fa fa-bar-chart-o"></i> 매출관리 <span class="fa fa-chevron-down"></span>
								</a>
									<ul class="nav child_menu">
										<li><a href="adminSales.do">기간별관리</a></li>
									</ul></li>
								<li><a> <i class="fa fa-question-circle"></i> 고객문의관리 <span class="fa fa-chevron-down"></span>
								</a>
									<ul class="nav child_menu">
										<li><a href="adminFaqMain.do">FAQ관리</a></li>
										<li><a href="adminCstMain.do">1:1문의관리</a></li>
										<li><a href="adminNctMain.do">공지사항관리</a></li>
									</ul></li>
								<li><a> <i class="fa fa-handshake-o"></i> 주문관리 <span class="fa fa-chevron-down"></span>
								</a>
									<ul class="nav child_menu">
										<li><a href="adminOrderSearch.do">주문내역</a></li>
										<li><a href="adminChange.do">환불/교환</a></li>
										<li><a href="adminDelivery.do">배송내역</a></li>
									</ul></li>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings"> <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen"> <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html"> <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
					<div class="nav toggle">
						<a id="menu_toggle"> <i class="fa fa-bars"></i>
						</a>
					</div>

					<ul class="nav navbar-nav navbar-right">
						<li class=""><a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <img src="images/adminTemplate/admin.jpg" alt=""> OOO관리자님 <span class=" fa fa-angle-down"></span>
						</a>
							<ul class="dropdown-menu dropdown-usermenu pull-right">
								<li><a href="javascript:;"> Profile</a></li>
								<li><a href="javascript:;"> <span class="badge bg-red pull-right">50%</span> <span>Settings</span>
								</a></li>
								<li><a href="javascript:;">Help</a></li>
								<li><a href="login.html"> <i class="fa fa-sign-out pull-right"></i> Log Out
								</a></li>
							</ul></li>

						<li role="presentation" class="dropdown"><a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false"> <i class="fa fa-envelope-o" style="margin-top: 7px;"></i> <!-- 개수 알람 --> <span class="badge bg-green">10</span>
						</a>
							<ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
								<li><a> <span class="image"> <img src="images/adminTemplate/admin.jpg" alt="Profile Image" />
									</span> <span> <span>John Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li><a> <span class="image"> <img src="images/adminTemplate/admin.jpg" alt="Profile Image" />
									</span> <span> <span>John Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li><a> <span class="image"> <img src="images/adminTemplate/admin.jpg" alt="Profile Image" />
									</span> <span> <span>John Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li><a> <span class="image"> <img src="images/adminTemplate/admin.jpg" alt="Profile Image" />
									</span> <span> <span>John Smith</span> <span class="time">3 mins ago</span>
									</span> <span class="message"> Film festivals used to be do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li>
									<div class="text-center">
										<a> <strong>See All Alerts</strong> <i class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</ul></li>
					</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
