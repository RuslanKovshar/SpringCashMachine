<#import "/spring.ftl" as spring/>
<#import "parts/common.ftl" as c>
<@c.common>
<div class="container mt-5" style="width: 40%">
    <form class="form-signin" method="post" action="/login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <h2 class="form-signin-heading"><@spring.message "welcome.message"/></h2>
        <#if error>
            <div class="alert alert-danger" role="alert"><@spring.message "after.error.message"/></div>
        </#if>
        <#if logout>
            <div class="alert alert-success" role="alert"><@spring.message "after.logout.message"/></div>
        </#if>
        <p>
            <label for="username" class="sr-only">Email</label>
            <input type="text" id="username" name="username" class="form-control"
                   placeholder="Email"
                   pattern="([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.]{2,6})"
                   title="Must look like example@mail.com"
                   required
                   autofocus>
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                   required>
        </p>


        <label for="remember-me"><@spring.message "remember.me.message"/></label>
        <input id="remember-me" type="checkbox" name="remember-me" value="true"/>


        <button class="btn btn-lg btn-dark btn-block" type="submit"><@spring.message "login.message"/></button>
    </form>
    <form class="form-signin mt-3" action="/registration">
        <button class="btn btn-lg btn-success btn-block" type="submit"><@spring.message "create.account.message"/></button>
    </form>
</div>
</@c.common>