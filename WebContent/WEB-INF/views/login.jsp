<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login - Food Waste Prevention</title>

    <!-- Theme CSS -->
    <link href="/foodwasteprevention/resources/css/login.css" rel="stylesheet">
</head>

<body>
	<label style=" color: #18bc9c; font-family: 'Helvetica Neue', sans-serif; font-size: 26px; font-size: 2.1vw; font-weight: bold; letter-spacing: -1px; line-height: 1; text-align: center;text-shadow: 2px 2px white;">Food Waste Prevention</label>
	<div class="form">
      <ul class="tab-group">
		<!-- <li class="tab active"><a href="#forgot">Sign Up</a></li>
		-->
             <li class="tab active"><a href="#login">Sign in</a></li>
         
      </ul>
      
      <div class="tab-content">
      
        <div id="login">   
		  <s:property value="%{err}"/>
          <form action="loginsubmit" method="post">
          
            <div class="field-wrap">
            <label>
              Net ID<span class="req">*</span>
            </label>
            <input name='sessionUID' autocomplete="off" required/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password" name='sessionPassword' autocomplete="off" required/>
          </div>
          <p class="tab forgot"><a href="#forgot">Forgot Password?</a></p>
          
	      <div class="field-wrap">
			<div class="container">			
			  <ul>
			  <li>
			    <input type="radio" id="typeStudent" name="type" value="Student" required>
			    <label for="typeStudent">Student</label>
			    <div class="check"></div>
			  </li>
			  
			  <li>
			    <input type="radio" id="typeManager" name="type" value="Manager">
			    <label for="typeManager">Manager</label>
			    <div class="check"><div class="inside"></div></div>
			  </li>
			  
			  <li>
			    <input type="radio" id="typeAdmin" name="type" value="Admin">
			    <label for="typeAdmin">Administrator</label>
			    <div class="check"><div class="inside"></div></div>
			  </li>
			</ul>
			</div>
		  </div>
          
          <button class="button button-block">Log In</button>
          </form>
        </div>

          <div id="forgot">   
          <h1>Enter your email address</h1>
          
          <form action="/" method="post">
          
          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input autocomplete="off" required/>
          </div>
          
          
          <button type="submit" class="button button-block"/>Reset Password</button>
          </form>
        </div>

      </div><!-- tab-content -->
      
	</div> <!-- /form -->

    <!-- jQuery -->
    <script src="/foodwasteprevention/resources/jquery/jquery.min.js"></script>

	<script>
		$(document).ready(function(){
		$('.form').find('input, textarea').on('keyup blur focus', function (e) {
		  
		  var $this = $(this),
		      label = $this.prev('label');
		
			  if (e.type === 'keyup') {
					if ($this.val() === '') {
		          label.removeClass('active highlight');
		        } else {
		          label.addClass('active highlight');
		        }
		    } else if (e.type === 'blur') {
		    	if( $this.val() === '' ) {
		    		label.removeClass('active highlight'); 
					} else {
				    label.removeClass('highlight');   
					}   
		    } else if (e.type === 'focus') {
		      
		      if( $this.val() === '' ) {
		    		label.removeClass('highlight'); 
					} 
		      else if( $this.val() !== '' ) {
				    label.addClass('highlight');
					}
		    }
		
		});
		
		$('.tab a').on('click', function (e) {
		  
		  e.preventDefault();
		  
		  $(this).parent().addClass('active');
		  $(this).parent().siblings().removeClass('active');
		  
		  target = $(this).attr('href');
		
		  $('.tab-content > div').not(target).hide();
		  
		  $(target).fadeIn(600);
		  });
		});
	</script>
	
</body>
</html>