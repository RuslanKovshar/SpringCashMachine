<#import "parts/common.ftl" as c>
<#import "parts/report.ftl" as report>
<#import "/spring.ftl" as spring/>
<@c.common>

    <@report.report alert="warning" type="z-report.message"/>

    <form action="/logout" class="form-inline my-2 my-lg-0" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button class="btn btn-outline-warning my-2 my-sm-0"
                type="submit"><@spring.message "finish.work.message"/></button>
    </form>

</@c.common>