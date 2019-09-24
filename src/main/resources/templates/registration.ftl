<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>

    <#if exist!false>
        <div class="alert alert-danger" role="alert">
            <@spring.message "user.with.email.message"/><i>${createUserDTO.email}</i> <@spring.message "exist.message"/>
        </div>
    </#if>

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
                   value="<#if createUserDTO??>${createUserDTO.email}</#if>">
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

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="InputFirstNameUA"><@spring.message "first.name.message"/>UA</label>
                    <input id="InputFirstNameUA" type="text"
                           class="form-control ${(firstNameUAError??)?string('is-invalid','')}"
                           name="firstNameUA"
                           placeholder="<@spring.message "placeholder.for.first.name.message"/>"
                           value="<#if createUserDTO??>${createUserDTO.firstNameUA}</#if>">
                    <#if firstNameUAError??>
                        <div class="invalid-feedback">${firstNameUAError}</div>
                    </#if>
                </div>
            </div>

            <div class="col">
                <div class="form-group">
                    <label for="InputFirstNameEN"><@spring.message "first.name.message"/>EN</label>
                    <input id="InputFirstNameEN" type="text"
                           class="form-control ${(firstNameENError??)?string('is-invalid','')}"
                           name="firstNameEN"
                           placeholder="<@spring.message "placeholder.for.first.name.message"/>"
                           value="<#if createUserDTO??>${createUserDTO.firstNameEN}</#if>">
                    <#if firstNameENError??>
                        <div class="invalid-feedback">${firstNameENError}</div>
                    </#if>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="InputSecondNameUA"><@spring.message "second.name.message"/>UA</label>
                    <input id="InputSecondNameUA" type="text"
                           class="form-control ${(secondNameUAError??)?string('is-invalid','')}"
                           name="secondNameUA"
                           placeholder="<@spring.message "placeholder.for.second.name.message"/>"
                           value="<#if createUserDTO??>${createUserDTO.secondNameUA}</#if>">
                    <#if secondNameUAError??>
                        <div class="invalid-feedback">${secondNameUAError}</div>
                    </#if>
                </div>
            </div>

            <div class="col">
                <div class="form-group">
                    <label for="InputSecondNameEN"><@spring.message "second.name.message"/>EN</label>
                    <input id="InputSecondNameEN" type="text"
                           class="form-control ${(secondNameENError??)?string('is-invalid','')}"
                           name="secondNameEN"
                           placeholder="<@spring.message "placeholder.for.second.name.message"/>"
                           value="<#if createUserDTO??>${createUserDTO.secondNameEN}</#if>">
                    <#if secondNameENError??>
                        <div class="invalid-feedback">${secondNameENError}</div>
                    </#if>
                </div>
            </div>
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