<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">
<@c.common>

    <form action="/check/product" method="post" class="form-group">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <label for="inputName"><@spring.message "input.code.or.name.message"/></label>
        <div class="row">
            <div class="col-8">
                <input id="inputName" type="text" name="name" class="form-control <#if notFound>is-invalid</#if>">
                <#if notFound>
                    <div class="invalid-feedback"><@spring.message "product.not.found.message"/></div>
                </#if>
            </div>
            <div class="col-4">
                <button class="btn btn-success btn-block"><@spring.message "search.product.message"/></button>
            </div>
        </div>
    </form>


    <#if check??>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th scope="col"><@spring.message "product.name.message"/></th>
                <th scope="col"><@spring.message "product.count.message"/></th>
                <th scope="col"><@spring.message "product.price.message"/></th>
            </tr>
            </thead>
            <tbody>
            <#list check.products as product>
                <tr>

                    <td>
                        ${product.product.name}
                        <#if isSeniorCashier>
                            <form action="/senior_cashier/check/remove_product" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <input type="hidden" value="${product.product.name}" name="name">
                                <button type="submit" class="btn btn-small btn-danger">
                                    Remove
                                </button>
                            </form>
                        </#if>
                    </td>
                    <td>
                        <#if product.product.type == "PIECE_PRODUCT">
                            X${product.countOfProduct}
                        <#else>
                            ${product.countOfProduct} <@spring.message "gram.message"/>
                        </#if>
                    </td>
                    <td>${product.price}</td>
                </tr>
            </#list>
            <tr>
                <td colspan="2"><h6><@spring.message "total.price.message"/></h6></td>
                <td><h6>${check.totalPrice}</h6></td>
            </tr>
            </tbody>
        </table>

        <form action="/close_check" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button class="btn btn-lg btn-success" type="submit"><@spring.message "close.check.message"/></button>
        </form>
    </#if>
</@c.common>