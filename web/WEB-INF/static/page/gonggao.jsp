<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/12
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--        LOGO--%>
<div class="col-md-offset-4">
    <p class="m-text-thin m-text-spaced m-text-lined m-opacity-tiny">Copyright @2018-2022 PengXin Designed by PengXin</p>
</div>

<%--将公用的js放在此处--%>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type="text/javascript">

    //恢复下拉菜单可点击
    $(document).ready(function(){
        $(document).off('click.bs.dropdown.data-api');
    });


    //鼠标悬停显示
    $(document).ready( function (){
        dropdownOpen(); //调用
    });
    /**
     * 鼠标划过就展开子菜单，免得需要点击才能展开
     */
    function dropdownOpen() {

        var $dropdownLi = $( 'li.dropdown' );

        $dropdownLi.mouseover( function () {
            $( this ).addClass( 'open' );
        }).mouseout( function () {
            $( this ).removeClass( 'open' );
        });
    }
</script>




