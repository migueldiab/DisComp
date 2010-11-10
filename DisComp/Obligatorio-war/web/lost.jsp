<%-- 
    Document   : index
    Created on : 23/10/2010, 09:48:00 AM
    Author     : migueldiab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Estás perdido?</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" content="no-cache" />
		<link rel="stylesheet" href="css/reset-fonts.css" type="text/css" media="screen, projection" />
		<link rel="stylesheet" href="css/gt-styles.css" type="text/css" media="screen, projection" />
	</head>
	<body>
		<!-- head -->
		<div class="gt-hd clearfix">
			<!-- logo -->
			<div class="gt-logo">
				Contact Admin
			</div>
			<!-- / logo -->

			<!-- navigation -->
      <div class="gt-nav">
        <jsp:include page="layout/nav.jsp" />
      </div>


			<!-- / navigation -->

		</div>
		<!-- / head -->

		<!-- body -->
		<div class="gt-bd gt-cols clearfix">
			<!-- main content -->
			<div class="gt-content">
        <h2>Estás perdido?</h2>
        Tal vez podamos sugerirte algo...
			</div>
			<!-- / main content -->

			<!-- sidebar -->
			<div class="gt-sidebar">

        <jsp:include page="layout/side.jsp" />
        <hr/>
        <% if (request.getAttribute("error") != null) { %>
        <div class="gt-error">Error :  <%= request.getAttribute("error") %></div>
        <% } %>

			</div>
			<!-- / sidebar -->

		</div>
		<!-- / body -->

		<!-- footer -->
		<div class="gt-footer">
			<p>Copyright &copy; 2010 Miguel A. Diab</p>
		</div>
		<!-- /footer -->
	</body>
</html>
