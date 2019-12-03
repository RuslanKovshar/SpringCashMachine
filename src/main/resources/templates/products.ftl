<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Code</th>
            <th scope="col">Price</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.code}</td>
                <td>${product.price}</td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.common>