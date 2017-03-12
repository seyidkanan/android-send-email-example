package az.kanan.sendemail;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import az.kanan.sendemail.util.GmailSender;

public class MainActivity extends AppCompatActivity {

    EditText myEmail, pass, sendToEmail, subject, text;

    Button sendEmailButton;

    String myEmailString, passString, sendToEmailString, subjectString, textString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEmail = (EditText) findViewById(R.id.emaileditText);
        pass = (EditText) findViewById(R.id.passEditText);
        sendToEmail = (EditText) findViewById(R.id.sendToEmaileditText);
        subject = (EditText) findViewById(R.id.subjectEditText);
        text = (EditText) findViewById(R.id.textEditText);

        sendEmailButton = (Button) findViewById(R.id.button);

        final SendEmailTask sendEmailTask = new SendEmailTask();

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEmailString = myEmail.getText().toString();
                passString = pass.getText().toString();
                sendToEmailString = sendToEmail.getText().toString();
                subjectString = subject.getText().toString();
                textString = text.getText().toString();

                sendEmailTask.execute();
            }
        });
    }

    class SendEmailTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("Email sending", "sending start");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                GmailSender sender = new GmailSender(myEmailString, passString);
                //subject, body, sender, to
                sender.sendMail(subjectString,
                        textString,
                        myEmailString,
                        sendToEmailString);

                Log.i("Email sending", "send");
            } catch (Exception e) {
                Log.i("Email sending", "cannot send");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

}
