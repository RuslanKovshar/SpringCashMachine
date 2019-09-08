<#import "/spring.ftl" as spring/>
<#import "pagination.ftl" as pagination>
<#macro checks>

        <div class="card-columns">
            <div ng-repeat="check in checks">
                <div class="card border-dark mt-4">
                    <div class="card-header">
                        <@spring.message "check.number"/> {{check.id}}
                    </div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col"><@spring.message "name.message"/></th>
                                <th scope="col"><@spring.message "product.price.message"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="p in check.products">
                                <td>{{$index + 1}}</td>
                                <#if .locale == "ua">
                                    <td>{{p.product.nameUA}}</td>
                                <#else >
                                    <td>{{p.product.nameEN}}</td>
                                </#if>
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
</#macro>