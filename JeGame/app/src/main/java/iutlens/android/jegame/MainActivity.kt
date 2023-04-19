package iutlens.android.jegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener,
    ActivityResultCallback<ActivityResult> {

    private lateinit var id_game: EditText
    private lateinit var actA: Button
    private lateinit var viderTable: Button
    private lateinit var actB: Button
    private lateinit var actC: Button
    private lateinit var actQuitter: Button
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        id_game=findViewById(R.id.id_et_id_game)
        actA = findViewById(R.id.id_bt_act_a)
        actA.setOnClickListener(this)
        viderTable = findViewById(R.id.id_bt_vider_table)
        viderTable.setOnClickListener(this)
        actB = findViewById(R.id.id_bt_act_b)
        actB.setOnClickListener(this)
        actQuitter = findViewById(R.id.id_bt_quitter)
        actQuitter.setOnClickListener(this)
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), this)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == actQuitter.id)
            finish()
        if (p0?.id == actA.id) {
            val intent = Intent(this, ActiviteA::class.java)
            intent?.putExtra("id_game",id_game.text.toString())
            activityResultLauncher.launch(intent)
        }
        if (p0?.id == actB.id){
            val monIntent: Intent = Intent(this, ActiviteB::class.java)
            startActivity(monIntent)
        }
        if (p0?.id == viderTable.id){
            val db = JeuxDB.getDatabase(applicationContext)
            val jeuDAO = db.jeuDao()
            jeuDAO.delete()
        }
    }

    override fun onActivityResult(result: ActivityResult?) {
    }

}