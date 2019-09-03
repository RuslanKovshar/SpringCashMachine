<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>
    <#list checks as check>
        <p>ID: ${check.id}</p>
        <#list check.products as product>
            <p>Name: ${product.product.name}</p>
            <p>Price: ${product.product.price}</p>
            <p>Code: ${product.product.code}</p>
            <p>Price: ${product.price}</p>
            <p>Value: ${product.value}</p>
        </#list>
    </#list>
</@c.common>