<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

  <title>Getting Started with AJAX using JAVA</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <!--  JavaScript  -->
 <script type="text/javascript" language="javascript" src="ajax.js"></script>
 <script src="script/jquery-1.11.2.min.js"></script>
 <script src="script/jquery-ui-1.11.4/jquery-ui.js"></script>
  <script src="http://localhost:8080/geoserver/openlayers/OpenLayers.js" type="text/javascript"></script>
 <script defer="defer"  src="script/onload1.js" type="text/javascript"> </script>
 <script type="text/javascript" src="script/JPlot/canvasjs.min.js"></script>


 <!-- CSS -->
  
  <link rel="stylesheet" type="text/css" href="http://localhost:8080/geoserver/openlayers/theme/default/style.css"/>
  
   <link rel="stylesheet" href="script/jquery-ui-themes-1.10.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="CSS/main.css">

       
        <!-- Import OpenLayers, reduced, wms read only version -->
	   
    </head>

    <body>
    <div id="header">
	<h1>Land Information Management for Precision Farming</h1>
	</div>
    <div id="nav">
    </div>
	<div id="section">
	</div>
	

	<script>
		var serArr=[];
		var serData;
		$(document).ready(function()
		{
			//sample();
			
			$( "#section" ).load( "HTML/sample.html", function( response, status, xhr ) 
				{
				
				init1();
			  if ( status == "error" ) 
			   {
			    var msg = "Sorry but there was an error: ";
			    $( "#section" ).html( msg + xhr.status + " " + xhr.statusText );

			  }
			});
			$( "#nav" ).load( "HTML/Geo_Side.html", function( response, status, xhr ) 
					{

					geo_side();
					sample();
					
				  
					$("#Farm_Img").attr("src","");
				  if ( status == "error" ) 
				   {
				    var msg = "Sorry but there was an error: ";
				    $( "#section" ).html( msg + xhr.status + " " + xhr.statusText );

				  }
				}); 
			
		});

		
	   </script>  
    </body>
	
</html>
