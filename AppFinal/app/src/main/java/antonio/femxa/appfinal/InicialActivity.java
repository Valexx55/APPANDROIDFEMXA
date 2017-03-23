package antonio.femxa.appfinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class InicialActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private static boolean musicaOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        mediaPlayer = MediaPlayer.create(this, R.raw.inicio1);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);

        ponerTexto();

        musicaOnOff = getIntent().getBooleanExtra("SonidoOn-Off",true);

        View v = findViewById(R.id.botonsonido);
        final Button ib = (Button) v;

        if(musicaOnOff)
        {
            mediaPlayer.start();
        }


        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                    ib.setText("SONIDO ON");
                    musicaOnOff = false;
                }
                else
                {
                    ib.setText("SONIDO OFF");
                    mediaPlayer.start();
                    musicaOnOff = true;
                }
            }
        });

    }

    public void aJugar (View v)
    {

        Intent intent = new Intent(this, CategoriaActivity.class);

        if(musicaOnOff)
        {
            intent.putExtra("SonidoOn-Off",true);
        }
        else
        {
            intent.putExtra("SonidoOn-Off",false);
        }

        startActivity(intent);

    }

    public void ponerTexto(){
        View v1 = findViewById(R.id.botonsonido);
        Button ib = (Button) v1;

        View v2 = findViewById(R.id.botoncreditos);
        Button ib2 = (Button) v2;

        ib.setText("SONIDO OFF");
        ib2.setText("   CREDITOS");

    }

    public void abrirCreditos (View v)
    {

        Intent intent = new Intent(this, CreditosActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
//super.onBackPressed();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            finishAffinity();
        }
        else
        {
            finish();
        }
    }


}
