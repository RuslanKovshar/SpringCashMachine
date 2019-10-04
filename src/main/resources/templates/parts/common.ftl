<#macro common>
    <#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Cash machine</title>
        <!-- Required meta tags -->
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet"
              href="/css/bootstrap.min.css"/>

    </head>
    <body>
    <#include "navbar.ftl">
    <div class="container mt-5">
        <#nested>
    </div>
    <script src="/jquery/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>