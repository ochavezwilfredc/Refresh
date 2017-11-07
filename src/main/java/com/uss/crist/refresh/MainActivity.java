package com.uss.crist.refresh;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
   Declarar instancias globales
   */
    private RecyclerView recycler;
    private ListaAdapter adapter;
    private RecyclerView.LayoutManager lManager;

    private SwipeRefreshLayout refreshLayout;

    private static final int CANTIDAD_ITEMS = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ListaAdapter(ConjuntoListas.randomList(CANTIDAD_ITEMS));
        recycler.setAdapter(adapter);

        // Obtener el refreshLayout
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        // Seteamos los colores que se usarán a lo largo de la animación
        refreshLayout.setColorSchemeResources(
                R.color.s1,
                R.color.s2,
                R.color.s3,
                R.color.s4
        );

        // Iniciar la tarea asíncrona al revelar el indicador
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new HackingBackgroundTask().execute();
                    }
                }
        );
    }


    private class HackingBackgroundTask extends AsyncTask<Void, Void, List<Lista>> {

        static final int DURACION = 3 * 1000; // 3 segundos de carga

        @Override
        protected List<Lista> doInBackground(Void... params) {
            // Simulación de la carga de items
            try {
                Thread.sleep(DURACION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Retornar en nuevos elementos para el adaptador
            return ConjuntoListas.randomList(CANTIDAD_ITEMS);
        }

        @Override
        protected void onPostExecute(List<Lista> result) {
            super.onPostExecute(result);

            // Limpiar elementos antiguos
            adapter.clear();

            // Añadir elementos nuevos
            adapter.addAll(result);

            // Parar la animación del indicador
            refreshLayout.setRefreshing(false);
        }

    }
}
