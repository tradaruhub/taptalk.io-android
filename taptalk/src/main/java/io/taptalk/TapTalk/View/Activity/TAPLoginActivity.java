package io.taptalk.TapTalk.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;

import io.taptalk.TapTalk.API.Api.TAPApiManager;
import io.taptalk.TapTalk.API.View.TapDefaultDataView;
import io.taptalk.TapTalk.Helper.TAPUtils;
import io.taptalk.TapTalk.Helper.TapTalk;
import io.taptalk.TapTalk.Helper.TapTalkDialog;
import io.taptalk.TapTalk.Manager.TAPConnectionManager;
import io.taptalk.TapTalk.Manager.TAPDataManager;
import io.taptalk.TapTalk.Model.ResponseModel.TAPAuthTicketResponse;
import io.taptalk.TapTalk.Model.ResponseModel.TAPCommonResponse;
import io.taptalk.TapTalk.Model.ResponseModel.TAPGetAccessTokenResponse;
import io.taptalk.TapTalk.Model.TAPErrorModel;
import io.taptalk.Taptalk.R;

import static io.taptalk.TapTalk.Const.TAPDefaultConstant.K_MY_USERNAME;

public class TAPLoginActivity extends TAPBaseActivity {

    private static final String TAG = TAPLoginActivity.class.getSimpleName();
    private TextInputEditText etUsername;
    private TextView tvSignIn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_activity_login);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        etUsername = findViewById(R.id.et_username);
        tvSignIn = findViewById(R.id.tv_sign_in);
        progressBar = findViewById(R.id.pb_signing_in);

        etUsername.setOnEditorActionListener((v, actionId, event) -> {
            attemptLogin();
            return false;
        });

        tvSignIn.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        if (etUsername.getText().toString().equals("")) {
            etUsername.setError("Please fill your username.");
        } else if (!checkValidUsername(etUsername.getText().toString().toLowerCase())) {
            etUsername.setError("Please enter valid username.");
        } else {
            TAPUtils.getInstance().dismissKeyboard(this);
            progressBar.setVisibility(View.VISIBLE);
            tvSignIn.setVisibility(View.GONE);

            new Thread(() -> {
                try {
                    setDataAndCallAPI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void setDataAndCallAPI() throws Exception {
        String ipAddress = TAPUtils.getInstance().getStringFromURL(new URL("https://api.ipify.org/"));
        String userAgent = "android";
        String userPlatform = "android";
        String xcUserID = getDummyUserID(etUsername.getText().toString()) + "";
        String fullname = getDummyUserFullName(xcUserID);
        String email = etUsername.getText().toString() + "@moselo.com";
        String phone = "08979809026";
        String username = etUsername.getText().toString();
        String deviceID = Settings.Secure.getString(TapTalk.appContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        TAPDataManager.getInstance().getAuthTicket(ipAddress, userAgent, userPlatform, deviceID, xcUserID,
                fullname, email, phone, username, authView);
    }

    // TODO: 14/09/18 nanti ini harus dihilangin (Wajib)
    private boolean checkValidUsername(String username) {
        switch (username) {
            case "ritchie":
            case "dominic":
            case "rionaldo":
            case "kevin":
            case "welly":
            case "jony":
            case "michael":
            case "richard":
            case "erwin":
            case "jefry":
            case "cundy":
            case "rizka":
            case "test1":
            case "test2":
            case "test3":
            case "santo":
            case "veronica":
            case "poppy":
            case "axel":
            case "ovita":
            case "amalia":
            case "ronal":
            case "ardanti":
            case "anita":
            case "kevinfianto":
            case "dessy":
            case "neni":
            case "bernama":
            case "william":
            case "sarah":
            case "retyan":
            case "sekar":
            case "putri":
            case "mei":
            case "yuendry":
            case "ervin":
            case "fauzi":
                return true;

            default:
                return false;
        }
    }

    // TODO: 14/09/18 nanti ini harus dihilangin (Wajib)
    private int getDummyUserID(String username) {
        switch (username) {
            case "ritchie":
                return 1;
            case "dominic":
                return 2;
            case "rionaldo":
                return 3;
            case "kevin":
                return 4;
            case "welly":
                return 5;
            case "jony":
                return 6;
            case "michael":
                return 7;
            case "richard":
                return 8;
            case "erwin":
                return 9;
            case "jefry":
                return 10;
            case "cundy":
                return 11;
            case "rizka":
                return 12;
            case "test1":
                return 13;
            case "test2":
                return 14;
            case "test3":
                return 15;
            case "santo":
                return 16;
            case "veronica":
                return 17;
            case "poppy":
                return 18;
            case "axel":
                return 19;
            case "ovita":
                return 20;
            case "putri":
                return 21;
            case "amalia":
                return 22;
            case "ronal":
                return 23;
            case "ardanti":
                return 24;
            case "anita":
                return 25;
            case "kevinfianto":
                return 26;
            case "dessy":
                return 27;
            case "neni":
                return 28;
            case "bernama":
                return 29;
            case "william":
                return 30;
            case "sarah":
                return 31;
            case "retyan":
                return 32;
            case "sekar":
                return 33;
            case "mei":
                return 34;
            case "yuendry":
                return 35;
            case "ervin":
                return 36;
            case "fauzi":
                return 37;
            default:
                return 0;
        }
    }

    // TODO: 04/10/18 DUMMY
    private String getDummyUserFullName(String userID) {
        switch (userID) {
            case "1":
                return "Ritchie Nathaniel";
            case "2":
                return "Dominic Vedericho";
            case "3":
                return "Rionaldo Linggautama";
            case "4":
                return "Kevin Reynaldo";
            case "5":
                return "Welly Kencana";
            case "6":
                return "Jony Lim";
            case "7":
                return "Michael Tansy";
            case "8":
                return "Richard Fang";
            case "9":
                return "Erwin Andreas";
            case "10":
                return "Jefry Lorentono";
            case "11":
                return "Cundy Sunardy";
            case "12":
                return "Rizka Fatmawati";
            case "13":
                return "Test 1";
            case "14":
                return "Test 2";
            case "15":
                return "Test 3";
            case "16":
                return "Santo";
            case "17":
                return "veronica";
            case "18":
                return "poppy";
            case "19":
                return "axel";
            case "20":
                return "ovita";
            case "21":
                return "putri";
            case "22":
                return "amalia";
            case "23":
                return "ronal";
            case "24":
                return "ardanti";
            case "25":
                return "anita";
            case "26":
                return "kevinfianto";
            case "27":
                return "dessy";
            case "28":
                return "neni";
            case "29":
                return "bernama";
            case "30":
                return "william";
            case "31":
                return "sarah";
            case "32":
                return "retyan";
            case "33":
                return "sekar";
            case "34":
                return "mei";
            case "35":
                return "yuendry";
            case "36":
                return "ervin";
            case "37":
                return "fauzi";
            default:
                return "User Ga Tau Dari Mana ini";
        }
    }

    TapDefaultDataView<TAPAuthTicketResponse> authView = new TapDefaultDataView<TAPAuthTicketResponse>() {
        @Override
        public void startLoading() {
            super.startLoading();
        }

        @Override
        public void endLoading() {
            super.endLoading();
        }

        @Override
        public void onSuccess(TAPAuthTicketResponse response) {
            super.onSuccess(response);
            TAPApiManager.getInstance().setLogout(false);
            TapTalk.saveAuthTicketAndGetAccessToken(response.getTicket()
                            , accessTokenView);
        }

        @Override
        public void onError(TAPErrorModel error) {
            super.onError(error);
            showDialog("ERROR "+error.getCode(), error.getMessage());
        }
    };

    TapDefaultDataView<TAPGetAccessTokenResponse> accessTokenView = new TapDefaultDataView<TAPGetAccessTokenResponse>() {
        @Override
        public void startLoading() {
            super.startLoading();
        }

        @Override
        public void endLoading() {
            super.endLoading();
        }

        @Override
        public void onSuccess(TAPGetAccessTokenResponse response) {
            super.onSuccess(response);
            TAPDataManager.getInstance().deleteAuthTicket();

            TAPDataManager.getInstance().saveAccessToken(response.getAccessToken());
            TAPDataManager.getInstance().saveRefreshToken(response.getRefreshToken());
            TAPDataManager.getInstance().saveRefreshTokenExpiry(response.getRefreshTokenExpiry());
            TAPDataManager.getInstance().saveAccessTokenExpiry(response.getAccessTokenExpiry());
            registerFcmToken();

            TAPDataManager.getInstance().saveActiveUser(response.getUser());
            runOnUiThread(() -> {
                Intent intent = new Intent(TAPLoginActivity.this, TAPRoomListActivity.class);
                intent.putExtra(K_MY_USERNAME, etUsername.getText().toString());
                startActivity(intent);
                TAPConnectionManager.getInstance().connect();
                finish();
            });
        }

        @Override
        public void onError(TAPErrorModel error) {
            super.onError(error);
            showDialog("ERROR "+error.getCode(), error.getMessage());
        }
    };

    private void showDialog(String title, String message) {
        new TapTalkDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPrimaryButtonTitle("OK")
                .setPrimaryButtonListener(view -> {
                    progressBar.setVisibility(View.GONE);
                    tvSignIn.setVisibility(View.VISIBLE);
                }).show();
    }

    private void registerFcmToken(){
        new Thread(() -> TAPDataManager.getInstance().registerFcmTokenToServer(TAPDataManager.getInstance().getFirebaseToken(), new TapDefaultDataView<TAPCommonResponse>() {})).start();
    }
}
