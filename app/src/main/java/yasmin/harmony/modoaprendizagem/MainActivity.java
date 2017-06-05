package yasmin.harmony.modoaprendizagem;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner musica, modos, maos, nivel;
    static SoundPool sp;
    int mID, nID, modoID, maoID;
    Button inicio, fim;
    static TextView statusMessage;
    ConnectionThread connect;
    static private int soundIDp1, soundIDp2, soundIDp3, soundIDp4, soundIDp5, soundIDp6, soundIDp7, soundIDp8, soundIDp9, soundIDp10,
            soundIDp11, soundIDp12, soundIDp13, soundIDp14, soundIDp15, soundIDp16, soundIDp17, soundIDp18, soundIDp19, soundIDp20, soundIDp21,
            soundIDp22, soundIDp23, soundIDp24, soundIDp25, soundIDp26, soundIDp27, soundIDp28, soundIDp29, soundIDp30, soundIDp31, soundIDp32,
            soundIDp33, soundIDp34, soundIDp35, soundIDp36, soundIDp37, soundIDp38, soundIDp39, soundIDp40, soundIDp41, soundIDp42, soundIDp43,
            soundIDp44, soundIDp45, soundIDp46, soundIDp47, soundIDp48, soundIDp49, soundIDp50, soundIDp51, soundIDp52, soundIDp53, soundIDp54,
            soundIDp55, soundIDp56, soundIDp57, soundIDp58, soundIDp59, soundIDp60;
    static boolean plays = false, loaded = false;
    static float actVolume, maxVolume, volume;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect = new ConnectionThread("98:D3:31:90:60:7A");
        connect.start();

        try {
            Thread.sleep(1000);
        } catch (Exception E) {
            E.printStackTrace();
        }

        musica = (Spinner) findViewById(R.id.musica);
        modos = (Spinner) findViewById(R.id.modo);
        maos = (Spinner) findViewById(R.id.maos);
        nivel = (Spinner) findViewById(R.id.nivel);

        inicio = (Button) findViewById(R.id.inicio);

        fim = (Button) findViewById(R.id.fim);

        statusMessage = (TextView) findViewById(R.id.statusMessage);

        musica.setOnItemSelectedListener(this);
        modos.setOnItemSelectedListener(this);
        maos.setOnItemSelectedListener(this);
        nivel.setOnItemSelectedListener(this);

        List<String> musicas = new ArrayList<String>();
        musicas.add("Für Elise");
        musicas.add("Super Mário");

        List<String> modo = new ArrayList<String>();
        modo.add("Normal");
        modo.add("Com Ritmo");

        List<String> niveis = new ArrayList<String>();
        niveis.add("Fácil");
        niveis.add("Médio");
        niveis.add("Difícil");

        List<String> mao = new ArrayList<String>();
        mao.add("Direita");
        mao.add("Esquerda");
        mao.add("Ambas");

        ArrayAdapter<String> musicaDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, musicas);

        ArrayAdapter<String> modoDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modo);

        ArrayAdapter<String> niveisDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, niveis);

        ArrayAdapter<String> maoDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mao);

        maoDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        modoDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        niveisDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        musicaDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        musica.setAdapter(musicaDataAdapter);
        modos.setAdapter(modoDataAdapter);
        nivel.setAdapter(niveisDataAdapter);
        maos.setAdapter(maoDataAdapter);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool sp, int sampleId, int status) {
                loaded = true;
            }
        });

        fim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect.write("fim".getBytes());
            }
        });


        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mID == 0 && nID == 0 && modoID == 0 && maoID == 0) {
                    statusMessage.setText("Für Elise Normal Fácil Direita");
                    connect.write("fnfd".getBytes());
                }else if (mID == 0 && nID == 0 && modoID == 0 && maoID == 1) {
                    statusMessage.setText("Für Elise Normal Fácil Esquerda");
                    connect.write("fnfe".getBytes());
                }else if (mID == 0 && nID == 0 && modoID == 0 && maoID == 2) {
                    statusMessage.setText("Für Elise Normal Fácil Duas Mãos");
                    connect.write("fnfa".getBytes());
                }else if (mID == 0 && nID == 0 && modoID == 1 && maoID == 0) {
                    statusMessage.setText("Für Elise Com Ritmo Fácil Direita");
                    connect.write("frfd".getBytes());
                }else if (mID == 0 && nID == 0 && modoID == 1 && maoID == 1) {
                    statusMessage.setText("Für Elise Com Ritmo Fácil Esquerda");
                    connect.write("frfe".getBytes());
                }else if (mID == 0 && nID == 0 && modoID == 1 && maoID == 2) {
                    statusMessage.setText("Für Elise Com Ritmo Fácil Ambas");
                    connect.write("frfa".getBytes());
                }else if (mID == 0 && nID == 1 && modoID == 0 && maoID == 0) {
                    statusMessage.setText("Für Elise Normal Médio Direita");
                    connect.write("fnmd".getBytes());
                }else if (mID == 0 && nID == 1 && modoID == 0 && maoID == 1) {
                    statusMessage.setText("Für Elise Normal Médio Esquerda");
                    connect.write("fnme".getBytes());
                }else if (mID == 0 && nID == 1 && modoID == 0 && maoID == 2) {
                    statusMessage.setText("Für Elise Normal Médio Ambas");
                    connect.write("fnma".getBytes());
                }else if (mID == 0 && nID == 1 && modoID == 1 && maoID == 0) {
                    statusMessage.setText("Für Elise Com Ritmo Médio Direita");
                    connect.write("frmd".getBytes());
                }else if (mID == 0 && nID == 1 && modoID == 1 && maoID == 1) {
                    statusMessage.setText("Für Elise Com Ritmo Médio Esquerda");
                    connect.write("frme".getBytes());
                }else if (mID == 0 && nID == 1 && modoID == 1 && maoID == 2) {
                    statusMessage.setText("Für Elise Com Ritmo Médio Ambas");
                    connect.write("frma".getBytes());
                }else if (mID == 0 && nID == 2 && modoID == 0 && maoID == 0) {
                    statusMessage.setText("Für Elise Normal Difícil Direita");
                    connect.write("fndd".getBytes());
                }else if (mID == 0 && nID == 2 && modoID == 0 && maoID == 1) {
                    statusMessage.setText("Für Elise Normal Difícil Esquerda");
                    connect.write("fnde".getBytes());
                }else if (mID == 0 && nID == 2 && modoID == 0 && maoID == 2) {
                    statusMessage.setText("Für Elise Normal Difícil Ambas");
                    connect.write("fnda".getBytes());
                }else if (mID == 0 && nID == 2 && modoID == 1 && maoID == 0) {
                    statusMessage.setText("Für Elise Com Ritmo Difícil Direita");
                    connect.write("frdd".getBytes());
                }else if (mID == 0 && nID == 2 && modoID == 1 && maoID == 1) {
                    statusMessage.setText("Für Elise Com Ritmo Difícil Esquerda");
                    connect.write("frde".getBytes());
                }else if (mID == 0 && nID == 2 && modoID == 1 && maoID == 2) {
                    statusMessage.setText("Für Elise Com Ritmo Difícil Ambas");
                    connect.write("frda".getBytes());

                }else if (mID == 1 && nID == 0 && modoID == 0 && maoID == 0) {
                    statusMessage.setText("Super Mário Normal Fácil Direita");
                    connect.write("mnfd".getBytes());
                }else if (mID == 1 && nID == 0 && modoID == 0 && maoID == 1) {
                    statusMessage.setText("Super Mário Normal Fácil Esquerda");
                    connect.write("mnfe".getBytes());
                }else if (mID == 1 && nID == 0 && modoID == 0 && maoID == 2) {
                    statusMessage.setText("Super Mário Normal Fácil Duas Mãos");
                    connect.write("mnfa".getBytes());
                }else if (mID == 1 && nID == 0 && modoID == 1 && maoID == 0) {
                    statusMessage.setText("Super Mário Com Ritmo Fácil Direita");
                    connect.write("mrfd".getBytes());
                }else if (mID == 1 && nID == 0 && modoID == 1 && maoID == 1) {
                    statusMessage.setText("Super Mário Com Ritmo Fácil Esquerda");
                    connect.write("mrfe".getBytes());
                }else if (mID == 1 && nID == 0 && modoID == 1 && maoID == 2) {
                    statusMessage.setText("Super Mário Com Ritmo Fácil Ambas");
                    connect.write("mrfa".getBytes());
                }else if (mID == 1 && nID == 1 && modoID == 0 && maoID == 0) {
                    statusMessage.setText("Super Mário Normal Médio Direita");
                    connect.write("mnmd".getBytes());
                }else if (mID == 1 && nID == 1 && modoID == 0 && maoID == 1) {
                    statusMessage.setText("Super Mário Normal Médio Esquerda");
                    connect.write("mnme".getBytes());
                }else if (mID == 1 && nID == 1 && modoID == 0 && maoID == 2) {
                    statusMessage.setText("Super Mário Normal Médio Ambas");
                    connect.write("mnma".getBytes());
                }else if (mID == 1 && nID == 1 && modoID == 1 && maoID == 0) {
                    statusMessage.setText("Super Mário Com Ritmo Médio Direita");
                    connect.write("mrmd".getBytes());
                }else if (mID == 1 && nID == 1 && modoID == 1 && maoID == 1) {
                    statusMessage.setText("Super Mário Com Ritmo Médio Esquerda");
                    connect.write("mrme".getBytes());
                }else if (mID == 1 && nID == 1 && modoID == 1 && maoID == 2) {
                    statusMessage.setText("Super Mário Com Ritmo Médio Ambas");
                    connect.write("mrma".getBytes());
                }else if (mID == 1 && nID == 2 && modoID == 0 && maoID == 0) {
                    statusMessage.setText("Super Mário Normal Difícil Direita");
                    connect.write("mndd".getBytes());
                }else if (mID == 1 && nID == 2 && modoID == 0 && maoID == 1) {
                    statusMessage.setText("Super Mário Normal Difícil Esquerda");
                    connect.write("mnde".getBytes());
                }else if (mID == 1 && nID == 2 && modoID == 0 && maoID == 2) {
                    statusMessage.setText("Super Mário Normal Difícil Ambas");
                    connect.write("mnda".getBytes());
                }else if (mID == 1 && nID == 2 && modoID == 1 && maoID == 0) {
                    statusMessage.setText("Super Mário Com Ritmo Difícil Direita");
                    connect.write("mrdd".getBytes());
                }else if (mID == 1 && nID == 2 && modoID == 1 && maoID == 1) {
                    statusMessage.setText("Super Mário Com Ritmo Difícil Esquerda");
                    connect.write("mrde".getBytes());
                }else if (mID == 1 && nID == 2 && modoID == 1 && maoID == 2) {
                    statusMessage.setText("Super Mário Com Ritmo Difícil Ambas");
                    connect.write("mrda".getBytes());

                }/*else if (mID == 2 && nID == 0 && modoID == 0 && maoID == 0)
                    statusMessage.setText("Fantasma da Ópera Normal Fácil Direita");
                else if (mID == 2 && nID == 0 && modoID == 0 && maoID == 1)
                    statusMessage.setText("Fantasma da Ópera Normal Fácil Esquerda");
                else if (mID == 2 && nID == 0 && modoID == 0 && maoID == 2)
                    statusMessage.setText("Fantasma da Ópera Normal Fácil Duas Mãos");
                else if (mID == 2 && nID == 0 && modoID == 1 && maoID == 0)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Fácil Direita");
                else if (mID == 2 && nID == 0 && modoID == 1 && maoID == 1)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Fácil Esquerda");
                else if (mID == 2 && nID == 0 && modoID == 1 && maoID == 2)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Fácil Ambas");
                else if (mID == 2 && nID == 1 && modoID == 0 && maoID == 0)
                    statusMessage.setText("Fantasma da Ópera Normal Médio Direita");
                else if (mID == 2 && nID == 1 && modoID == 0 && maoID == 1)
                    statusMessage.setText("Fantasma da Ópera Normal Médio Esquerda");
                else if (mID == 2 && nID == 1 && modoID == 0 && maoID == 2)
                    statusMessage.setText("Fantasma da Ópera Normal Médio Ambas");
                else if (mID == 2 && nID == 1 && modoID == 1 && maoID == 0)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Médio Direita");
                else if (mID == 2 && nID == 1 && modoID == 1 && maoID == 1)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Médio Esquerda");
                else if (mID == 2 && nID == 1 && modoID == 1 && maoID == 2)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Médio Ambas");
                else if (mID == 2 && nID == 2 && modoID == 0 && maoID == 0)
                    statusMessage.setText("Fantasma da Ópera Normal Difícil Direita");
                else if (mID == 2 && nID == 2 && modoID == 0 && maoID == 1)
                    statusMessage.setText("Fantasma da Ópera Normal Difícil Esquerda");
                else if (mID == 2 && nID == 2 && modoID == 0 && maoID == 2)
                    statusMessage.setText("Fantasma da Ópera Normal Difícil Ambas");
                else if (mID == 2 && nID == 2 && modoID == 1 && maoID == 0)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Difícil Direita");
                else if (mID == 2 && nID == 2 && modoID == 1 && maoID == 1)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Difícil Esquerda");
                else if (mID == 2 && nID == 2 && modoID == 1 && maoID == 2)
                    statusMessage.setText("Fantasma da Ópera Com Ritmo Difícil Ambas");*/

            }


        });

        soundIDp1 = sp.load(this, R.raw.piano1, 1);
        soundIDp2 = sp.load(this, R.raw.piano2, 1);
        soundIDp3 = sp.load(this, R.raw.piano3, 1);
        soundIDp4 = sp.load(this, R.raw.piano4, 1);
        soundIDp5 = sp.load(this, R.raw.piano5, 1);
        soundIDp6 = sp.load(this, R.raw.piano6, 1);
        soundIDp7 = sp.load(this, R.raw.piano7, 1);
        soundIDp8 = sp.load(this, R.raw.piano8, 1);
        soundIDp9 = sp.load(this, R.raw.piano9, 1);
        soundIDp10 = sp.load(this, R.raw.piano10, 1);
        soundIDp11 = sp.load(this, R.raw.piano11, 1);
        soundIDp12 = sp.load(this, R.raw.piano12, 1);
        soundIDp13 = sp.load(this, R.raw.piano13, 1);
        soundIDp14 = sp.load(this, R.raw.piano14, 1);
        soundIDp15 = sp.load(this, R.raw.piano15, 1);
        soundIDp16 = sp.load(this, R.raw.piano16, 1);
        soundIDp17 = sp.load(this, R.raw.piano17, 1);
        soundIDp18 = sp.load(this, R.raw.piano18, 1);
        soundIDp19 = sp.load(this, R.raw.piano19, 1);
        soundIDp20 = sp.load(this, R.raw.piano20, 1);
        soundIDp21 = sp.load(this, R.raw.piano21, 1);
        soundIDp22 = sp.load(this, R.raw.piano22, 1);
        soundIDp23 = sp.load(this, R.raw.piano23, 1);
        soundIDp24 = sp.load(this, R.raw.piano24, 1);
        soundIDp25 = sp.load(this, R.raw.piano25, 1);
        soundIDp26 = sp.load(this, R.raw.piano26, 1);
        soundIDp27 = sp.load(this, R.raw.piano27, 1);
        soundIDp28 = sp.load(this, R.raw.piano28, 1);
        soundIDp29 = sp.load(this, R.raw.piano29, 1);
        soundIDp30 = sp.load(this, R.raw.piano30, 1);
        soundIDp31 = sp.load(this, R.raw.piano31, 1);
        soundIDp32 = sp.load(this, R.raw.piano32, 1);
        soundIDp33 = sp.load(this, R.raw.piano33, 1);
        soundIDp34 = sp.load(this, R.raw.piano34, 1);
        soundIDp35 = sp.load(this, R.raw.piano35, 1);
        soundIDp36 = sp.load(this, R.raw.piano36, 1);
        soundIDp37 = sp.load(this, R.raw.piano37, 1);
        soundIDp38 = sp.load(this, R.raw.piano38, 1);
        soundIDp39 = sp.load(this, R.raw.piano39, 1);
        soundIDp40 = sp.load(this, R.raw.piano40, 1);
        soundIDp41 = sp.load(this, R.raw.piano41, 1);
        soundIDp42 = sp.load(this, R.raw.piano42, 1);
        soundIDp43 = sp.load(this, R.raw.piano43, 1);
        soundIDp44 = sp.load(this, R.raw.piano44, 1);
        soundIDp45 = sp.load(this, R.raw.piano45, 1);
        soundIDp46 = sp.load(this, R.raw.piano46, 1);
        soundIDp47 = sp.load(this, R.raw.piano47, 1);
        soundIDp48 = sp.load(this, R.raw.piano48, 1);
        soundIDp49 = sp.load(this, R.raw.piano49, 1);
        soundIDp50 = sp.load(this, R.raw.piano50, 1);
        soundIDp51 = sp.load(this, R.raw.piano51, 1);
        soundIDp52 = sp.load(this, R.raw.piano52, 1);
        soundIDp53 = sp.load(this, R.raw.piano53, 1);
        soundIDp54 = sp.load(this, R.raw.piano54, 1);
        soundIDp55 = sp.load(this, R.raw.piano55, 1);
        soundIDp56 = sp.load(this, R.raw.piano56, 1);
        soundIDp57 = sp.load(this, R.raw.piano57, 1);
        soundIDp58 = sp.load(this, R.raw.piano58, 1);
        soundIDp59 = sp.load(this, R.raw.piano59, 1);
        soundIDp60 = sp.load(this, R.raw.piano60, 1);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch (parent.getId()){
            case R.id.musica:
                String musica = parent.getItemAtPosition(position).toString();
                if (musica.equals("Für Elise"))
                    mID = 0;
                else if (musica.equals("Super Mário"))
                    mID = 1;
                break;
            case R.id.modo:
                String modo = parent.getItemAtPosition(position).toString();
                if (modo.equals("Normal"))
                    modoID = 0;
                else if (modo.equals("Com Ritmo"))
                    modoID = 1;
                break;
            case R.id.nivel:
                String nivel = parent.getItemAtPosition(position).toString();
                if (nivel.equals("Fácil"))
                    nID = 0;
                else if (nivel.equals("Médio"))
                    nID = 1;
                else if (nivel.equals("Difícil"))
                    nID = 2;
                break;
            case R.id.maos:
                String maos = parent.getItemAtPosition(position).toString();
                if (maos.equals("Direita"))
                    maoID = 0;
                else if (maos.equals("Esquerda"))
                    maoID = 1;
                else if (maos.equals("Ambas"))
                    maoID = 2;
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static Handler handler = new Handler() {

        @Override

        public void handleMessage(Message msg) {


            Bundle bundle = msg.getData();

            byte[] data = bundle.getByteArray("data");

            String dataString = new String(data);


            if (dataString.equals("---N"))

                statusMessage.setText("Ocorreu um erro durante a conexão D:");

            if (dataString.equals("---S"))

                statusMessage.setText("Conectado :D");



            /* Esse método é invocado na Activity principal

                sempre que a thread de conexão Bluetooth recebe

                uma mensagem.

             */


            if (dataString.equals("t1")) {
                if (loaded && !plays) {
                    sp.play(soundIDp1, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t2")) {
                if (loaded && !plays) {
                    sp.play(soundIDp2, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t3")) {
                if (loaded && !plays) {
                    sp.play(soundIDp3, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t4")) {
                if (loaded && !plays) {
                    sp.play(soundIDp4, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t5")) {
                if (loaded && !plays) {
                    sp.play(soundIDp5, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t6")) {
                if (loaded && !plays) {
                    sp.play(soundIDp6, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t7")) {
                if (loaded && !plays) {
                    sp.play(soundIDp7, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t8")) {
                if (loaded && !plays) {
                    sp.play(soundIDp8, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t9")) {
                if (loaded && !plays) {
                    sp.play(soundIDp9, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t10")) {
                if (loaded && !plays) {
                    sp.play(soundIDp10, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t11")) {
                if (loaded && !plays) {
                    sp.play(soundIDp11, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t12")) {
                if (loaded && !plays) {
                    sp.play(soundIDp12, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t13")) {
                if (loaded && !plays) {
                    sp.play(soundIDp13, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t14")) {
                if (loaded && !plays) {
                    sp.play(soundIDp14, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t15")) {
                if (loaded && !plays) {
                    sp.play(soundIDp15, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t16")) {
                if (loaded && !plays) {
                    sp.play(soundIDp16, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t17")) {
                if (loaded && !plays) {
                    sp.play(soundIDp17, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t18")) {
                if (loaded && !plays) {
                    sp.play(soundIDp18, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t19")) {
                if (loaded && !plays) {
                    sp.play(soundIDp19, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t20")) {
                if (loaded && !plays) {
                    sp.play(soundIDp20, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t21")) {
                if (loaded && !plays) {
                    sp.play(soundIDp21, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t22")) {
                if (loaded && !plays) {
                    sp.play(soundIDp22, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t23")) {
                if (loaded && !plays) {
                    sp.play(soundIDp23, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t24")) {
                if (loaded && !plays) {
                    sp.play(soundIDp24, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t25")) {
                if (loaded && !plays) {
                    sp.play(soundIDp25, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t26")) {
                if (loaded && !plays) {
                    sp.play(soundIDp26, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t27")) {
                if (loaded && !plays) {
                    sp.play(soundIDp27, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t28")) {
                if (loaded && !plays) {
                    sp.play(soundIDp28, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t29")) {
                if (loaded && !plays) {
                    sp.play(soundIDp29, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t30")) {
                if (loaded && !plays) {
                    sp.play(soundIDp30, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
                //tocou = true;
            } if (dataString.equals("t31")) {
                if (loaded && !plays) {
                    sp.play(soundIDp31, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t32")) {
                if (loaded && !plays) {
                    sp.play(soundIDp32, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t33")) {
                if (loaded && !plays) {
                    sp.play(soundIDp33, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t34")) {
                if (loaded && !plays) {
                    sp.play(soundIDp34, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t35")) {
                if (loaded && !plays) {
                    sp.play(soundIDp35, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t36")) {
                if (loaded && !plays) {
                    sp.play(soundIDp36, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t37")) {
                if (loaded && !plays) {
                    sp.play(soundIDp37, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t38")) {
                if (loaded && !plays) {
                    sp.play(soundIDp38, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t39")) {
                if (loaded && !plays) {
                    sp.play(soundIDp39, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t40")) {
                if (loaded && !plays) {
                    sp.play(soundIDp40, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t41")) {
                if (loaded && !plays) {
                    sp.play(soundIDp41, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t42")) {
                if (loaded && !plays) {
                    sp.play(soundIDp42, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t43")) {
                if (loaded && !plays) {
                    sp.play(soundIDp43, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t44")) {
                if (loaded && !plays) {
                    sp.play(soundIDp44, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t45")) {
                if (loaded && !plays) {
                    sp.play(soundIDp45, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t46")) {
                if (loaded && !plays) {
                    sp.play(soundIDp46, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t47")) {
                if (loaded && !plays) {
                    sp.play(soundIDp47, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t48")) {
                if (loaded && !plays) {
                    sp.play(soundIDp48, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t49")) {
                if (loaded && !plays) {
                    sp.play(soundIDp49, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t50")) {
                if (loaded && !plays) {
                    sp.play(soundIDp50, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t50")) {
                if (loaded && !plays) {
                    sp.play(soundIDp50, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t51")) {
                if (loaded && !plays) {
                    sp.play(soundIDp51, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t52")) {
                if (loaded && !plays) {
                    sp.play(soundIDp52, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t53")) {
                if (loaded && !plays) {
                    sp.play(soundIDp53, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t54")) {
                if (loaded && !plays) {
                    sp.play(soundIDp54, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t55")) {
                if (loaded && !plays) {
                    sp.play(soundIDp55, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t56")) {
                if (loaded && !plays) {
                    sp.play(soundIDp56, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t57")) {
                if (loaded && !plays) {
                    sp.play(soundIDp57, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t58")) {
                if (loaded && !plays) {
                    sp.play(soundIDp58, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t59")) {
                if (loaded && !plays) {
                    sp.play(soundIDp59, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            } if (dataString.equals("t60")) {
                if (loaded && !plays) {
                    sp.play(soundIDp60, volume, volume, 1, 0, 1f);
                    plays = true;
                }
                plays = false;
            }

        }

    };
}
