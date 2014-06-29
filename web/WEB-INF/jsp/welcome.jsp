<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/player.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/player_canvas.js"></script>
    <script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/jquery-ui/1.11.0/jquery-ui.min.js"></script>
</head>
<body>
    <div id="playerCanvasContainer"></div>
    <script>
        var playerCanvas = new PlayerCanvas();
        var canvas = playerCanvas.create();
        $('#playerCanvasContainer').append(canvas);
        playerCanvas.refresh();
    </script>
</body>
</html>
