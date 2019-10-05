<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">
<@c.common>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">
                <@spring.message "id.message"/>
            </th>
            <th scope="col">
                <@spring.message "email.message"/>
            </th>
            <th scope="col">
                <@spring.message "user.first.name.message"/>
            </th>
            <th scope="col">
                <@spring.message "user.second.name.message"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${userDTO.id}</td>
            <td>${userDTO.email}</td>
            <td>${userDTO.firstName}</td>
            <td>${userDTO.secondName}</td>
        </tr>
        </tbody>
    </table>

    <div>
        <form action="/admin/user/${userDTO.id}" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="checkbox" name="roles" value="CASHIER"
                   <#if userDTO.isCashier()>checked</#if>>CASHIER<Br>
            <input type="checkbox" name="roles" value="SENIOR_CASHIER"
            <#if userDTO.isSeniorCashier()>checked</#if>
            >SENIOR_CASHIER<Br>
            <input type="checkbox" name="roles" value="MERCHANDISER"
            <#if userDTO.isMerchandiser()>checked</#if>
            >MERCHANDISER<Br>
            <input type="checkbox" name="roles" value="ADMIN"
            <#if userDTO.isAdmin()>checked</#if>
            >ADMIN<Br>
            <button type="submit" class="btn btn-small btn-dark">
                <@spring.message "save.message"/>
            </button>
        </form>
    </div>
</@c.common>