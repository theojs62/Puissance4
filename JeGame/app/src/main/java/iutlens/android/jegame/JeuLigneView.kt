package iutlens.android.jegame

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import java.text.SimpleDateFormat

class JeuLigneView {
    companion object{
        fun celluleEnTete(texte:String,context:Context): TextView {
            val cellule= TextView(context)
            cellule.setText(texte)
            cellule.setTextColor(Color.DKGRAY)
            cellule.setBackgroundColor(Color.YELLOW)
            cellule.setPadding(7, 5, 5, 5)
            cellule.setTextSize(20.0F)
            cellule.textAlignment= View.TEXT_ALIGNMENT_CENTER
            return cellule
        }
        fun ligneEnTete(context:Context, vararg textes:String): TableRow {
            val ligne= TableRow(context)
            textes.forEach {ligne.addView(celluleEnTete(it,context)) }
            ligne.setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
            return ligne
        }
        fun celluleNormale(texte:String,context:Context): TextView {
            val cellule= TextView(context)
            cellule.setText(texte)
            cellule.setTextColor(Color.BLUE)
            cellule.setBackgroundColor(Color.WHITE)
            cellule.setPadding(7, 5, 5, 5)
            cellule.setTextSize(20.0F)
            cellule.textAlignment= View.TEXT_ALIGNMENT_CENTER
            return cellule
        }
        fun ligneJeu(jeu:Jeu,context: Context): TableRow {
            val ligne= TableRow(context)
            ligne.addView(celluleNormale(jeu.id.toString(),context))
            ligne.addView(celluleNormale(jeu.nom,context))
            ligne.addView(celluleNormale(jeu.annee_sortie.toString(),context))
            ligne.setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
            return ligne
        }
    }
}
