<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">
<@c.common>
    <div class="jumbotron jumbotron-fluid">
        <div class="container text-center">
            <h1 class="display-4"><@spring.message "main.404.message"/></h1>
            <p class="lead"><@spring.message "secondary.404.message"/></p>
        </div>
    </div>
</@c.common>