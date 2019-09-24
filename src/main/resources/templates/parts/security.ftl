<#assign
authenticated = Session.SPRING_SECURITY_CONTEXT??
>

<#if authenticated>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isCashier = user.isCashier()
    isMerchandiser = user.isMerchandiser()
    isSeniorCashier = user.isSeniorCashier()
    >
</#if>
