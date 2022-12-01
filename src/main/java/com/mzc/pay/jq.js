$(function (){
   $('apibtn').click(function (){
       $.ajax({
           url:'/cls/jq/kakaopay.cls',
           dataType:'json',
           success:function (data){
               alert(data.tid);
               var box = data.next_redirect_pc_url
               window.open(box);
           },
           error:function (error){
               alert(error);
           }
       });
   });
});

<button id="apibtn">apibtn</button>