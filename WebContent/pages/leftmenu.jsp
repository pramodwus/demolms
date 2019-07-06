<%@page import="com.qbis.model.AppFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.qbis.model.User"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
	.aside-main-sidebar{
	   margin-top:-15px !important;
	}
	.sidebar-menu>li.active>a{
	color:#000000	 !important;
	border-left: 3px solid #479cce !important;
	}
	.skin-black-light .treeview-menu>li>a {
    color: #fff;
}
	@media (max-width: 767px)
	{
	   .aside-main-sidebar{
		   margin-top:-14px !important;
		}
    }li.active>a 
    {
    background-color: #fff !important;
    }
    .skin-black-light .sidebar-menu>li:hover>a, .skin-black-light .sidebar-menu>li.active>a{
    background:#000;
    color: #fff;
    }
    
   .skin-black-light .sidebar-menu>li:hover>a, .skin-black-light .sidebar-menu>{ 
  background-color: black !important;
}
  .treeview-menu>li.active>a>treeview-menu{
	color:#000000	 !important;
	border-left: 3px solid #479cce !important;
	}
	.skin-black-light .sidebar a {
    color: #fff;
    }
    .skin-black-light .treeview-menu>li.active>a, .skin-black-light .treeview-menu>li>a:hover {
    color: #000;
}
    </style>
<!-- LeftMenu -->
<!-- Left side column. contains the logo and sidebar -->
<div class="col-sm-2 main-sidebar"
	style="padding-left: 0px; border: 0px solid; height: 0;">
	<aside class="main-sidebar aside-main-sidebar">

		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			          <%
			          @SuppressWarnings("unchecked")
			          ArrayList<AppFunctions> functionlist=(ArrayList<AppFunctions>)request.getSession().getAttribute("appfunctions");
			          %>
			<ul class="sidebar-menu" style="background-color: #479dce; ">
				<%
					 for(int functionIndex=0;functionIndex<functionlist.size();functionIndex++) {
					 if(functionlist.get(functionIndex).getSubAppFuntion()!=null && functionlist.get(functionIndex).getSubAppFuntion().size()>1){	
				%>	
				      <!-- This is used for website tour. -->
				       <%
						   if(functionIndex ==0){
						%>
						<c:set var="firstmenupage"
							value="<%=functionlist.get(functionIndex).getMenuId()%>"></c:set>
						<%
							}
						%>
						 <!-- /.end of touring -->
				<li class="treeview" 
					id="<%=functionlist.get(functionIndex).getMenuId()%>"><a
					class="cursor-pointer"><i
						class="<%=functionlist.get(functionIndex).getFunctionClass()%>"></i><span><spring:message
								code="<%=functionlist.get(functionIndex).getFunctionName()%>" /></span>
				</a>
					<ul class="treeview-menu">
						<%
							if(functionlist.get(functionIndex).getSubAppFuntion()!=null)
						   for(int subindex=0;subindex<functionlist.get(functionIndex).getSubAppFuntion().size();subindex++){
						%>
						<li 
							id="<%=functionlist.get(functionIndex).getSubAppFuntion().get(subindex).getSubMenuId()%>">
							<a style="font-size:12px ;background-color: #479cce"
							href="<%=functionlist.get(functionIndex).getSubAppFuntion().get(subindex).getSubFunctionService()%>">
								<i
								class="<%=functionlist.get(functionIndex).getSubAppFuntion().get(subindex).getSubfunctionClass()%>"></i>
								<spring:message
									code="<%=functionlist.get(functionIndex).getSubAppFuntion().get(subindex).getSubFunctionName()%>" />
									
						</a>
						</li>
						<%
							}
						%>
					</ul>
					</li>
				<%
					}else{					 
				%>
				
				 <!-- This is used for website tour. -->
				       <%
						   if(functionIndex ==0){
						%>
						<c:set var="firstmenupage"
							value="<%=functionlist.get(functionIndex).getSubAppFuntion().get(0).getSubMenuId()%>"></c:set>
						<%
							}
						%>
						 <!-- /.end of touring -->
				<li class="treeview"
							id="<%=functionlist.get(functionIndex).getSubAppFuntion().get(0).getSubMenuId()%>">
							<a style="font-size:12px;"
							href="<%=functionlist.get(functionIndex).getSubAppFuntion().get(0).getSubFunctionService()%>">
								<i
								class="<%=functionlist.get(functionIndex).getSubAppFuntion().get(0).getSubfunctionClass()%>"></i>
								<span><spring:message
									code="<%=functionlist.get(functionIndex).getSubAppFuntion().get(0).getSubFunctionName()%>" />
									</span>
						</a>
						</li>
				<%
					}}					 
				%>
			</ul>
			<!-- /.sidebar-menu -->
		</section>
		<!-- /.sidebar -->
	</aside>
</div>
<script>

/**
 * @summary array of qbis guide tour steps
 */
var steps = [];
/**
 * @summary a variable which describes the id of first page in leftmenu for a current user.
 */
var firstpagemenuid="${firstmenupage}";

<%for(int functionIndex=0;functionIndex<functionlist.size();functionIndex++) {
	 if(functionlist.get(functionIndex).getSubAppFuntion()!=null && functionlist.get(functionIndex).getSubAppFuntion().size()>1){
%>
	  /**
	   * @summary pushing the steps for leftmenu in step guide array.
	   */
steps.push({
	    element: '#<%=functionlist.get(functionIndex).getMenuId()%>',
	    title : 'Welcome to leftment tour',
	    content : "Lorem Ipsum",
	    placement : 'right'
	});
	  <%} else {%>
	  
	  /**
	   * @summary pushing the steps for leftmenu in step guide array.
	   */
steps.push({
	    element: '#<%=functionlist.get(functionIndex).getSubAppFuntion().get(0).getSubMenuId()%>',
	    title : 'Welcome to leftment tour',
	    content : "Lorem Ipsum",
	    placement : 'right'
	});
   <%
	  }
	  }%>
	/**
	 * @summary This function would be called on document ready.
	 */
	$(document).ready(function() {
		try {
			if (firstpagemenuid != currentpagemenuid) {
				steps = [];
			}
			if (steps.length > 0) {
				startQbisTourSteps();
			}
		} catch (err) {
			console.log(err.message);
		}
	});

	/**
	 * @summary this function is used for starting the steps for qbis guide tour.
	 */
	function startQbisTourSteps() {
		var check_guided_tour = getCookie('qbis_guid_tour_' + currentpagemenuid);
		if (check_guided_tour != 1) {
			var tour = new Tour({
				  steps: steps ,
				  storage:false,
				  onEnd: function (tour) {
					  setCookie("qbis_guid_tour_" + currentpagemenuid);
				  },
					  });
			tour.init();
			// Start the tour
			tour.start();
			
		}
	}

	/**
	 * @summary This is used for setting the cookie.
	 * @param name
	 */
	function setCookie(name) {
		var d = new Date();
		d.setTime(d.getTime() + (365 * 24 * 60 * 60 * 1000));
		var expires = "expires=" + d.toUTCString();
		document.cookie = name + "=1;" + expires + ";path=/";
	}

	/**
	 * @summary This is used for getting the cookie.
	 * @param cname
	 */
	function getCookie(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ')
				c = c.substring(1);
			if (c.indexOf(name) == 0)
				return c.substring(name.length, c.length);
		}
		return "";
	}
</script>








