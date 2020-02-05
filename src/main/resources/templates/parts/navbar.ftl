<#import "/spring.ftl" as spring/>
<#include "security.ftl">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">CASH machine</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><@spring.message "home.message"/></a>
            </li>
        </ul>

        <div class="dropdown my-2 my-lg-0">
            <a class="nav-link dropdown-toggle"
               href="#" id="navbarDropdown"
               role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <@spring.message "language.message"/>
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a id="en" class="dropdown-item" href="?lang=en">ENG</a>
                <a class="dropdown-item" href="?lang=UA">UA</a>
            </div>
        </div>

        <#if authenticated>
            <div class="nav-link mx-4"><a href="/" style="text-decoration: none;">${name}</a></div>
            <form action="/logout" class="form-inline my-2 my-lg-0" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button class="btn btn-outline-warning my-2 my-sm-0"
                        type="submit"><@spring.message "logout.message"/></button>
            </form>
        <#else>
            <form action="/login" class="form-inline my-2 my-lg-0">
                <button class="btn btn-outline-success my-2 my-sm-0"
                        type="submit"><@spring.message "login.message"/></button>
            </form>
        </#if>
    </div>
</nav>