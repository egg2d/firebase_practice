package android.bins.practice;

import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.gms.tasks.OnSuccessListener;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import android.support.design.widget.NavigationView;
public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private static final String TAG = "MainAct";

    private FirebaseUser user;


    private DrawerLayout layoutDrawer;

    private ConstraintLayout navRightMain;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference userColRef = db.collection("user");

    private NavigationView navRightView;


    private String myNickname;
    private String myEmail;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        layoutDrawer = (DrawerLayout) findViewById(R.id.activityMain);
        navRightView = (NavigationView) findViewById(R.id.rightNavView);



        Button button = (Button) findViewById(R.id.btnSetting);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    layoutDrawer.openDrawer(navRightView);
            }


        });

        navRightMain=(ConstraintLayout) navRightView.getHeaderView(0);

        if (user.getDisplayName() != null) {

            TextView navRightMainNickname = (TextView) navRightMain.findViewById(R.id.txtNickname0);
            navRightMainNickname.setText(user.getDisplayName());
        }

        if (user.getEmail() != null) {

            TextView navRightMainEmail = (TextView) navRightMain.findViewById(R.id.txtEmail0);
            navRightMainEmail.setText(user.getEmail());
        }


        if (myNickname != null) {

            TextView navRightMainNickname1 = (TextView) navRightMain.findViewById(R.id.txtNickname1);
            navRightMainNickname1.setText(myNickname);
        }

        if (myEmail != null) {

            TextView navRightMainEmail1 = (TextView) navRightMain.findViewById(R.id.txtEmail1);
            navRightMainEmail1.setText(myEmail);
        }




        user = mAuth.getCurrentUser();


        Log.d("getUID",user.getUid());

        userColRef.document(user.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {




                            Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());


                            myNickname = new String(documentSnapshot.getData().get("nickname").toString());
                            myEmail = new String(documentSnapshot.getData().get("email").toString());


                            if (myNickname != null) {

                                TextView navRightMainNickname1 = (TextView) navRightMain.findViewById(R.id.txtNickname1);
                                navRightMainNickname1.setText(myNickname);
                            }

                            if (myEmail != null) {

                                TextView navRightMainEmail1 = (TextView) navRightMain.findViewById(R.id.txtEmail1);
                                navRightMainEmail1.setText(myEmail);
                            }


                        } else {


                        }
                    }
                });



    }










    @Override
    public void onBackPressed() {


        if (layoutDrawer.isDrawerOpen(navRightView)) {
            layoutDrawer.closeDrawer(navRightView);
            return;
        }




    }

}
