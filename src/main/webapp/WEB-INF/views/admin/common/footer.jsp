<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- basic scripts -->

<!--[if !IE]> -->

<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script> -->

<!-- <![endif]-->

<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='" + "<%=request.getContextPath()%>/assets/js/jquery-2.0.3.min.js" + "'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='" + " <%=request.getContextPath()%>/assets/js/jquery-1.10.2.min.js" + "'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='" + "<%=request.getContextPath()%>/assets/js/jquery.mobile.custom.min.js" +"'>"+"<"+"/script>");
</script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<script src="<%=request.getContextPath()%>/assets/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.dataTables.bootstrap.js"></script>

<!-- ace scripts -->

<script src="<%=request.getContextPath()%>/assets/js/ace-elements.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/ace.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/yy.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    ;(function($){
        var common_function = function()
        {
            var self = this;
            this.body = $(document.body);
            this.body.delegate(".blog_exit", "click", function(){
                if(confirm("确定退出？")){
                    self.logout();
                }
            });
            // this.test();
        };
        common_function.prototype = {
            test:function ()
            {
                var self = this;
                yy_init("common");
            },
            logout:function()
            {
                var self = this;
                $.ajax({
                    url:"<%=request.getContextPath()%>/login/loginOut",
                    type:"GET",
                    dataType:"json",
                    data:{},
                    success:function(data)
                    {
                        if(data.text == "ok"){
                            location.reload();
                        }else{
                            yy_init("系统错误，请稍后再试");
                        }
                    },
                    error:function(data, status, e){
                        console.log(e);
                    }
                });
            }
        },
            window['common_function'] = common_function;
    })(jQuery);
    $(function(){
        var common = new common_function();
    });
</script>