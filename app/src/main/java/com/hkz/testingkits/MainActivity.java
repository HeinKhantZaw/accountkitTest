package com.hkz.testingkits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton;

public class MainActivity extends AppCompatActivity {
    HuaweiIdAuthButton signInBtn;
    AccountAuthService accountAuthService;
    AccountAuthParams accountAuthParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInBtn = findViewById(R.id.huaweiBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        accountAuthParams = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setIdToken().setAccessToken().createParams();
        accountAuthService = AccountAuthManager.getService(this, accountAuthParams);
        Intent intent = accountAuthService.getSignInIntent();
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if(authAccountTask.isSuccessful()){
                AuthAccount account = authAccountTask.getResult();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("username",account.getDisplayName());
                startActivity(intent);
                finish();
            }
        }
    }
    //    private void signIn() {
//        accountAuthParams = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
//                .setIdToken().setAccessToken().createParams();
//        accountAuthService = AccountAuthManager.getService(this, accountAuthParams);
//        Intent intent = accountAuthService.getSignInIntent();
//        startActivityForResult(intent,1);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    if(requestCode == 1){
//        Task<AuthAccount> authAccountTask =AccountAuthManager.parseAuthResultFromIntent(data);
//        if(authAccountTask.isSuccessful()){
//            AuthAccount account = authAccountTask.getResult();
//            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//            intent.putExtra("Name",account.getDisplayName());
//            startActivity(intent);
//            finish();
//        }
//    }
//    }
}