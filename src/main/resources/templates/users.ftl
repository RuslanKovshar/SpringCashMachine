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
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.secondName}</td>
                <td>
                    <form action="/admin/user/${user.id}">
                        <button type="submit" class="btn btn-small btn-dark"><@spring.message "edit.message"/></button>
                    </form>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</@c.common>