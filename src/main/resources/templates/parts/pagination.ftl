<#macro pagination>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <div ng-repeat="p in [].constructor(page.totalPages) track by $index">
            <li class="page-item">
                <button class="page-link" ng-click="init(${id},$index)">{{$index + 1}}</button>
            </li>
        </div>
    </ul>
</nav>
</#macro>