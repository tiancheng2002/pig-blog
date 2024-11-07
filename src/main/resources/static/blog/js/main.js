$(function(){
    var p=0,

        t=0;

    $(window).scroll(function(){

        p=$(this).scrollTop();

        if(t<p){

            $("#header").css("position","relative");//下滚
            $("#header").css("display","none");

        }else{
            $("#header").css("position","fixed");//上滚
            $("#header").css("display","block");
            $("#header a").css("color","black");
            $("#header").css("background-color","rgba(255,255,255,0.8)");
            $("#header").css("box-shadow","0 5px 6px -5px rgb(133 133 133 / 60%)");
            $("#header").css("z-index","91");
            $("#blog_header_img").attr("src","https://pic.imgdb.cn/item/61c3077b2ab3f51d91280376.png");

        }
        if(p<300){
            $("#header").css("background-color","");
            $("#header a").css("color","white");
            $("#header").css("opacity","1");
            $("#header").css("box-shadow","");
            $("#blog_header_img").attr("src","https://pic.imgdb.cn/item/61c306a02ab3f51d9127cc65.png");
        }

        setTimeout(function(){ t = p ; },0)

    })

    $("#myList").click(function () {
        console.log("点击了")
        $('#siderbar').css("display","block")
    })
    $('#close').click(function(){
        $('#siderbar').css("display","none")
    })

    $("#main_smallcontext").appendAndRemoveCharAnimation([
        "没有失败，只有暂时停止的成功！".split(""),
        "There will be no regret and sorrow if you fight with all your strength".split(""),
        "每一天的努力,总会有一天,你会变成自己想要的样子".split("")
    ], 1500, 100, 200);

})