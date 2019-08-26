<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>
    <form class="form-signin" action="/registration" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <#if success!false>
            <div class="alert alert-success" role="alert">
                <@spring.message "success.message"/>
                <a href="/login"><@spring.message "go.to.login.page.message"/></a>
            </div>
        </#if>

        <div class="form-group">
            <label for="InputEmail"><@spring.message "email.message"/></label>
            <input type="email"
                   class="form-control ${(emailError??)?string('is-invalid','')}"
                   id="InputEmail"
                   placeholder="example@mail.com"
                   name="email"
                   value="<#if userDTO??>${userDTO.email}</#if>">
            <#if emailError??>
                <div class="invalid-feedback">${emailError}</div>
            </#if>
        </div>

        <div class="form-group">
            <label for="InputPassword"><@spring.message "password.message"/></label>
            <input type="password"
                   class="form-control ${(passwordError??)?string('is-invalid','')}"
                   id="InputPassword"
                   placeholder="Password"
                   name="password">
            <#if passwordError??>
                <div class="invalid-feedback">${passwordError}</div>
            </#if>
        </div>

        <div class="form-group">
            <label for="InputFirstName"><@spring.message "first.name.message"/></label>
            <input id="InputFirstName" type="text"
                   class="form-control ${(firstNameError??)?string('is-invalid','')}"
                   name="firstName"
                   placeholder="<@spring.message "placeholder.for.first.name.message"/>"
                   value="<#if userDTO??>${userDTO.firstName}</#if>">
            <#if firstNameError??>
                <div class="invalid-feedback">${firstNameError}</div>
            </#if>
        </div>

        <div class="form-group">
            <label for="InputSecondName"><@spring.message "second.name.message"/></label>
            <input id="InputSecondName" type="text"
                   class="form-control ${(secondNameError??)?string('is-invalid','')}"
                   name="secondName"
                   placeholder="<@spring.message "placeholder.for.second.name.message"/>"
                   value="<#if userDTO??>${userDTO.secondName}</#if>">
            <#if secondNameError??>
                <div class="invalid-feedback">${secondNameError}</div>
            </#if>
        </div>

        <button type="submit" class="btn btn-dark btn-lg btn-block">
            <@spring.message "create.account.message"/>
        </button>
    </form>

    <form class="form-signin mt-3" action="/login">
        <button type="submit" class="btn btn-success btn-lg btn-block">
            <@spring.message "back.button.message"/>
        </button>
    </form>
</@c.common>