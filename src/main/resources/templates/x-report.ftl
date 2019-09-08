<#import "parts/common.ftl" as c>
<#import "parts/checks_display.ftl" as checks>
<#import "parts/pagination.ftl" as page>
<#import "parts/report.ftl" as report>
<#import "/spring.ftl" as spring/>
<@c.common>

    <@report.report alert="success" type="x-report.message"/>

    <div class="collapse" id="collapseExample">
        <div ng-app="check_form" ng-controller="CheckCtrl" ng-model="checks"
             ng-init="init(${id},0)">

            <@page.pagination/>
            <@checks.checks/>
        </div>
        <script src="../js/angular.min.js"></script>
        <script src="../js/check.js"></script>
    </div>

</@c.common>