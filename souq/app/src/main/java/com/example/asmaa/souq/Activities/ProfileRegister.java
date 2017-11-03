package com.example.asmaa.souq.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.asmaa.souq.R;
import com.example.asmaa.souq.utilities.APIs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;

public class ProfileRegister extends AppCompatActivity {
    EditText name_driv_edittext, phone_driv_edittext, pass_driv_edittext, licenceNo_edittext;
    ImageView btn_register;
    TextView takephoto;
    Spinner spinner_district,spinner_age;
    ArrayAdapter<CharSequence>adapter_district;
    ArrayAdapter<String>adapter_age;
    String[] age_array = new String[50];
    String id;
    TextView back;
    ImageView help;




    String name, phone, age, govern, pass, LicenceNo ,image_string;
    CircleImageView Img_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_register);
        name_driv_edittext = (EditText) findViewById(R.id.name_driv_edittext);
        phone_driv_edittext = (EditText) findViewById(R.id.phone_driv_edittext);
        pass_driv_edittext = (EditText) findViewById(R.id.pass_driv_edittext);
        licenceNo_edittext = (EditText) findViewById(R.id.licenceNo_edittext);
        btn_register = (ImageView) findViewById(R.id.btn_register);
        takephoto = (TextView) findViewById(R.id.takephoto);
        Img_profile=(CircleImageView)findViewById(R.id.ic_profile);
        back=(TextView)findViewById(R.id.back_ProfileRigister);
        help=(ImageView)findViewById(R.id.help_ProfileRegister);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:01143050128"));
                if (ActivityCompat.checkSelfPermission(ProfileRegister.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (ActivityCompat.checkSelfPermission(ProfileRegister.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);

            }
        });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfileRegister.this,StartPage.class);
                startActivity(intent);
            }
        });


        num_age();
        spinner_district=(Spinner)findViewById(R.id.spinner_district);
        adapter_district=ArrayAdapter.createFromResource(this, R.array.list_district,android.R.layout.simple_spinner_item);
        adapter_district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_district.setAdapter(adapter_district);
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position) + "is selected", Toast.LENGTH_SHORT).show();
                govern=parent.getItemAtPosition(position).toString();
               // Toast.makeText(getBaseContext(),govern + "is selected", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_age=(Spinner)findViewById(R.id.spinner_age);
        adapter_age = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, age_array); //selected item will look like a spinner set from XML
        adapter_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_age.setAdapter(adapter_age);
        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age=parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(),age + "is selected", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ProfileRegister.this, AlbumSelectActivity.class);
                intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 1); // set limit for image selection
                startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);




            }
        });





        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = name_driv_edittext.getText().toString();
                pass = pass_driv_edittext.getText().toString();
                phone = phone_driv_edittext.getText().toString();
                //govern = Govern_driv_edittext.getText().toString();
                LicenceNo = licenceNo_edittext.getText().toString();
                //age = age_driv_edittext.getText().toString();
                if (!validate()) {
                    return;
                }
                registration();
            }
        });


    }

    void registration() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = APIs.RegisterProfile_URL;

        final ProgressDialog loading = ProgressDialog.show(this, null, "انتظر من فضلك ......", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("response", response);
                        id=response;

                        Toast.makeText(ProfileRegister.this, " تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ProfileRegister.this,CarRegister.class);
                        intent.putExtra("id",id);
                        startActivity(intent);

                        loading.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(ProfileRegister.this, "حاول مره اخرى", Toast.LENGTH_SHORT).show();
                Log.w("response", "حاول مره اخرى ");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> params = new HashMap<String, String>();

                params.put("Name", name_driv_edittext.getText().toString());
                params.put("phone_number", phone_driv_edittext.getText().toString());
                params.put("Age", age);
                params.put("Governorate", govern);
                params.put("licenceNo", licenceNo_edittext.getText().toString());
                params.put("password", pass_driv_edittext.getText().toString());
//                params.put("userImage",image_string);

                return params;

            }
        };

        queue.add(stringRequest);
    }


    public boolean validate() {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 3) {
            name_driv_edittext.setError(getString(R.string.invalid_name));
            valid = false;
        } else {
            name_driv_edittext.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            phone_driv_edittext.setError(getString(R.string.invalid_phone));
            valid = false;
        } else {
            phone_driv_edittext.setError(null);
        }

        if (pass.isEmpty() || pass.length() < 6) {

            pass_driv_edittext.setError(getString(R.string.invalid_password));
            valid = false;
        } else {
            pass_driv_edittext.setError(null);

        }
        if (LicenceNo.isEmpty() || LicenceNo.length() > 7 || LicenceNo.length() < 7) {

            licenceNo_edittext.setError(getString(R.string.invalid_licenceNo));
            valid = false;
        } else {
            licenceNo_edittext.setError(null);

        }


        return valid;
    }

    public void num_age(){
        for (int i = 0; i < age_array.length; i++) {
            age_array[i]=(i+20)+"";
        }

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);

            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));
                // start play with image uri
                Glide.with(this).load(uri)
                        .into(Img_profile);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(ProfileRegister.this.getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmap  =getResizedBitmap(bitmap,600,600);
                image_string  = encodeToBase64(bitmap,Bitmap.CompressFormat.JPEG,100);
                //Toast.makeText(ProfileRegister.this,image_string,Toast.LENGTH_SHORT).show();
                Log.w("image_string", image_string);





            }
        }
    }


    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(),Base64.NO_WRAP);
    }
    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }









}
