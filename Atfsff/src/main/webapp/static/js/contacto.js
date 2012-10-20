
$(function(){
    
    _.templateSettings = {
        interpolate : /\{\{(.+?)\}\}/g
    };    

    require(['text!/Atfsff/static/templates/view_obj.html'],
        function(app){
            $('#dyn_req').html(app);
        }
    ); 
    

    $('#list').click(function (){
        $.getJSON('/Atfsff/contact', {}, function (data){        
            });        
        
        
    });
    
    
    $('#post').click(function (){
        var p_contacto = '{"nombre":"martinPOST","fec_contact":1350251709448,"msg":"mensajePOST", "edad":33}' ;
        
        $.ajax({
            dataType: 'json',
            contentType: "application/json",
            url: "/Atfsff/contact",
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
            url: "/Atfsff/contact",
            type: 'PUT',
            data: p_contacto,
            success: function(data) {
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
            // alert(jqXHR + " : " + textStatus + " : " + errorThrown);
            }
        }); 
        
        
    });      

    
    /*
     *MODELO
     **/
    var Contacto = Backbone.Model.extend({
        idAttribute: "nombre",    
        defaults: {            
            nombre : 'nombre' , 
            edad : 1, 
            fec_contact : 1, 
            msg : 'mensaje default'           
        },
        initialize: function(){
            console.log('this model has been initialized');
            this.on("change:nombre", function(){
                var nom = this.get("nombre");
                console.log('nombre updated to ' + nom);
            });
            this.bind("error", function (model, error) {
                console.log("desdetrg "+error);
            });
            
        },
        validate: function(attribs){
            if(attribs.nombre === undefined){
                return "Remember to set a nombre for your contact!";
            }
        }        
        
    });
    
    var someContact = new Contacto({
        nombre: "cacho", 
        edad:12
    });
  
    
    someContact.set("nombre", "nombre_cambiado");
    someContact.set({
        nombre: "nombre_nuevo"
    });
    someContact.set({
        nombre: undefined
    });
    
    
    /*
     *VISTAA
     *
     **/
    var ContactoSearchView = Backbone.View.extend({
        el: $('#vista'),
        render: function( event ){
            var compiled_template = _.template( $("#results-template").html() );
            this.$el.html( compiled_template( { coso : 'pepeito'+atfsff.state.test } ) );
            return this; //recommended as this enables calls to be chained.
        },
        events: {
            "click a": "apretoA"
        },
        apretoA: function( event ){
            alert('se apreto el link!!');
        }
    });
    
    var vista1 = new ContactoSearchView();
    vista1.render();      
    
    /*
    * COLECCION
    */   
    var ContactoCollection = Backbone.Collection.extend({
        model: Contacto,
        url: '/Atfsff/contact' 
    });
    
    var cc = new ContactoCollection() ;
    
    cc.on("add", function(cont) {
        console.log("I liked " + cont.get("nombre") + ' its this one, right? '+ cont.get("edad"));
    });
    
//    cc.add([{nombre: 'pepe'},{nombre:'teto'}]);
    
    cc.fetch({ data: $.param({ offset: 30}) });
    
    cc.create({nombre : 'nuevoPOST'});
    
    /*
     *ROUTERS________________________________
     *
     */
    
    var ContactRouter = Backbone.Router.extend({
        routes:{
            "about" : "mostrarAbaut",            
            "contacto/:id" : "obtcontactos"
        },
        mostrarAbaut : function(){
            // http://localhost:8091/Atfsff/contact/page#about
            console.log('about!!');
        },
        obtcontactos : function(id){
            console.log('id de contacto: '+id);
        }
    });
    
    var ccR = new ContactRouter() ;
    
    Backbone.history.start();  
    // ccR.navigate('contacto/3455'); // esto es para modificar la URL
    
})

/*
 *ver en rino.js:
 *java -cp ~/.gradle/caches/artifacts-14/filestore/rhino/js/1.7R2/jar/b95d5212ff4cea92cee1c3c6fa50aa82c9d4905b/js-1.7R2.jar org.mozilla.javascript.tools.shell.Main
 *js> load(["src/main/webapp/static/js/ext/underscore-min.js"])
 *js> var compiled = _.template("hello: <%= name %>");
 *js> compiled({name : 'moe'});
 *hello: moe
 **/

