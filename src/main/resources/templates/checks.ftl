<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>

    <div ng-app="check_form" ng-controller="CheckCtrl" ng-model="checks"
         ng-init="init(${id},0)">

        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <div ng-repeat="p in [].constructor(page.totalPages) track by $index">
                    <li class="page-item">
                        <button class="page-link" ng-click="init(${id},$index)">{{$index + 1}}</button>
                    </li>
                </div>
            </ul>
        </nav>

        <div class="card-columns">
            <div ng-repeat="check in checks">
                <div class="card border-dark mt-4">
                    <div class="card-header">
                        Check number: {{check.id}}
                    </div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="p in check.products">
                                <td>{{$index + 1}}</td>
                                <td>{{p.product.name}}</td>
                                <td>{{p.price}}</td>
                            </tr>
                            <tr>
                                <td colspan="2"><h6><@spring.message "total.price.message"/></h6></td>
                                <td>{{check.totalPrice}}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="js/angular.min.js"></script>
    <script src="js/check.js"></script>
</@c.common>