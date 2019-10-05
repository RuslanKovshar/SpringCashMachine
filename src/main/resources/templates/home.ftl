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

    <#if isCashier>
        <form action="/open_check">
            <button class="btn btn-lg btn-success btn-block mt-2"><@spring.message "open.check.message"/></button>
        </form>
    </#if>

    <#if isMerchandiser>
        <form action="/merchandiser">
            <button class="btn btn-lg btn-success btn-block mt-2"><@spring.message "merchandiser.menu.message"/></button>
        </form>
    </#if>

    <#if isSeniorCashier>

        <form action="/senior_cashier/all_checks">
            <button type="submit" class="btn btn-success btn-lg btn-block mt-2">
                <@spring.message "all.checks.message"/>
            </button>
        </form>

        <form action="/senior_cashier/x-report">
            <button type="submit" class="btn btn-success btn-lg btn-block mt-2">
                <@spring.message "x-report.message"/>
            </button>
        </form>

        <form action="/senior_cashier/z-report">
            <button type="submit" class="btn btn-success btn-lg btn-block mt-2">
                <@spring.message "z-report.message"/>
            </button>
        </form>
    </#if>

    <#if isAdmin>
        <form action="/admin/users">
            <button type="submit" class="btn btn-success btn-lg btn-block mt-2">
                <@spring.message "users.message"/>
            </button>
        </form>
    </#if>
</@c.common>