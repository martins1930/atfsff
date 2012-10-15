
$(function(){
    
    $('#list').click(function (){
        $.getJSON('list', {}, function (data){        
        });        
        
        
    });
    
    
    $('#post').click(function (){
        var p_contacto = '{"nombre":"martinPOST","fec_contact":1350251709448,"msg":"mensajePOST", "edad":33}' ;
        
        $.ajax({
            dataType: 'json',
            contentType: "application/json",
            url: "add",
            type: 'POST',
            data: p_contacto,
            success: function(data) {
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
               // alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
          }); 
        
        
    });    
    
    
    $('#postV').click(function (){
        var p_contacto = '{"nombre":"martinPOST","fec_contact":1350251709448,"msg":"mensajePOST", "edad":33}' ;
        
        $.ajax({
            dataType: 'json',
            contentType: "application/json",
            url: "addValid",
            type: 'POST',
            data: p_contacto,
            success: function(data) {
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
               // alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
          }); 
        
        
    });      

    
    
    
    
})