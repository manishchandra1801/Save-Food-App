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
	
	    <title>Welcome Administrator!</title>
	
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
	                </ul>
	            </div>
	            <!-- /.navbar-collapse -->
	        </div>
	        <!-- /.container-fluid -->
	    </nav>
	    
	<header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-text">
                        <span class="name">Admin Options</span>
                        <hr class="star-light">
                    </div>
                </div>
                <div class="col-lg-12 text-center" style="padding-left: 285px;">
                        <h3><a style="width:70%;" class="btn btn-lg btn-block btn-primary"href="<s:url action="urlTagAction" />"target="frame1">Manage Quad Managers</a></h3>
						<h3><a style="width:70%;" class="btn btn-lg btn-block btn-primary" href="<s:url action="quadInfo" />" target="frame1">Manage Quads</a></h3>
						<h3><a style="width:70%;" class="btn btn-lg btn-block btn-primary" href="<s:url action="mealCourseInfo" />" target="frame1" >Manage Meal Courses</a></h3>
						<h3><a style="width:70%;" class="btn btn-lg btn-block btn-primary" href="<s:url action="configEdit" />" target="frame1" >Food Selection/Menu Creation Deadlines</a></h3>    
						<h3><a style="width:70%;" class="btn btn-lg btn-block btn-primary" href="<s:url action="uploadaction" />" target="frame1" >Add Student</a></h3>   
                </div>
            </div>
        </div>
    </header> 

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