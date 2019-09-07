<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.common>

    <form action="/checks">
        <button type="submit" class="btn btn-success btn-lg btn-block">
            checks
        </button>
    </form>

    <form action="/x-report">
        <button type="submit" class="btn btn-success btn-lg btn-block">
            X-Report
        </button>
    </form>
</@c.common>