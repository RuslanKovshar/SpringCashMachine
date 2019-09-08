<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>

    <button class="btn btn-primary btn-block" type="button"
            data-toggle="collapse"
            data-target="#collapseExample"
            aria-expanded="false"
            aria-controls="collapseExample">
        <@spring.message "create.new.product.message"/>
    </button>
    <br>

    <div class="collapse" id="collapseExample">
        <form action="/merchandiser" method="post" class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="row">
                <div class="col">
                    <label for="inputNameUA"><@spring.message "name.message"/>UA</label>
                    <input id="inputNameUA" type="text" name="nameUA" class="form-control">
                </div>
                <div class="col">
                    <label for="inputNameEN"><@spring.message "name.message"/>EN</label>
                    <input id="inputNameEN" type="text" name="nameEN" class="form-control">
                </div>
            </div>

            <label for="inputCode"><@spring.message "code.message"/></label>
            <input id="inputCode" type="text" name="code" class="form-control">

            <label for="inputPrice"><@spring.message "product.price.message"/></label>
            <input id="inputPrice" type="text" name="price" class="form-control">

            <label for="inputCount"><@spring.message "count.on.stock.message"/></label>
            <input id="inputCount" type="text" name="count" class="form-control">

            <label for="inputType"><@spring.message "type.message"/></label>
            <select id="inputType" name="type" class="form-control">
                <option value="PIECE_PRODUCT"><@spring.message "count.product.message"/></option>
                <option value="PRODUCT_BY_WEIGHT"><@spring.message "weight.product.message"/></option>
            </select>

            <button type="submit" class="btn btn-danger btn-block mt-2"><@spring.message "create.btn.message"/></button>
        </form>
    </div>

    <button class="btn btn-primary btn-block" type="button"
            data-toggle="collapse"
            data-target="#collapseExample1"
            aria-expanded="false"
            aria-controls="collapseExample">
        <@spring.message "add.products.to.stock.message"/>
    </button>
    <div class="collapse <#if notFound>show</#if>" id="collapseExample1">
        <form action="/merchandiser/stock" method="post" class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">


            <label for="inputName1"><@spring.message "input.code.or.name.message"/></label>
            <input id="inputName1" type="text" name="name" class="form-control <#if notFound>is-invalid</#if>">

            <#if notFound>
                    <div class="invalid-feedback"><@spring.message "product.not.found.message"/></div>
            </#if>

            <label for="inputCount1"><@spring.message "product.count.message"/></label>
            <input id="inputCount1" type="text" name="countOfProduct" class="form-control">

            <button type="submit" class="btn btn-lg btn-danger btn-block mt-2"><@spring.message "create.btn.message"/></button>
        </form>
    </div>
</@c.common>