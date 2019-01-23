package vn.edu.poly.bookmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.database.DatabaseHelper;
import vn.edu.poly.bookmanager.model.User;


public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText edtusername,edtpassword;
    private Button btnLogin;
    private TextView tvDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper=new DatabaseHelper(getApplicationContext());
        User user2;
        user2=databaseHelper.getUser("PhamCong");
        if(user2==null){
            User user3=new User("PhamCong","PhamCong","0869135740","1234567");
            databaseHelper.insertUser(user3);
        }else {
        }

       initView();
       initActions();



}
public  void initView(){


    edtusername=findViewById(R.id.edtUsername);
    edtpassword=findViewById(R.id.edtPassword);
    btnLogin=findViewById(R.id.btnLogin);
    tvDangKy = (TextView) findViewById(R.id.tvDangKy);
}
public void initActions(){
        edtusername.setText("PhamCong");
        edtpassword.setText("1234567");
       btnLogin.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               String username = edtusername.getText().toString().trim();
               if(username.matches("")){
                   edtusername.setError(getString(R.string.notify_empty_user_name));
                   return;
               }
               String password=edtpassword.getText().toString().trim();
               if(password.matches("")){
                   edtpassword.setError(getString(R.string.notify_empty_pass));
                   return;
               }
               if(password.length()<6){
                   edtpassword.setError(getString(R.string.notify_min_pass));
                   return;
               }
               User user=databaseHelper.getUser(username);
               if(user!=null&&user.getUser_name()!=null){
                   if(password.matches(user.getPassword())){
                   Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                   startActivity(intent);
                   finish();
                       Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                   }
                   else {

                       Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();

                   }
               }else {
                   Toast.makeText(LoginActivity.this,"Bạn chưa có tài khoản", Toast.LENGTH_SHORT).show();
           }
           }
       });

       tvDangKy.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(),DangKyActivity.class);
               startActivity(intent);
               finish();

           }
       });
}
}

