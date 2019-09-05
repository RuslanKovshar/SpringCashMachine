<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">

<@c.common>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><@spring.message "id.message"/></th>
            <th scope="col"><@spring.message "email.message"/></th>
            <th scope="col"><@spring.message "user.first.name.message"/></th>
            <th scope="col"><@spring.message "user.second.name.message"/></th>
            <th scope="col"><@spring.message "roles.message"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td><#list user.authorities as r >${r}<#sep>, </#list></td>
        </tr>
        </tbody>
    </table>

    <form action="/open_check">
            <button class="btn btn-lg btn-success btn-block mt-2"><@spring.message "open.check.message"/></button>
    </form>
    <#if isMerchandiser>
        <form action="/merchandiser">
                <button class="btn btn-lg btn-success btn-block mt-2"><@spring.message "merchandiser.menu.message"/></button>
        </form>
    </#if>

    <form action="/senior_cashier_menu">
        <button class="btn btn-lg btn-success btn-block mt-2"><@spring.message "open.check.message"/></button>
    </form>
</@c.common>