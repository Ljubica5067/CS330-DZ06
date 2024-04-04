package rs.ac.metropolitan.cs330_dz06_1

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import rs.ac.metropolitan.cs330_dz06_1.ui.theme.CS330DZ061Theme


class MainActivity : AppCompatActivity() {
    private var faks: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1=findViewById<Button>(R.id.button)
        val btn2=findViewById<Button>(R.id.button2)

        val yes=findViewById<Button>(R.id.btnyes)
        val no=findViewById<Button>(R.id.btnno)


        btn1.setOnClickListener{
            Toast.makeText(this, "Univerzitet Metropolitan",Toast.LENGTH_LONG).show()
        }

        btn2.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=Univerzitet+Metropolitan")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        yes.setOnClickListener{
            confirm(true)
        }

        no.setOnClickListener{
            confirm(false)
        }
    }

    private fun confirm(selected:Boolean)
    {
        if (faks==null) {
            Toast.makeText(this, "Molimo izaberite fakultet", Toast.LENGTH_SHORT).show()
            return
        }
        val message: String
        message = if (selected) {
            "Vi ste student fakulteta $faks"
        } else {
            "Niste student fakulteta $faks"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        val txt=findViewById<TextView>(R.id.tvRotation)
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            txt.setText("Rotacija ekrana: Pejzažna")
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            txt.setText("Rotacija ekrana: Normalna")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId)
        {
            R.id.fit->
                {
                    faks="fit"
                    true
                }
            R.id.fdu->
            {
                faks="fdu"
                true
            }
            R.id.fam->
            {
                faks="fam"
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CS330DZ061Theme {
        Greeting("Android")
    }
}