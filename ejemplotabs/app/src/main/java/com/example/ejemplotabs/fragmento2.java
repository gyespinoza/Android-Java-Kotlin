package com.example.ejemplotabs;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmento2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmento2 extends Fragment {
    //declarar los controles, propiedades
    private EditText etNombre, etApellido, etCorreo;
    private Button btEnviar, btCancelar;
    private Boolean validar=false;
    private String mensaje="Se ha registrado exitosamente!";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmento2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmento2.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmento2 newInstance(String param1, String param2) {
        fragmento2 fragment = new fragmento2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v =inflater.inflate(R.layout.fragment_fragmento2, container, false);

         //vincular propiedades con los controles de formulario
        etNombre=(EditText) v.findViewById(R.id.nombre);
        etApellido=(EditText) v.findViewById(R.id.apellido);
        etCorreo=(EditText) v.findViewById(R.id.correo);
        btCancelar=(Button) v.findViewById(R.id.btCancelar);
        btEnviar=(Button) v.findViewById(R.id.btEnviar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar=validaEditText();
                if(validar){
                   // Toast.makeText(getContext(), mensaje,Toast.LENGTH_LONG).show();

                    //crear alertDialog
                    AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                    builder.setTitle("Registro");
                    builder.setMessage("Se ha registrado con exito!");
                    builder.setPositiveButton("Aceptar", null);

                    //crear la alerta y mostrarla
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    limpiar();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        return v;
    }

    //funcion para evaluar que todos los campos esten llenos
    private boolean validaEditText(){
        if(etNombre.length()==0){
            etNombre.setError("Debe escribir el nombre");
            return false;
        }
        if(etApellido.length()==0){
            etApellido.setError("Debe escribir el apellido");
            return  false;
        }
        if(etCorreo.length()==0){
            etCorreo.setError("Debe escribir el correo");
            return  false;
        }
        return true;
    }

    //metodo para limpiar los EditText
    private void limpiar(){
        etNombre.setText("");
        etApellido.setText("");
        etCorreo.setText("");
    }
}