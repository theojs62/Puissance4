package iutlens.android.jegame

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import iutlens.android.jegame.JeuLigneView.Companion.ligneEnTete


class ActiviteB : AppCompatActivity(), View.OnClickListener {

    private lateinit var bt_quitter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activite_b)
        bt_quitter=findViewById(R.id.id_bt_quitter_lister_jeux)
        bt_quitter.setOnClickListener(this)
        remplirTable()
    }
    override fun onClick(v: View) {
        if (v.id==R.id.id_bt_quitter_lister_jeux) {
            finish()
        }
    }

    fun remplirTable() {
        val table: LinearLayout =findViewById(R.id.id_table_lister_produits)
        table.addView(ligneEnTete(this,"id","nom","annee de sortie"))
        val db = JeuxDB.getDatabase(applicationContext)
        val jeux=db.jeuDao().listeJeux()
        for (jeu in jeux)
            table.addView(JeuLigneView.ligneJeu(jeu, this))
    }
}
