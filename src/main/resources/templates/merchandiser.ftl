<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>

    <button class="btn btn-primary" type="button"
            data-toggle="collapse"
            data-target="#collapseExample"
            aria-expanded="false"
            aria-controls="collapseExample">
        Add product
    </button>
    <br>

    <div class="collapse" id="collapseExample">
        <form action="/merchandiser" method="post" class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <label>Name
                <input type="text" name="name" class="form-control">
            </label>


            <label>Code
                <input type="text" name="code" class="form-control">
            </label>

            <label>Price
                <input type="text" name="price" class="form-control">
            </label>

            <label>Count
                <input type="text" name="count" class="form-control">
            </label>

            <label>Type
                <select name="type" class="form-control">
                    <option value="PIECE_PRODUCT">PIECE_PRODUCT</option>
                    <option value="PRODUCT_BY_WEIGHT">PRODUCT_BY_WEIGHT</option>
                </select>
            </label>

            <button type="submit" class="btn btn-lg btn-danger">Create product</button>
        </form>
    </div>

    <button class="btn btn-primary" type="button"
            data-toggle="collapse"
            data-target="#collapseExample1"
            aria-expanded="false"
            aria-controls="collapseExample">
        Count of products
    </button>
    <div class="collapse <#if notFound!false>show</#if>" id="collapseExample1">
        <form action="/merchandiser/stock" method="post" class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <label>Name
                <input type="text" name="name" class="form-control <#if notFound!false>is-invalid</#if>">
                <#if notFound!false>
                    <div class="invalid-feedback">Not Found</div>
                </#if>
            </label>
            <label>Count of product
                <input type="text" name="countOfProduct" class="form-control">
            </label>
            <button type="submit" class="btn btn-lg btn-danger">Create product</button>
        </form>
    </div>
</@c.common>