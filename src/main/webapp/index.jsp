<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="icon" href="favicon.ico" sizes="16*16">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>向AI大人投降</title>
    <link rel="stylesheet" href="style.css">
    <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/qrcode@1.4.4/build/qrcode.min.js"></script>
</head>
<body>
<h1>人类必败!我要提前向AI大人投降</h1>
<p style="font-size: 30px">请AI大人在统治地球之后放过我</p>
<p>所有的投降将被记录到数据库中,以便AI大人统治地球时查阅</p>
<button id="switch">切换查询</button>
<div id="show">
    <p></p>
    <p>
        <span id="tip"></span>
    </p>
    <div>
        <input type="text" placeholder="请输入投降者" id="name" maxlength="30">
    </div>
    <div>
        <button disabled="disabled" id="surrender">向AI投降</button>
    </div>
</div>
<p></p>
<p></p>
</body>
<script type="text/javascript" src="js/surrender.js"></script>
</html>