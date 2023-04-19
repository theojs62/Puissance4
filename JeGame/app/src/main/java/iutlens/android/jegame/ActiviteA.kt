package iutlens.android.jegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.selects.select
import org.json.JSONObject

class ActiviteA : AppCompatActivity(), View.OnClickListener{

    private lateinit var queueRequetesWeb: RequestQueue
    private lateinit var id_game: TextView
    private lateinit var nom_game: TextView
    private lateinit var annee_game: TextView
    private lateinit var nb_joueurs_min_game: TextView
    private lateinit var nb_joueurs_max_game: TextView
    private lateinit var duree_game: TextView
    private lateinit var image: Button
    private lateinit var actQuitterActA: Button
    private lateinit var les_id: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activitea)
        id_game=findViewById(R.id.id_tv_id)
        nom_game=findViewById(R.id.id_tv_nom)
        annee_game=findViewById(R.id.id_tv_annee_sortie)
        nb_joueurs_min_game=findViewById(R.id.id_tv_joueurs_min)
        nb_joueurs_max_game=findViewById(R.id.id_tv_joueurs_max)
        duree_game=findViewById(R.id.id_tv_duree)
        image = findViewById(R.id.id_bt_image)
        image.setOnClickListener(this)
        actQuitterActA = findViewById(R.id.id_bt_quitter_act_a)
        actQuitterActA.setOnClickListener(this)
        val db = JeuxDB.getDatabase(applicationContext)
        val jeuDAO = db.jeuDao()
        les_id = jeuDAO.select().toTypedArray()
        queueRequetesWeb  = Volley.newRequestQueue(this)
        val id_jeu = intent.getStringExtra("id_game")
        chercheJeuFromAPI(id_jeu.toString())

    }

    fun chercheJeuFromAPI(id_jeu: String){
        Log.i("",id_jeu)
        val requeteHTTP = StringRequest(
            Request.Method.GET,
            "https://bgg-json.azurewebsites.net/thing/" + id_jeu,
            { reponse ->
                val json: JSONObject = JSONObject(reponse)
                var nom = json.getString("name")
                val annee_sortie = json.getString("yearPublished")
                val nb_joueurs_min = json.getString("minPlayers")
                val nb_joueurs_max = json.getString("maxPlayers")
                val duree_jeu = json.getString("playingTime")
                id_game.text = id_jeu
                nom_game.text = nom
                annee_game.text = annee_sortie
                nb_joueurs_min_game.text = nb_joueurs_min
                nb_joueurs_max_game.text = nb_joueurs_max
                duree_game.text = duree_jeu
                if (id_jeu.isNotEmpty() && !les_id.contains(id_jeu.toInt())) {
                    val db = JeuxDB.getDatabase(applicationContext)
                    val jeuDAO = db.jeuDao()
                    jeuDAO.insert(Jeu(id_jeu.toInt(),nom.toString(),annee_sortie.toString().toInt(),nb_joueurs_min.toString().toInt(),nb_joueurs_max.toString().toInt(),duree_jeu.toString().toInt()))
                }
            },
            {
                Log.i("jeu","Une erreur est présente")
            })
        queueRequetesWeb.add(requeteHTTP)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == actQuitterActA.id)
            this.finish()
        if (p0?.id == image.id)
            this.finish()
    }

}