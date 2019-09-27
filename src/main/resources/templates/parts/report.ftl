<#import "/spring.ftl" as spring/>
<#macro report alert type>
    <div class="alert alert-${alert}" role="alert">
        <h4 class="alert-heading"><@spring.message "${type}"/></h4>
        <p><@spring.message "count.of.closed.checks.message"/> <strong>${countOfChecks}</strong></p>
        <p><@spring.message "money.received.message"/> <strong>${totalSum}</strong></p>
    </div>
</#macro>