<#macro pagination>
    <#if page.totalPages gt 0 >
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <#list 1..page.totalPages as pageNumber>
                    <li class="page-item">
                        <a class="page-link" href="?page=${pageNumber - 1}">${pageNumber}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </#if>
</#macro>