package mx.edu.ittepic.ladm_u2_practica2_benjaminmaximilianozepedaibarra

import android.media.MediaPlayer
import android.provider.MediaStore
import android.widget.ImageView
import kotlinx.coroutines.delay

class Carta(imagen : Int, sonido : Int, vista : ImageView, act : MainActivity) {
    val imagen = imagen
    val sonido = sonido
    val act = act
    var vista = vista

    fun mostrar(){
        vista.setImageResource(imagen)
    }
    fun reproducir() : MediaPlayer{
        var mp = MediaPlayer.create(act,sonido)
        mp.start()
        return mp
    }
}