<#import "parts/common.ftl" as c>
<#import "parts/checks_display.ftl" as checks>
<#import "/spring.ftl" as spring/>
<@c.common>

    <div class="alert alert-success" role="alert">
        <h4 class="alert-heading">X-Report!</h4>
        <p>Count of payed checks: <strong>${countOfChecks}</strong></p>
        <p>Money received: <strong>${totalSum}</strong></p>
        <hr>
        <button class="btn mb-0"
                data-toggle="collapse"
                data-target="#collapseExample"
                aria-expanded="false"
                aria-controls="collapseExample">
            Show checks
        </button>
    </div>

    <div class="collapse" id="collapseExample">

        <@checks.checks orderType="order-x"/>

        <script src="../js/angular.min.js"></script>
        <script src="../js/check.js"></script>
    </div>

</@c.common>