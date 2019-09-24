<#import "parts/common.ftl" as c>
<#import "parts/checks_display.ftl" as checks>
<#import "parts/report.ftl" as report>
<#import "/spring.ftl" as spring/>
<@c.common>

    <#--<@report.report alert="warning" type="z-report.message"/>-->

    <div class="collapse" id="collapseExample">

        <div ng-app="z-order_form" ng-controller="ZCtrl" ng-model="checks"
             ng-init="init(${id})">
            <@checks.checks/>
        </div>
        <script src="../js/angular.min.js"></script>
        <script src="../js/z-order.js"></script>
    </div>

    <form action="/logout" class="form-inline my-2 my-lg-0" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><@spring.message "finish.work.message"/></button>
    </form>

</@c.common>