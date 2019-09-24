<#import "/spring.ftl" as spring/>
<#macro report alert type>
    <div class="alert alert-${alert}" role="alert">
        <h4 class="alert-heading"><@spring.message "${type}"/></h4>
        <p><@spring.message "count.of.closed.checks.message"/> <strong>${countOfChecks}</strong></p>
        <p><@spring.message "money.received.message"/> <strong>${TOTAL_SUM}</strong></p>
        <hr>
        <button class="btn mb-0"
                data-toggle="collapse"
                data-target="#collapseExample"
                aria-expanded="false"
                aria-controls="collapseExample">
            <@spring.message "show.checks.message"/>
        </button>
    </div>
</#macro>