package com.example.ejemplotabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //los canales son una caracteristicas de android
    //utilizados para configurar los niveles de prioridad de notificacion
    //definir el canal para la notificacion
    public static final String NOTIFICATION_CHANNEL_ID="1001";
    public static final String default_notification_channel_id="default";


    //definir los iconos para los tabs
    private int[]tabIcons={
        R.drawable.ic_baseline_article_24,
        R.drawable.ic_baseline_assignment_24,
        R.drawable.ic_baseline_assignment_ind_24
    };


    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    //para los fragmentos
    private fragmento1 fragmento1;
    private fragmento2 fragmento2;
    private fragmento3 fragmento3;


    //para mostrar el menu en el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    //mostrar mensajes al momento de seleccionar una opcion de menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.opcion1:
                Toast.makeText(this,"Selecciono la opcion 1", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.opcion2:
                Toast.makeText(this,"Selecciono la opcion 2", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.opcion3:
                Toast.makeText(this,"Selecciono la opcion 3", Toast.LENGTH_LONG).show();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////////////////////////////////////
        //NOTIFICACION//

        //NotificationManager, es una clase que notifica al usuario los eventos que suceden
        //de esta manera se le indica al usuario lo que sucede en segundo plano
        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //permite construir los diseños de la notificacion
        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this, default_notification_channel_id);
        builder.setContentTitle("Notificacion");
        builder.setContentText("Se ha iniciado la aplicacion");
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);

        if(Build.VERSION. SDK_INT >= Build.VERSION_CODES. O){
            //definir importancia de la notificacion
            int importancia = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importancia);
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            assert notificationManager!=null;
            notificationManager.createNotificationChannel(notificationChannel);
        }

        assert notificationManager!=null;
        notificationManager.notify((int)System.currentTimeMillis(), builder.build());

        //////////////////////////////////////////////////


        //mostrar toolbar en el activity
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //asociar las instancias de los controles a  los componentes del activity
        viewPager= findViewById(R.id.view_pager);
        tabLayout= findViewById(R.id.tabs);
        fragmento1= new fragmento1();
        fragmento2= new fragmento2();
        fragmento3= new fragmento3();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(fragmento1, "Tab 1");
        viewPagerAdapter.addFragment(fragmento2, "Tab 2");
        viewPagerAdapter.addFragment(fragmento3, "Tab 3");
        viewPager.setAdapter(viewPagerAdapter);

        //mostrar iconos en los tabs
        for(int i=0; i< tabLayout.getTabCount();i++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }


        //opciones de menu

    }


    public class ViewPagerAdapter  extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior){
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position){
            return fragmentTitle.get(position);
        }

    }
}