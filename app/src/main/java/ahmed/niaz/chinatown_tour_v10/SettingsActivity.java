package ahmed.niaz.chinatown_tour_v10;

import android.media.effect.Effect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    public DatabaseReference databaseUsers;
    public Button submitFeedback;
    public EditText addFeedback;
    public EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users Feedback");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        submitFeedback = (Button) findViewById(R.id.submit_feedback_button);
        addFeedback = (EditText) findViewById(R.id.feedback_editText);
        email = (EditText) findViewById(R.id.email_editText);

        // When submit_feedback_button is clicked, it runs recordFeedback()
        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordFeedback();
            }
        });
    }

    // When run, it creates a user. Then checks if the feedback text box is empty
    public void recordFeedback(){
        String feedback = addFeedback.getText().toString();
        String Email = email.getText().toString();

        if(!TextUtils.isEmpty(feedback)){

            String id = databaseUsers.push().getKey();

            Users user = new Users(id,feedback,Email);

            databaseUsers.child(id).setValue(user);

            Toast.makeText(this, "Thank you for your feedback.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please fill in the feedback text box.", Toast.LENGTH_LONG).show();
        }
    }


}
