/*
 * creates a new XMLHttpRequest object which is the backbone of AJAX,
 * or returns false if the browser doesn't support it
 */
function getXMLHttpRequest() {
	var xmlHttpReq = false;
	// to create XMLHttpRequest object in non-Microsoft browsers
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			// to create XMLHttpRequest object in later versions
			// of Internet Explorer
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {
				// to create XMLHttpRequest object in older versions
				// of Internet Explorer
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}
/*
 * AJAX call starts with this function
 */
 function pareclDetail(x)
 {
	//alert("Hi");
	//$("#Nitrogen").text("Nitrogen   : "+serData.ParcelInfo[x].nitrogen.toString()+" %");
	//$("#Phos").text("    Phosphorus : "+serData.ParcelInfo[x].phos.toString()+" %");
	//$("#k").text("       Pottasium   :"+serData.ParcelInfo[x].pottasium.toString()+" %");
	$( "#farmDetail").empty();
	$( "#chartContainer").empty();
	var chart = new CanvasJS.Chart("chartContainer",
						{
						  title:{
							text: ""
						  },
							legend:{
								verticalAlign: "bottom",
								horizontalAlign: "center"
							},
						   data: [
						  {
							 type: "pie",
							showInLegend: true,
							animationEnabled: true,
							toolTipContent: "#percent %",
							animationDuration: 5000,
						   dataPoints: 
							[
						   {  y: serData.ParcelInfo[x].nitrogen, legendText:"N", indexLabel: "Nitrogen" },
						   {  y: serData.ParcelInfo[x].phos, legendText:"P", indexLabel: "Phosphorus" },
						   {  y: serData.ParcelInfo[x].pottasium, legendText:"K", indexLabel: "Pottasium" }
						   ]
						 }
						 ]
					   });

					chart.render();
	var HTML=null;
	$("#Farm_Img").attr("src",serData.ParcelInfo[x].image);
	HTML="<table style='text-align: center;border: 1px solid #322F2F;border-collapse: initial;'>"
	   HTML+="<tr>";
	   HTML+="<td>Name:</td>";
	   HTML+="<td>"+serData.ParcelInfo[x].owner+"</td>";
	   HTML+="</tr>";
	   HTML+="<tr>";
	   HTML+="<td>Address:</td>";
	   HTML+="<td>"+serData.ParcelInfo[x].address+"</td>";
	   HTML+="</tr>";
	   HTML+="<tr>";
	   HTML+="<td>On Lease:</td>";
	   HTML+="<td>"+serData.ParcelInfo[x].isOnLease+"</td>";
	   HTML+="</tr>";
	   HTML+="<tr>";
	   HTML+="<td>Lease From:</td>";
	   HTML+="<td>"+serData.ParcelInfo[x].leaseStart+"</td>";
	   HTML+="</tr>";
	   HTML+="<tr>";
	    HTML+="<td>Lease To:</td>";
	   HTML+="<td>"+serData.ParcelInfo[x].leaseEnd+"</td>";
	   HTML+="</tr>";

   HTML+="</table>";
   $("#farmDetail").append(HTML);
 }
function makeRequest() {
/*	alert("I am in");
	var param="serchTxt:1";
	var xmlHttpRequest = getXMLHttpRequest();
	xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
	xmlHttpRequest.open("POST", "helloWorld.do", param.length);
	xmlHttpRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttpRequest.send(param);
	*/

		var serchVal= document.getElementById("serchTxt").value;
		var data=2;
		$.ajax({
							
							type: "POST",
							url: "/Demo/helloWorld.do",
							data:{serchTxt:serchVal,load:data},
							dataType: "json",
							
							//if received a response from the server
							success: function( data, textStatus, jqXHR) {
								//our country code was correct so we have some information to display
								 if(data.success){
								   alert("Success");
								   $( "#tableSerch").empty();
								    $( "#farmDetail").empty();
									$("#Farm_Img").attr("src","");
									$("#Nitrogen").text("Nitrogen   : ");
									$("#Phos").text("    Phosphorus : ");
									$("#k").text("       Pottasium   :");
								   serData=null;
								   serData=data;
									var HTML=null;
									HTML="<table style='text-align: center;border: 1px solid #322F2F;border-collapse: initial;'>"
									HTML+="<th>ParcelId</th><th>LandUseTyep</th><th>SoilType</th><th>Area SQ.Meters</th>"
								   for(var i=0;i<serData.ParcelInfo.length ;i++ )
								   {
									   HTML+="<tr>"
									   HTML+="<td><a  onclick='pareclDetail("+i+")'>"+serData.ParcelInfo[i].parcelId+"</a></td>"
									   HTML+="<td>"+serData.ParcelInfo[i].landUseType+"</td>"
									   HTML+="<td>"+serData.ParcelInfo[i].soilType+"</td>"
									   HTML+="<td>"+serData.ParcelInfo[i].area+"</td>"
									   HTML+="</tr>"

								   }
								   HTML+="</table>"
								   $("#tableSerch").append(HTML);

							}},
							
							//If there was no resonse from the server
							error: function(jqXHR, textStatus, errorThrown){
								 alert("there is an error")
							},
							
							//capture the request before it was sent to server
							beforeSend: function(jqXHR, settings){
								
							},
							
							//this is called after the response or error functions are finsihed
							//so that we can take some action
							complete: function(jqXHR, textStatus){
								
							}
				  
						});
}

/*
 * Returns a function that waits for the state change in XMLHttpRequest
 */
function getReadyStateHandler(xmlHttpRequest) {

	// an anonymous function returned
	// it listens to the XMLHttpRequest instance
	return function() {
		if (xmlHttpRequest.readyState == 4) {
			if (xmlHttpRequest.status == 200) {
			//	document.getElementById("hello").innerHTML = xmlHttpRequest.responseText;
			alert(xmlHttpRequest.responseText);
			} else {
				alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
			}
		}
	};
}