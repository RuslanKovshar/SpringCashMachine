<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">

<@c.common>

    <div class="card-columns">
        <#list checks as check>
        <div class="card border-dark mt-4">
            <div class="card-header">
                <@spring.message "check.number"/> ${check.id}
            </div>
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><@spring.message "name.message"/></th>
                        <th scope="col"><@spring.message "product.price.message"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list check.products as p>
                    <tr>
                        <td>${p?index + 1}</td>
                        <#if .locale == "ua">
                            <td>${p.product.nameUA}</td>
                        <#else >
                            <td>${p.product.nameEN}</td>
                        </#if>
                        <td>${p.price}</td>
                    </tr>
                    </#list>
                    <tr>
                        <td colspan="2"><h6><@spring.message "total.price.message"/></h6></td>
                        <td>${check.totalPrice}</td>
                    </tr>
                    </tbody>
                </table>
                <form action="/senior_cashier/cancel_check?id=${check.id}"
                      method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="btn btn-small btn-danger"><@spring.message "cancel.check.message"/></button>
                </form>
            </div>
        </div>
        </#list>
    </div>

</@c.common>