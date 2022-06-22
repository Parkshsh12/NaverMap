<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	double x = (double)request.getAttribute("juso1");
	double y = (double)request.getAttribute("juso2");
%>
<body>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=mj1uygntyw&callback=initMap"></script>
    <script type="text/javascript">
        var map = null;

        function initMap() {
            map = new naver.maps.Map('map', {
                center: new naver.maps.LatLng(<%=x%>, <%=y%>),
                zoom: 18
            });
            
            var marker = new naver.maps.Marker({
            	map: map,
     			position: new naver.maps.LatLng(<%=x%>, <%=y%>),
     			icon:{
     				content: '<img src="./resources/img/free-icon-flag-148878.png" alt="" style="margin: 0px; padding: 0px; border: 0px solid transparent; display: block; max-width: none; max-height: none; -webkit-user-select: none; position: absolute; width: 1.5rem; height: 1.5rem; left: 0px; top: 0px;">',
     	            size: new naver.maps.Size(32, 32),
     	            anchor: new naver.maps.Point(8, 16)
     			}
            });
        }
    </script>
	<div id="map" style="width:500px;height:400px;margin:0 auto;"></div>
</body>
</html>