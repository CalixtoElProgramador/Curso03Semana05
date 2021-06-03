package com.example.curso03semana03;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutEmail;
    private TextInputEditText inputName, inputEmail, inputSubject, inputMessage;
    private MaterialCardView cardMessage;
    private MaterialButton buttonSend;
    private TextView textRequiredMessage, textErrorMessage, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setupViews();
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar_contact);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationOnClickListener(view -> onBackPressed());

        inputsChangesListeners();
        sendMail();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setupViews() {
        inputLayoutEmail = findViewById(R.id.inputLayout_mail);
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_mail);
        inputMessage = findViewById(R.id.input_message);
        buttonSend = findViewById(R.id.button_sendMail);
        cardMessage = findViewById(R.id.card_message);
        textRequiredMessage = findViewById(R.id.text_requiredMessage);
        textErrorMessage = findViewById(R.id.text_errorMessage);
        textMessage = findViewById(R.id.text_message);
        inputSubject = findViewById(R.id.input_subject);
    }

    private void sendMail() {
        buttonSend.setOnClickListener(view -> checkInputs());
    }

    private void checkInputs() {
        if (Objects.requireNonNull(inputEmail.getText()).toString().trim().isEmpty()) {
            inputLayoutEmail.setErrorEnabled(true);
            inputLayoutEmail.setError(getString(R.string.error_input_empty));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()) {
            inputLayoutEmail.setErrorEnabled(true);
            inputLayoutEmail.setError(getString(R.string.error_email_false));
        } else if (Objects.requireNonNull(inputMessage.getText()).toString().trim().isEmpty()) {
            cardMessage.setStrokeColor(getColor(R.color.colorError));
            textMessage.setTextColor(getColor(R.color.colorError));
            textRequiredMessage.setVisibility(View.INVISIBLE);
            textErrorMessage.setVisibility(View.VISIBLE);
        } else {
            sendParameters();
        }
    }

    private void sendParameters() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //properties.put("mail.smtp.port", "587");

        //Alternative configuration
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.getKeyEmail(), Constants.getKeyPassword());
            }
        });

        try {
            // Initialize email content
            Message message = new MimeMessage(session);
            //Sender email
            message.setFrom(new InternetAddress(Constants.getKeyEmail()));
            //Recipient email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress
                            .parse(Objects.requireNonNull(inputEmail.getText()).toString().trim()));
            //Email subject
            message.setSubject(subject());
            //Email message
            message.setText(messageContact());
            //Send email
            new SendMail().execute(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String subject() {
        String subject;
        if (Objects.requireNonNull(inputSubject.getText()).toString().trim().isEmpty()) {
            subject = "Android Pet App";
        } else {
            subject = "Android Pet App - " + inputSubject.getText().toString().trim();
        }
        return subject;
    }

    private String messageContact(){
        return Objects.requireNonNull(inputMessage.getText()).toString().trim() +
                '\n' + '\n' +
                Objects.requireNonNull(inputName.getText()).toString().trim() +
                '\n' + Objects.requireNonNull(inputEmail.getText()).toString().trim();
    }

    private class SendMail extends AsyncTask<Message, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Create and show progress dialog
            progressDialog = ProgressDialog.show(ContactActivity.this,
                    "Please Wait", "Sending Mail...",
                    true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //When success
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                //When error
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dismiss progress dialog
            progressDialog.dismiss();
            if (s.equals("Success")) {
                //When Success

                //Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color = '#509324'>Success</font>"));
                builder.setMessage("Mail send successfully.");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    //Clear all edit text
                    inputName.setText("");
                    inputEmail.setText("");
                    inputSubject.setText("");
                    inputMessage.setText("");
                });
                builder.show();
            } else {
                //When error
                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void inputsChangesListeners() {
        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputLayoutEmail.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cardMessage.setStrokeColor(getColor(R.color.material_on_surface_stroke));
                textErrorMessage.setVisibility(View.INVISIBLE);
                textRequiredMessage.setVisibility(View.VISIBLE);
                textMessage.setTextColor(getColor(R.color.colorTextPrimary));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}