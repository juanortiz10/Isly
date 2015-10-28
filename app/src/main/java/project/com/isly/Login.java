package project.com.isly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by juan on 14/10/15.
 */
public class Login extends Activity{
    private EditText etUser,etPass;
    private Button btnEnter;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnEnter=(Button)findViewById(R.id.btnEnter);
        etUser=(EditText)findViewById(R.id.etUser);
        etPass=(EditText)findViewById(R.id.etPass);
        tvError=(TextView)findViewById(R.id.tvError);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(etUser.getText().toString().trim().equals("") || etPass.getText().toString().trim().equals(""))){
                    if(etUser.getText().toString().equals("root") && etPass.getText().toString().trim().equals("root")){
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra(etUser.toString(),"username");
                        startActivity(intent);
                    }else{
                        tvError.setText(R.string.error_incorrect);
                    }
                }else{
                        tvError.setText(R.string.empty_field);
                }
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //This method disables the back button if nothing is typed inside it
    @Override
    public void onBackPressed() {
    }

}
