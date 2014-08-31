<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kodlak: Game in Progress</title>
    <%@include file="include_common.jsp"%>
</head>
<body>
    <div id="main">
        <div id="sidebarContainer"></div>
        <div id="playerCanvasContainer"></div>
    </div>
    <script>
        var playerCanvas = new PlayerCanvas();
        var canvas = playerCanvas.create();
        $('#playerCanvasContainer').append(canvas);
        playerCanvas.refresh();

        var sidebar = new Sidebar();
        var sidebarContent = sidebar.create();
        $('#sidebarContainer').append(sidebarContent);
    </script>
</body>
</html>
