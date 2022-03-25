package mx.edu.ittepic.ladm_u2_practica2_benjaminmaximilianozepedaibarra

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import mx.edu.ittepic.ladm_u2_practica2_benjaminmaximilianozepedaibarra.databinding.ActivityMainBinding
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var buenas = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cartas.setImageResource(R.drawable.fondocartas)
        val Cartas =arrayOf(
            Carta(R.drawable.carta1,R.raw.a1,binding.cartas,this),Carta(R.drawable.carta2,R.raw.a2,binding.cartas,this),
            Carta(R.drawable.carta3,R.raw.a3,binding.cartas,this),Carta(R.drawable.carta4,R.raw.a4,binding.cartas,this),
            Carta(R.drawable.carta5,R.raw.a5,binding.cartas,this),Carta(R.drawable.carta6,R.raw.a6,binding.cartas,this),
            Carta(R.drawable.carta7,R.raw.a7,binding.cartas,this),Carta(R.drawable.carta8,R.raw.a8,binding.cartas,this),
            Carta(R.drawable.carta9,R.raw.a9,binding.cartas,this),Carta(R.drawable.carta10,R.raw.a10,binding.cartas,this),
            Carta(R.drawable.carta11,R.raw.a11,binding.cartas,this),Carta(R.drawable.carta12,R.raw.a12,binding.cartas,this),
            Carta(R.drawable.carta13,R.raw.a13,binding.cartas,this),Carta(R.drawable.carta14,R.raw.a14,binding.cartas,this),
            Carta(R.drawable.carta15,R.raw.a15,binding.cartas,this),Carta(R.drawable.carta16,R.raw.a16,binding.cartas,this),
            Carta(R.drawable.carta17,R.raw.a17,binding.cartas,this),Carta(R.drawable.carta18,R.raw.a18,binding.cartas,this),
            Carta(R.drawable.carta19,R.raw.a19,binding.cartas,this),Carta(R.drawable.carta20,R.raw.a20,binding.cartas,this),
            Carta(R.drawable.carta21,R.raw.a21,binding.cartas,this),Carta(R.drawable.carta22,R.raw.a22,binding.cartas,this),
            Carta(R.drawable.carta23,R.raw.a23,binding.cartas,this),Carta(R.drawable.carta24,R.raw.a24,binding.cartas,this),
            Carta(R.drawable.carta25,R.raw.a25,binding.cartas,this),Carta(R.drawable.carta26,R.raw.a26,binding.cartas,this),
            Carta(R.drawable.carta27,R.raw.a27,binding.cartas,this),Carta(R.drawable.carta28,R.raw.a28,binding.cartas,this),
            Carta(R.drawable.carta29,R.raw.a29,binding.cartas,this),Carta(R.drawable.carta30,R.raw.a30,binding.cartas,this),
            Carta(R.drawable.carta31,R.raw.a31,binding.cartas,this),Carta(R.drawable.carta32,R.raw.a32,binding.cartas,this),
            Carta(R.drawable.carta33,R.raw.a33,binding.cartas,this),Carta(R.drawable.carta34,R.raw.a34,binding.cartas,this),
            Carta(R.drawable.carta35,R.raw.a35,binding.cartas,this),Carta(R.drawable.carta36,R.raw.a36,binding.cartas,this),
            Carta(R.drawable.carta37,R.raw.a37,binding.cartas,this),Carta(R.drawable.carta38,R.raw.a38,binding.cartas,this),
            Carta(R.drawable.carta39,R.raw.a39,binding.cartas,this),Carta(R.drawable.carta40,R.raw.a40,binding.cartas,this),
            Carta(R.drawable.carta41,R.raw.a41,binding.cartas,this),Carta(R.drawable.carta42,R.raw.a42,binding.cartas,this),
            Carta(R.drawable.carta43,R.raw.a43,binding.cartas,this),Carta(R.drawable.carta44,R.raw.a44,binding.cartas,this),
            Carta(R.drawable.carta45,R.raw.a45,binding.cartas,this),Carta(R.drawable.carta46,R.raw.a46,binding.cartas,this),
            Carta(R.drawable.carta47,R.raw.a47,binding.cartas,this),Carta(R.drawable.carta48,R.raw.a48,binding.cartas,this),
            Carta(R.drawable.carta49,R.raw.a49,binding.cartas,this),Carta(R.drawable.carta50,R.raw.a50,binding.cartas,this),
            Carta(R.drawable.carta51,R.raw.a51,binding.cartas,this),Carta(R.drawable.carta52,R.raw.a52,binding.cartas,this),
            Carta(R.drawable.carta53,R.raw.a53,binding.cartas,this),Carta(R.drawable.carta54,R.raw.a54,binding.cartas,this))

        this.setTitle("DAR LAS CARTAS DE LA LOTERIA")
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        lateinit var darCartas : CoroutineContext
        binding.dCartas.setOnClickListener {
            buenas=0
            var a : MediaPlayer ?= null
            arregloRandom(Cartas)
            darCartas = scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {
                for (i in Cartas) {
                    runOnUiThread() {
                        i.mostrar()
                        binding.contador.text = "${buenas+1}/54"
                        a = i.reproducir()
                        buenas++
                    }
                    delay(2500L)
                    a!!.release()
                }
            }
            binding.buenas.setVisibility(View.VISIBLE)
            binding.dCartas.setVisibility(View.INVISIBLE)
            binding.revisar.setVisibility(View.INVISIBLE)
            (darCartas as Job).start()
        }
        binding.buenas.setOnClickListener {
            if (darCartas.isActive) {
                darCartas.cancel()
            }
            binding.buenas.setVisibility(View.INVISIBLE)
            binding.revisar.setVisibility(View.VISIBLE)
        }
        binding.revisar.setOnClickListener{
            binding.buenas.setVisibility(View.INVISIBLE)

            var Hilo = Restantes(binding.cartas, buenas, Cartas,this)
            Hilo.start()
        }
    }
    fun arregloRandom(baraja: Array<Carta>): Array<Carta>? {
        var aux: Carta? = null
        var posAr = 0
        for (i in 0 until baraja.size) {
            posAr = Random.nextInt(baraja.size)
            aux = baraja[i]
            baraja[i] = baraja[posAr]
            baraja[posAr] = aux
        }
        return baraja
    }
}

class Restantes(cartas:ImageView, quedo:Int,baraja:Array<Carta>, este:MainActivity) : Thread(){
    var imagenVista = cartas
    var conta = quedo
    val baraja = baraja
    var este = este
    override fun run() {
        super.run()
        while (conta < baraja.size) {
            este.runOnUiThread {
                baraja[conta].reproducir()
                imagenVista.setImageResource(baraja[conta++].imagen)
            }
            sleep(2500)
        }
        este.runOnUiThread {
            este.dCartas.setVisibility(View.VISIBLE)
            este.cartas.setImageResource(R.drawable.fondocartas)
        }
    }
}