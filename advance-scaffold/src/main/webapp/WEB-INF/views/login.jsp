<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/common/include.jsp" %>
    <link rel="stylesheet" href="${root}/static/style/css/login.css" type="text/css">
    <TITLE>登录页面</TITLE>

    <script>
        $(function(){
            var sessionInfo_userId = '${sessionInfo.id}';
            if (sessionInfo_userId) {//如果登录,直接跳转到index页面
                window.location.href = '${root}/welcome/index';
            }

            $('#loginform').form({
                url: '${root}/welcome/login',
                onSubmit: function () {
                    progressLoad();
                    var isValid = $(this).form('validate');
                    if (!isValid) {
                        progressClose();
                    }
                    return isValid;
                },
                success: function (result) {
                    result = $.parseJSON(result);
                    progressClose();
                    if (result.success) {
                        window.location.href = '${root}/welcome/index';
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: '<div class="light-info"><div class="light-tip icon-tip"></div><div>' + result.msg + '</div></div>',
                            showType: 'show'
                        });
                        //刷新验证码
                       // changeImg();
                    }
                }
            });

            //得到焦点
            $("#password").focus(function(){
                $("#left_hand").animate({
                    left: "150",
                    top: " -38"
                },{step: function(){
                    if(parseInt($("#left_hand").css("left"))>140){
                        $("#left_hand").attr("class","left_hand");
                    }
                }}, 2000);
                $("#right_hand").animate({
                    right: "-64",
                    top: "-38px"
                },{step: function(){
                    if(parseInt($("#right_hand").css("right"))> -70){
                        $("#right_hand").attr("class","right_hand");
                    }
                }}, 2000);
            });
            //失去焦点
            $("#password").blur(function(){
                $("#left_hand").attr("class","initial_left_hand");
                $("#left_hand").attr("style","left:100px;top:-12px;");
                $("#right_hand").attr("class","initial_right_hand");
                $("#right_hand").attr("style","right:-112px;top:-12px");
            });
        });

        function submitForm() {
            $('#loginform').submit();
        }

        function clearForm() {
            $('#loginform').form('clear');
        }

        //回车登录
        document.onkeydown = function mykeyDown(e) {
            //compatible IE and firefox because there is not event in firefox
            e = e || event;
            if (e.keyCode == 13) {
                e.returnValue = false;
                e.cancel = true;
                $('#loginform').submit();
            }

        };
      /*  function changeImg() {
            $("#checkcode").val("验证码");
            $("#checkImg").attr("src", "${root}/welcome/checkImg?" + new Date().getTime());
        }*/
    </script>
</head>

<body>
<DIV class="top_div"></DIV>
<form method="post" name="myform" id="loginform">

    <DIV style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
        <DIV style="width: 165px; height: 96px; position: absolute;">
            <DIV class="tou"></DIV>
            <DIV class="initial_left_hand" id="left_hand"></DIV>
            <DIV class="initial_right_hand" id="right_hand"></DIV>
        </DIV>
        <P style="padding: 30px 0px 10px; position: relative;"><SPAN
                class="u_logo"></SPAN>
            <INPUT class="ipt" type="text" name="loginName" id="username" placeholder="请输入用户名或邮箱" value="">
        </P>

        <P style="position: relative;"><SPAN class="p_logo"></SPAN>
            <INPUT class="ipt" id="password" name="password" type="password" placeholder="请输入密码" value="">
        </P>

        <DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 0px; border-top-style: solid;">
            <P style="margin: 0px 35px 20px 45px;">
               <SPAN style="align:center;">
                  <A style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
                     href="javascript:void(0);" onclick="submitForm();">登录</A>
               </SPAN></P></DIV>
    </DIV>
</form>
</body>
</html>