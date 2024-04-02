function carregar(){
    var msg = window.document.getElementById('msg')
    var img = window.document.getElementById('imagem')
    var data = new Date()
    var hora = data.getHours()
    // var hora = 1;
    msg.innerHTML = `Agora são ${hora} horas.`
    if(hora >= 0 && hora < 12 ){
        //Bom dia
        img.src = 'fotomanha.png'
        document.body.style.background = '#f5d05d'
        msg.innerHTML =  `Agora são ${hora} horas. Bom Dia!`
        if(hora == 1){
            msg.innerHTML =  `Agora são ${hora} hora. Bom Dia!`
        }
    } else if(hora >= 12 && hora < 18){
        //Boa tarde
        img.src = 'fototarde.png'
        document.body.style.background = '#ee7611'
        msg.innerHTML =  `Agora são ${hora} horas. Boa Tarde!`
    } else{
        //Boa noite
        img.src = 'fotonoite.png'
        document.body.style.background = '#15262c'
        msg.innerHTML =  `Agora são ${hora} horas. Boa Noite!`
    }
}
