<#import "parts/common.ftl" as c>
<#import "parts/pagination.ftl" as p>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">

<@c.common>

    <#if error>
        <div class="alert alert-danger" role="alert"><@spring.message "check.not.found.message"/></div>
    </#if>

    <@p.pagination></@p.pagination>

    <div class="card-columns">
        <#list page.content as check>
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
                                <td>${p.product.name}</td>
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
                        <button type="submit"
                                class="btn btn-small btn-danger"><@spring.message "cancel.check.message"/></button>
                    </form>
                </div>
            </div>
        </#list>
    </div>

    <@p.pagination></@p.pagination>

</@c.common>