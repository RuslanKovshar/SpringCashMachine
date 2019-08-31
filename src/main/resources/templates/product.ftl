<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>


    <#if product??>
        <form action="/product/add/${product.id}" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div><p>${product.name}</p></div>
            <#if product.class.name == 'ruslan.kovshar.final_project.entity.CountProduct'>
                <label for="inputNumber">Enter count</label>
            <#else>
                <label for="inputNumber">Enter weight</label>
            </#if>
            <input type="number" id="inputNumber" name="number">
            <button class="btn btn-lg btn-success">Add product</button>
        </form>
    <#else>
        <form action="/product" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <label>Name
                <input type="text" name="name">
            </label>
            <button class="btn btn-lg btn-success">Search product</button>
        </form>
    </#if>

    <#if !check.empty>
        <div class="alert alert-success" role="alert">
            <#list check.products as product, price>

                    <p>Name: ${product.name}</p>
                    <p>Price:${price} </p>

            </#list>
        </div>
        <form action=""></form>
        <button class="btn btn-lg btn-success">Close check</button>
        <#else >

    </#if>
</@c.common>