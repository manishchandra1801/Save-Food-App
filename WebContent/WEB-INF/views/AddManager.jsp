<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>   
<%@taglib  prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>

<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>Manager Quad Managers</title>
	
		<!-- Date Picker CSS -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		
	    <!-- Bootstrap Core CSS -->
	    <link href="/foodwasteprevention/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	    <!-- Theme CSS -->
	    <link href="/foodwasteprevention/resources/css/freelancer.css" rel="stylesheet">
	
	    <link href="/foodwasteprevention/resources/css/media.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="/foodwasteprevention/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
	    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	
	</head>

	<body id="page-top" class="index">
	    <!-- Navigation -->
	    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
	        <div class="container">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header page-scroll">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
	                </button>
	                <a class="navbar-brand" href="#page-top">Administrator</a>
	            </div>
	
	            <!-- Collect the nav links, forms, and other content for toggling -->
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav navbar-right">
	                    <li class="hidden">
	                        <a href="#page-top"></a>
	                    </li>
	                    <li class="page-scroll">
	                        <a href="logout">Logout</a>
	                    </li>
	                    <li class="page-scroll">
	                        <a href="/foodwasteprevention/admin">Back</a>
	                    </li>
	                </ul>
	            </div>
	            <!-- /.navbar-collapse -->
	        </div>
	        <!-- /.container-fluid -->
	    </nav>
	
	    <!-- Header -->
	    <header>
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-12 text-center">
	                    <div class="intro-text">
	                    <div class="row">
		                    <div class="footer-col col-md-4"></div>
							<div class="footer-col col-md-4"><h1 style="text-shadow: 0px 0px 6px rgba(255,255,255,0.7);">Manage Quad Managers</h1></div>
	                        <div class="footer-col col-md-4"></div>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    	   	 
	    <!-- Portfolio Grid Section -->
	    <section id="portfolio">
	        <div class="container">
				<div class="row">
					<div class="col-lg-12" style="width: 50%;">
						<h2 style="color: rgba(0,0,0,0.6);text-shadow: 2px 8px 6px rgba(0,0,0,0.2),0px -5px 35px rgba(255,255,255,0.3);">ADD MANAGER</h2>
							<hr>
							<%
							String message="";
							if(request.getAttribute("MESSAGE")!=null)
							{
								message=(String)request.getAttribute("MESSAGE");
							}
							else
							{
								message="";
							}
							%>
							<p><%= message %></p>
							
							<form action="addmanagersubmit" method="post">
								<h4>Name</h4><h4><input type="managername" name="addmanagername" required></h4>
								<h4>Manager ID</h4><h4><input type="managerid" name="addmanagerid" required></h4>
								<h4>Phone Number</h4><h4><input type="managerphonenumber" name="addmanagerphonenumber"></h4>
								<h4>Email</h4><h4><input type="manageremail" name="addmanageremail" required></h4>
								<h4>Quad ID</h4><h4><s:select name="addmanagerQuad" list="quadName"/></h4><br/><br/>
								<input type="submit" class="btn btn-lg btn-block btn-success" style="width:12%;display:inline-block;" value="Add">
							</form>
							
							<hr>
							<form action="searchmanagersubmit" method="post">
					</div>
					<div class="col-lg-12" style="width: 50%;">
							<h2 style="color: rgba(0,0,0,0.6);text-shadow: 2px 8px 6px rgba(0,0,0,0.2),0px -5px 35px rgba(255,255,255,0.3);">SEARCH MANAGER</h2>
							<hr>
							<%
							String smessage="";
							if(request.getAttribute("SEARCHERRORMESSAGE")!=null)
							{
								smessage=(String)request.getAttribute("SEARCHERRORMESSAGE");
							}
							else
							{
								smessage="";
							}
							%>
							<p><%= smessage %></p>
							<h4>Name</h4><h4><input type="text" name="searchmanagername">&nbsp &nbsp</h4>
							<h4>Manager ID</h4><h4><input type="text" name="searchmanagerid">&nbsp &nbsp</h4>
							<h4>Email ID</h4><h4><input type="text" name="searchmanageremail">&nbsp &nbsp</h4>
							<h4>Quad ID</h4><h4><s:select name="searchmanagerQuad" headerKey='-1' headerValue="" list="quadName"/>&nbsp &nbsp</h4>
							<input type="submit" class="btn btn-lg btn-block btn-success" value="Search" style="width:25%">
							<br/><br/>
					</div>				
					<div class="col-lg-12 text-center">
							<table style="width:100%" border="1px solid black">
							  <tr>
							    <th><h4>Manager ID</h4></th>
							    <th><h4>Manager Name</h4></th>
							    <th><h4>Quad ID</h4></th>
							    <th><h4>Email ID</h4></th>
							    <th><h4>Phone Number</h4></th>
							    <th><h4>Select</h4></th>
							  </tr>

							  <% ArrayList<ArrayList<String>> searchlist=null; %>
							    
							   <%
							   if(request.getAttribute("SEARCHRESULTSMESSAGE")!=null)
							   {
								   out.print("<tr><td>"+request.getAttribute("SEARCHRESULTSMESSAGE").toString()+"</td></tr>");
							   }
							   %>
							
							   <s:iterator value="parsesearch" var="var">
								<tr>
								<td><s:property value="#var[0]"/></td>
								<td><input type="text" name=name_${var[0]} value="${var[1]}"/></td>
								<td><input type="text" name=quad_${var[0]} value="${var[2]}" /></td>
								<td><input type="text" name=email_${var[0]} value="${var[3]}"/></td>
								<td><input type="text" name=phone_${var[0]} value="${var[4]}"/></td>
								<td><input type="radio" name="check" value="${var[0]}"></td>
								</tr>
							 </s:iterator>
							   	<tr><input type="submit" name="buttonAction" class="btn btn-lg btn-block btn-danger" value="Delete" style="width:12%;display:inline" formaction="updatedeletemanagersubmit">
								<input type="submit" name="buttonAction" class="btn btn-lg btn-block btn-warning" value="Update" style="width:12%;display:inline;margin-left: 25px;" formaction="updatedeletemanagersubmit"></tr>
							<br/><br/>	
							</form>
							</table>
							</form>

					</div>
				</div>
	            
				                    
		<hr class="star-primary">  
	    </section>
		
	    <!-- Footer -->
	    <footer class="text-center">
	        <div class="footer-above">
	            <div class="container">
	                <div class="row">
	                    <div class="footer-col col-md-4">
	                        <h3>Location</h3>
	                        <p>Whichever Quad You Like</p>
	                    </div>
	                    <div class="footer-col col-md-4">
	                        <h3>Around the Web</h3>
	                        <ul class="list-inline">
	                            <li>
	                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-facebook"></i></a>
	                            </li>
	                            <li>
	                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-google-plus"></i></a>
	                            </li>
	                            <li>
	                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-twitter"></i></a>
	                            </li>
	                            <li>
	                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-linkedin"></i></a>
	                            </li>
	                            <li>
	                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-dribbble"></i></a>
	                            </li>
	                        </ul>
	                    </div>
	                    <div class="footer-col col-md-4">
	                        <h3>About Developers</h3>
	                        <p>To iterate is human, to recurse divine.</p>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="footer-below">
	            <div class="container">
	                <div class="row">
	                    <div class="col-lg-12">
	                        Copyright &copy; 2016
	                    </div>
	                </div>
	            </div>
	        </div>
	    </footer>
	
	    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	    <div class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
	        <a class="btn btn-primary" href="#page-top">
	            <i class="fa fa-chevron-up"></i>
	        </a>
	    </div>
	
	    <!-- jQuery -->
	    <script src="/foodwasteprevention/resources/jquery/jquery.min.js"></script>
		
		<!-- Date Picker -->
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="/foodwasteprevention/resources/bootstrap/js/bootstrap.min.js"></script>
	
	    <!-- Plugin JavaScript -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	
	    <!-- Contact Form JavaScript -->
	    <script src="/foodwasteprevention/resources/js/jqBootstrapValidation.js"></script>
		
	    <!-- Theme JavaScript -->
	    <script src="/foodwasteprevention/resources/js/freelancer.js"></script>
	
	</body>
</html>