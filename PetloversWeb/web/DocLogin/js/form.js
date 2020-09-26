(function(){
    $(document).ready(function(){
        $('.alt-form').click(function(){
            $('.form-content').animate({
                height: "toggle",
                opacity: 'toggle'
            }, 600);
        });
        $('.login100-form-btn').click(function(){
            ingresarsi();
        });

        let formRegistro = document.getElementsByName('form-input');
        for (let i = 0; i < formRegistro.length; i++) {
            formRegistro[i].addEventListener('blur', function(){
                if (this.value.length >= 1) {
                    this.nextElementSibling.classList.add('active');
                    this.nextElementSibling.classList.remove('error');
                } else if (this.value.length = " ") {
                    this.nextElementSibling.classList.add('error');
                    this.nextElementSibling.classList.remove('active');
                } else {
                    this.nextElementSibling.classList.remove('active');
                }
            })
        }

    })
}())


var usuario=document.getElementById('first-name').value;
var contrasena=document.getElementById('first-pasword');
var usuarios=["juan","brayan","laura"];
var contrasenainterna=["jjj","bbb","lll"];


function ingresarsi(){

    alert(usuario);
    if (usuario.value==='' && contrasena.value==='') {


     swal({
       title: "Algo salio mal",
       text: "El nombre y la contrasena estan vacios",
       icon: "warning",
       button: "Listo",
   });
 }else if(usuario.value=== null || usuario.value==='') {

    swal({
       title: "Algo salio mal",
       text: "El nombre esta vacio",
       icon: "warning",
       button: "Listo",
   });
}else if (contrasena.value=== null || contrasena.value==='') {

    swal({
       title: "Algo salio mal",
       text: "La contrasena esta vacia",
       icon: "warning",
       button: "Listo",
   });

}else if (contrasena.value!='' && usuario.value!='') {

    var usuariofinal= usuario.value;
    var itera=5;

    for (var i = -1; usuariofinal!=usuarios[i]; i++) {


        var nombre;
        var decir=  swal({
           title: "Listo",
           text: "Bienvenido a A Y T information" + nombre,
           icon: "success",
           button: "Listo",
       });


        if (contrasena.value==1007400892){
            decir;
            nombre="Juan";
            location.href="AdminBSBMaterialDesign/index.html";

        }else if (contrasena.value==12345){
            decir;
            nombre="Brayan";
        }else if (contrasena.value==1002521673){
            decir;
            nombre="Laura";
        }
        else{

           swal({
               title: "Algo salio mal",
               text: "La contraseña esta mal",
               icon: "warning",
               button: "Listo",
           });

       }

       /*if (usuariofinal===usuariofinal[i]){

        swal({
           title: "Listo",
           text: "Listo tus crendenciales son correctas",
           icon: "success",
           button: "Listo",
       });

       }
       else {swal({
           title: "Listo",
           text: "Listo tus crendenciales son incorrectas",
           icon: "success",
           button: "Listo",
       });
   }*/
   console.log(i);
}

}

}