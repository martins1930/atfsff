<%-- 
    Document   : contacto
    Created on : Oct 14, 2012, 6:30:15 PM
    Author     : martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../static/js/ext/jquery-1.8.1.min.js"></script>
        <script type="text/javascript" src="../static/js/ext/underscore-min.js"></script>
        <script type="text/javascript" src="../static/js/ext/backbone-min.js"></script>
        <script data-main="../static/js/ext/require/text" type="text/javascript" src="../static/js/ext/require/require.js"></script>
        <!--<script type="text/javascript" src="../static/js/ext/require/text.js"></script>-->
        
        
        <script type="text/javascript" src="../static/js/core_atfsff.js"></script>
        <script type="text/javascript" src="../static/js/contacto.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div id="test2">
            <a id="list" href="#">list</a> <br/>
            <a id="post" href="#">post</a> <br/>
            <a id="postV" href="#">postV</a> <br/>
        </div>
        
        <div id="vista">
            <div id="results-template">
                <a href="#">{{ coso }}</a>
            </div>
        </div>
        <a href="#about">about</a>
        <a href="#contacto/234">contacto</a>
        
        <div id="dyn_req">
            
        </div>
    </body>
</html>
