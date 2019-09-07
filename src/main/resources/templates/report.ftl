<#import "parts/common.ftl" as c>
<#import "parts/checks_display.ftl" as checks>
<#import "/spring.ftl" as spring/>
<@c.common>

    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">Z-Report!</h4>
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

        <div ng-app="z-order_form" ng-controller="ZCtrl" ng-model="checks"
             ng-init="init(${id})">

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
        <script src="../js/angular.min.js"></script>
        <script src="../js/z-order.js"></script>
    </div>

    <form action="/logout" class="form-inline my-2 my-lg-0" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button class="btn btn-outline-warning my-2 my-sm-0" type="submit">Finish work</button>
    </form>

</@c.common>