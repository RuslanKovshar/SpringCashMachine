<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>

    <form action="/product/add/${product.id}" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="row">
            <div class="col-4">
                <h2><em><@spring.message "name.message"/></em></h2>
            </div>
            <div class="col-8">
                <h2>${product.name}</h2>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <#if product.type == "PIECE_PRODUCT">
                    <h2><em><@spring.message "price.for.one.product.message"/></em></h2>
                <#else>
                    <h2><em><@spring.message "price.for.one.kg.message"/></em></h2>
                </#if>
            </div>
            <div class="col-8">
                <h2>${product.price}</h2>
            </div>
        </div>

        <#if product.type == "PIECE_PRODUCT">
            <label for="inputNumber"><@spring.message "input.count.message"/></label>
        <#else>
            <label for="inputNumber"><@spring.message "input.weight.message"/></label>
        </#if>
        <div class="row">
            <div class="col-8">
                <input type="number" id="inputNumber" name="number" min="1" required class="form-control <#if error>is-invalid</#if>">
                <#if error>
                    <div class="invalid-feedback"><@spring.message "not.enough.product.message"/></div>
                </#if>
            </div>
            <div class="col-4">
                <button class="btn btn-success btn-block"><@spring.message "add.product.message"/></button>
            </div>
        </div>
    </form>

</@c.common>